package net.sudormrf.chorechart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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

    ShoppingList shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        int index = getIntent().getIntExtra("index",-1);

        //need to find problem, crashes app if run
        if (index != -1) {
            shoppingList = Facade.getInstance().getShoppingList(index);

            ImageView icon = findViewById(R.id.listIcon);
            EditText name = findViewById(R.id.listName);
            TextView location = findViewById(R.id.listStore);

            icon.setImageResource(shoppingList.getIcon());
            name.setText(shoppingList.getName());
            location.setText(String.valueOf(shoppingList.getLocation()));
        }
        else {
            Facade.getInstance().addShoppingList(new ShoppingList(null, null, 1, Facade.getInstance()));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    public void onSaveButtonClick(View view) {

        ImageView icon = findViewById(R.id.listIcon);
        EditText name = findViewById(R.id.listName);
        TextView location = findViewById(R.id.listStore);

        //TODO: Set icon should be base64 string of icon.
        //shoppingList.setName(name.getText().toString());
        //shoppingList.setLocation(location.getText().toString());

        //Facade.getInstance().addShoppingList(shoppingList);
        Facade.getInstance().publishShoppingLists();
        finish();
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
                .setMinCropResultSize(128,128)
                .setMaxCropResultSize(512, 512)
                .start(this);
    }
}
