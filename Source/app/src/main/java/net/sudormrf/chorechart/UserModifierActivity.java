package net.sudormrf.chorechart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import android.widget.TextView;

/**
 * Created by Josh on 2017-11-27.
 */

//confirmation dialog help from https://stackoverflow.com/questions/2257963/how-to-show-a-dialog-to-confirm-that-the-user-wishes-to-exit-an-android-activity#2258147

public class UserModifierActivity extends AppCompatActivity {
    private Uri mImageUri;
    private Bitmap userImg;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

        int index = getIntent().getIntExtra("index",-1);

        if (index != -1) {
            user = Facade.getInstance().getUser(index);

            ImageView icon = findViewById(R.id.userIcon);
            EditText name = findViewById(R.id.userName);
            TextView points = findViewById(R.id.points);

            //icon.setImageResource(user.getIcon());
            icon.setImageResource(R.drawable.ic_logo_empty);
            name.setText(user.getName());
            points.setText("Points: " + String.valueOf(user.getPoints()));
        }
        else {
            user = new User();
            Facade.getInstance().addUser(user);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present. 
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    public void onSaveButtonClick(View view) {

        ImageView icon = findViewById(R.id.userIcon);
        EditText name = findViewById(R.id.userName);
        TextView points = findViewById(R.id.points);

        //TODO: Set icon should be base64 string of icon.
        user.setIcon(R.drawable.ic_logo_empty);
        user.setName(name.getText().toString());
        user.setPoints(Integer.parseInt(points.getText().toString().substring(8)));

        //this handles whether or not the thing is new, if so, assign it a new id. else, edit the existing one
        if (user.getId() == null) {
            user.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        try {
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

    public void onDeleteButtonClick(View view) {
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


    //Launching an intent to get a image from gallery based on
    //https://stackoverflow.com/questions/5309190/android-pick-images-from-gallery
    public void onIconClick(View view)
    {
        CropImage.startPickImageActivity(this);
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
                ImageView icon = (ImageView) findViewById(R.id.userIcon);
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
                .setMinCropResultSize(128,128)
                .setMaxCropResultSize(512, 512)
                .start(this);
    }
}
