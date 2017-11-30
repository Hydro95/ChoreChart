package net.sudormrf.chorechart;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity implements
        DateTimeFragment.OnDateTimeSetListener,
        DatePickerFragment.OnDateSetListener,
        TimePickerFragment.OnTimeSetListener {

    private static final int PICK_IMAGE = 1;
    private static final int CROP_IMAGE = 2;

    //Date stored as variable to facilitate getting data from the fragment.
    private Calendar deadline;

    private boolean dateSet;
    private boolean timeSet;
    private boolean isNewTask;

    @Override
    //TODO: Add a way to tell this activity,whether its a new task or an existing one.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

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
        //TODO: Implement db sync.
        //TODO: Bitmap to Base64.
        finish();
    }

    //Launching an intent to get a image from gallery based on
    //https://stackoverflow.com/questions/5309190/android-pick-images-from-gallery
    public void onIconClick(View view)
    {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        //Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        //chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(pickIntent, PICK_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            if(resultCode == RESULT_OK) {
                cropImage(data.getData());
            }
        }
        else if(requestCode == CROP_IMAGE) {
            if(resultCode == RESULT_OK) {
                ImageView img = (ImageView) findViewById(R.id.task_image);
                Bundle imgData = data.getExtras();
                Bitmap stuff = imgData.getParcelable("data");
                img.setImageBitmap(stuff);
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

    //Image cropping intent based off of:
    //https://code.tutsplus.com/tutorials/capture-and-crop-an-image-with-the-device-camera--mobile-11458
    private void cropImage(Uri imgUri)
    {
        //Uri selImage = data.getData();
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(imgUri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 128);
        cropIntent.putExtra("outputY", 128);
        cropIntent.putExtra("return-data", true);
        startActivityForResult(cropIntent, CROP_IMAGE);
    }

}
