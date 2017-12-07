package net.sudormrf.chorechart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

/**
 * Created by Josh on 2017-11-27.
 */

//confirmation dialog help from https://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity#2258147

public class UserModifierActivity extends AppCompatActivity {
    private Uri mImageUri;
    private Bitmap userImg;
    private boolean isNewUser;

    private User user;

    private final int CAMERA_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        int index = getIntent().getIntExtra("index",-1);

        if (index != -1) {
            user = Facade.getInstance().getUser(index);

            ImageView icon = findViewById(R.id.rowUserIcon);
            EditText name = findViewById(R.id.userName);
            TextView points = findViewById(R.id.points);

            //Decode from Base64 string.
            userImg = ImageHelper.bitmapFromBase64(user.getIcon());
            icon.setImageBitmap(userImg);

            name.setText(user.getName());
            points.setText("Points: " + String.valueOf(user.getPoints()));

            isNewUser = false;
        }
        else {
            user = new User();
            isNewUser = true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(!isNewUser) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }

        return true;
    }

    public void onSaveButtonClick(View view) {

        ImageView icon = findViewById(R.id.rowUserIcon);
        EditText name = findViewById(R.id.userName);

        //TODO: Maybe a robust system for default image.
        if(userImg == null) {
            userImg = BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo_empty);
        }

        //Calculate Base64 string.
        String encodedPic = ImageHelper.bitmapToBase64(userImg, Bitmap.CompressFormat.WEBP, 90);

        user.setIcon(encodedPic);
        user.setName(name.getText().toString());

        //this handles whether or not the thing is new, if so, assign it a new id. else, edit the existing one
        if (user.getId() == null) {
            user.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        try {
            Facade.getInstance().addUser(user);
            Facade.getInstance().publishUsers();
            finish();
        }
        catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Incorrect information. Please check all fields for missing data.")
                    .setNeutralButton("Okay", null)
                    .show();
        }
    }

    //Request permission to use the camera, then fire off the event to get an image.
    public void onIconClick(View view)
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if(permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSIONS);
        }
        else {
            pickImage();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_delete) {
            //Ask the user for deletion.
            new AlertDialog.Builder(this)
                    .setTitle("Delete User")
                    .setMessage("Are you sure you want to delete this user?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Facade.getInstance().getUserRef().child(user.getId()).removeValue();
                            Facade.getInstance().removeUser(user);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    //Android 6 requires runtime permissions for certain things.
    //https://github.com/ArthurHub/Android-Image-Cropper/wiki/Pick-image-for-cropping-from-Camera-or-Gallery
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        if (requestCode == CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            if (mImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // required permissions granted, start crop image activity
                cropImage(mImageUri);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
            }
        }
        else if(requestCode == CAMERA_PERMISSIONS) {
            if(grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "Camera will not be used due to insufficient permission.", Toast.LENGTH_LONG).show();
            }

            pickImage();
        }
    }

    //See https://github.com/ArthurHub/Android-Image-Cropper/wiki/Pick-image-for-cropping-from-Camera-or-Gallery
    //for more details.
    //SuppressLint required due to some issues.
    @Override
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK) {
            if(requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE) {
                Uri imageUri = CropImage.getPickImageResultUri(this, data);

                // For API >= 23 we need to check specifically that we have permissions to read external storage.
                if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                    // request permissions and handle the result in onRequestPermissionsResult()
                    mImageUri = imageUri;
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE} , CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                } else {
                    // no permissions required or already granted, can start crop image activity
                    cropImage(imageUri);
                }
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri img = result.getUri();
                ImageView icon = (ImageView) findViewById(R.id.rowUserIcon);
                try {
                    Bitmap btm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
                    userImg = btm;
                    icon.setImageBitmap(btm);
                }
                catch(IOException e) {
                    System.out.println("Cannot open: " + img.toString());
                }
                icon.setImageURI(img);
            }
        }
    }

    //Image cropping intent based off of:
    //https://code.tutsplus.com/tutorials/capture-and-crop-an-image-with-the-device-camera--mobile-11458
    private void cropImage(Uri imgUri)
    {
        CropImage.activity(imgUri)
                .setFixAspectRatio(true)
                .setAspectRatio(1,1)
                .setMinCropResultSize(256,256)
                .setMaxCropResultSize(1024, 1024)
                .start(this);
    }

    private void pickImage() { CropImage.startPickImageActivity(this); }

    public void onViewTasksClick(View view) {
        Intent intent = new Intent(this, MyTasks.class);
        intent.putExtra("userId", user.getId());
        startActivity(intent);
    }
}
