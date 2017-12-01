package net.sudormrf.chorechart;

import android.*;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class AddList extends AppCompatActivity {

    private Uri mImageUri;
    private Bitmap listImg;
    private boolean isNewList;

    private final int CAMERA_PERMISSIONS = 1;

    ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        int index = getIntent().getIntExtra("index",-1);

        if (index != -1) {
            shoppingList = Facade.getInstance().getShoppingList(index);

            ImageView icon = findViewById(R.id.listIcon);
            EditText name = findViewById(R.id.listName);
            TextView location = findViewById(R.id.listStore);

            RoundedBitmapDrawable rDrawable;
            rDrawable = ImageHelper.roundedImageFromBase64(getResources(), shoppingList.getIcon());

            icon.setImageDrawable(rDrawable);
            name.setText(shoppingList.getName());
            location.setText(String.valueOf(shoppingList.getLocation()));

            isNewList = false;
        }
        else {
            shoppingList = new ShoppingList();
            Facade.getInstance().addShoppingList(shoppingList);
            isNewList = true;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    public void onAddListClick(View view)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("index", Facade.getInstance().indexOfShoppingList(shoppingList));
        Intent intent = new Intent(this,ItemList.class);
        intent.putExtra("index", Facade.getInstance().indexOfShoppingList(shoppingList));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(isNewList) {
            Facade.getInstance().removeShoppingList(shoppingList);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void onSaveButtonClick(View view) {
        EditText name = findViewById(R.id.listName);
        TextView location = findViewById(R.id.listStore);

        String encodedPic = ImageHelper.bitmapToBase64(listImg, Bitmap.CompressFormat.WEBP, 90);

        shoppingList.setIcon(encodedPic);
        shoppingList.setName(name.getText().toString());
        shoppingList.setLocation(location.getText().toString());

        //shoppingList.setName("Clothes");
        //shoppingList.setLocation("Soup Store");

        if (shoppingList.getId() == null) {
            shoppingList.setId(Facade.getInstance().getShoppingRef().push().getKey());
        }


        Facade.getInstance().publishShoppingLists();
        finish();

        //this handles whether or not the thing is new, if so, assign it a new id. else, edit the existing one
        /* if (shoppingList.getId() == null) {
            shoppingList.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        try {
            Facade.getInstance().addShoppingList(shoppingList);
            Facade.getInstance().publishShoppingLists();
            finish();
        }
        catch (Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Incorrect information. Please check all fields for missing data.")
                    .setNeutralButton("Okay", null)
                    .show();
        } */
    }

    public void onDeleteButtonClick(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setMessage("Are you sure you want to delete this user?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Facade.getInstance().getShoppingRef().child(shoppingList.getId()).removeValue();
                        Facade.getInstance().removeShoppingList(shoppingList);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    //Request permission to use the camera, then fire off the event to get an image.
    public void onIconClick(View view)
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

        if(permissionCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSIONS);
        }
        else {
            pickImage();
        }
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
                ImageView icon = (ImageView) findViewById(R.id.listIcon);
                try {
                    Bitmap btm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
                    listImg = btm;
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
}
