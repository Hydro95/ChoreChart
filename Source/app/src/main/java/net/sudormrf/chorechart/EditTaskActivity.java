package net.sudormrf.chorechart;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.Manifest;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
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

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        int index = getIntent().getIntExtra("index", -1);

        if(index != -1) {
            task = Facade.getInstance().getTask(index);

            CheckBox checkBox2 = findViewById(R.id.checkBox2);
            //TODO: figure out how spinner works (select user)
            //TODO: select REPEAT
            EditText name = findViewById(R.id.name);
            EditText duration = findViewById(R.id.duration);
            //TODO: figure out how to set datetime
            EditText comment = findViewById(R.id.comment);

            checkBox2.setChecked(task.getCompleted());
            //user goes here
            //repeat goes here

            name.setText(task.getName());
            duration.setText(task.getDuration());
            //datetime goes here
            comment.setText(task.getComment());

            isNewTask = false;
        }
        else {
            task = new Task();
            Facade.getInstance().addTask(task);
            isNewTask = true;
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
        UserSpinnerAdapter uAdapter = new UserSpinnerAdapter(this, userList);
        users.setAdapter(uAdapter);

        deadline = Calendar.getInstance();
        dateSet = false;
        timeSet = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //Only show delete button if it is a new task.
        if(!isNewTask) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_delete) {
            //Ask the user for deletion.
            new AlertDialog.Builder(this)
                    .setTitle("Delete User")
                    .setMessage("Are you sure you want to delete this task?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Facade.getInstance().getTaskRef().child(task.getId()).removeValue();
                            Facade.getInstance().removeTask(task);
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSaveButtonClick(View view)
    {
        EditText name = findViewById(R.id.name);
        EditText duration = findViewById(R.id.duration);
        Spinner repeat = findViewById(R.id.repeat);
        Spinner user = findViewById(R.id.user);
        EditText comment = findViewById(R.id.comment);
        CheckBox checkbox = findViewById(R.id.checkBox2);

        task.setName(name.getText().toString());
        task.setDuration(duration.getText().toString());
        task.setDeadline(deadline.toString());
        task.setFrequency(Task.Repeat.values()[repeat.getSelectedItemPosition()]);
        task.setUserId(Facade.getInstance().getUser(user.getSelectedItemPosition()).getId());
        task.setComment(comment.getText().toString());
        task.setCompleted(checkbox.isChecked());

        //this handles whether or not the thing is new, if so, assign it a new id. else, edit the existing one
        if (task.getId() == null) {
            task.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        //TODO: Why won't sync properly?
        try {
            Facade.getInstance().publishTasks();
            finish();
        }
        catch(Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Incorrect information. Please check all fields for missing data.")
                    .setNeutralButton("Okay", null)
                    .show();

            e.printStackTrace();
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

}
