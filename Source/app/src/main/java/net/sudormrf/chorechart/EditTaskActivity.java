package net.sudormrf.chorechart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.Manifest;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity implements
        DateTimeFragment.OnDateTimeSetListener,
        DatePickerFragment.OnDateSetListener,
        TimePickerFragment.OnTimeSetListener {

    //Date stored as variable to facilitate getting data from the fragment.
    private Calendar deadline;

    private boolean dateSet;
    private boolean timeSet;
    private boolean isNewTask;

    private Uri mImageUri;
    private Bitmap taskImg;

    private Task task;

    @Override
    //TODO: Add a way to tell this activity,whether its a new task or an existing one.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        int index = getIntent().getIntExtra("index", -1);

        if(index != -1) {
            task = Facade.getInstance().getTask(index);

            CheckBox checkBox2 = findViewById(R.id.checkBox2);
            //TODO: figure out how spinner works (select user)
            //TODO: select REPEAT
            ImageView icon = findViewById(R.id.task_image); //TODO: change to x64 stirng
            EditText name = findViewById(R.id.name);
            EditText duration = findViewById(R.id.duration);
            //TODO: figure out how to set datetime
            EditText comment = findViewById(R.id.comment);

            checkBox2.setChecked(task.getCompleted());
            //user goes here
            //repeat goes here
            if (task.hasAllocation()) {
                Bitmap userIcon = ImageHelper.bitmapFromBase64(task.getUser().getIcon());
                icon.setImageBitmap(userIcon);
            }

            name.setText(task.getName());
            duration.setText(task.getDuration());
            //datetime goes here
            comment.setText(task.getComment());
        }
        else {
            task = new Task();
            Facade.getInstance().addTask(task);
        }

        //Setup repeat menu (based from https://developer.android.com/guide/topics/ui/controls/spinner.html)
        Spinner repeat = (Spinner) findViewById(R.id.repeat);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.repeat_choices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        repeat.setAdapter(adapter);

        //Setup users menu
        Spinner users = (Spinner) findViewById(R.id.user);
        List<User> userList = Facade.getInstance().getUsers();
        //Convert List<T> to ArrayList<T> because UserArrayAdapter expects ArrayList<T>
        //ArrayList<User> actualUserList = new ArrayList<>(userList);
        //UserArrayAdapter uAdapter = new UserArrayAdapter(this, actualUserList);
        UserArrayAdapter uAdapter = new UserArrayAdapter(this, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        users.setAdapter(uAdapter);

        deadline = Calendar.getInstance();
        dateSet = false;
        timeSet = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        System.out.println(id);

        //TODO: Add delete confirm alertdialog.
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            System.out.println("trash");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSaveButtonClick(View view)
    {
        //TODO: Bitmap to Base64.
        //Facade.getInstance().addTask(task);
        finish();

        /*
        ImageView icon = findViewById(R.id.task_image);
        EditText name = findViewById(R.id.name);

        //TODO: Set icon should be base64 string of icon.
        user.setIcon(R.drawable.ic_logo_empty);
        user.setName(name.getText().toString());
        user.setPoints(Integer.parseInt(points.getText().toString().substring(8)));
        if (user.getId() == null) {
            user.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        System.out.println(user);
        Facade.getInstance().publishUsers();

        */

        finish();
    }

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
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                } else {
                    // no permissions required or already granted, can start crop image activity
                    cropImage(imageUri);
                }
            }
            else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri img = result.getUri();
                ImageView icon = (ImageView) findViewById(R.id.task_image);
                try {
                    Bitmap btm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), img);
                    taskImg = btm;
                    icon.setImageBitmap(btm);
                }
                catch(IOException e) {
                    System.out.println("Cannot open: " + img.toString());
                }
                icon.setImageURI(img);
            }
        }
    }
    public void onDateTimeSet(Calendar datetime) { }

    public void onDateSet(Calendar date)
    {
        deadline.set(Calendar.YEAR, date.get(Calendar.YEAR));
        deadline.set(Calendar.MONTH, date.get(Calendar.MONTH));
        deadline.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
        dateSet = true;
        updateDeadline();
    }

    public void onTimeSet(Calendar time)
    {
        deadline.set(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
        deadline.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
        timeSet = true;
        updateDeadline();
    }

    private void updateDeadline()
    {
        if(dateSet && timeSet) {
            DateTimeFragment dt =
                    (DateTimeFragment) this.getSupportFragmentManager().findFragmentById(R.id.deadline);
            dt.updateDateTime(deadline);
        }
    }

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
