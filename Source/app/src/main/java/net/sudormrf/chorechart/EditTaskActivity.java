package net.sudormrf.chorechart;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
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
        List<User> userList = new ArrayList(Facade.getInstance().getUsers());

        //Add dummy user value for no allocation.
        User nullUser = new User();
        nullUser.setName("Select a User");
        Bitmap nullUserIcon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_logo_empty);
        nullUser.setIcon(ImageHelper.bitmapToBase64(nullUserIcon));
        userList.add(0,nullUser);

        UserSpinnerAdapter uAdapter = new UserSpinnerAdapter(this, userList);
        users.setAdapter(uAdapter);

        //Setup complete checkbox
        CheckBox checkBox2 = findViewById(R.id.complete);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if(b == true) {
                     System.out.println("checked");
                     task.markCompleted();
                 }
                 else {
                     System.out.println("not checked");
                     System.out.println("fail the task?");
                 }
             }
        });
        deadline = Calendar.getInstance();
        dateSet = false;
        timeSet = false;

        int index = getIntent().getIntExtra("index", -1);

        if(index != -1) {
            task = Facade.getInstance().getTask(index);
            deadline.setTimeInMillis(Long.parseLong(task.getDeadline()));

            EditText name = findViewById(R.id.name);
            EditText duration = findViewById(R.id.duration);
            EditText comment = findViewById(R.id.comment);
            DateTimeFragment dtf = (DateTimeFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.deadline);

            name.setText(task.getName());
            duration.setText(task.getDuration());
            comment.setText(task.getComment());
            dtf.updateDateTime(deadline);
            checkBox2.setChecked(task.getCompleted());

            //User select
            for(int i = 0; i < userList.size(); i++) {
                User user = userList.get(i);
                if(user.getId().equals(task.getUserId())) {
                    users.setSelection(i);
                }
            }

            //Repeat
            repeat.setSelection(repeatToInt(task.getFrequency()));

            isNewTask = false;
        }
        else {
            task = new Task();
            isNewTask = true;
        }
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
                    .setTitle("Delete Task")
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
        CheckBox checkbox = findViewById(R.id.complete);

        //Get unix time stamp(in milliseconds), then set time in task.
        long time = deadline.getTimeInMillis();
        task.setDeadline(Long.toString(time));

        //Get User
        if(user.getSelectedItemPosition() == 0) {
            task.setUserId("");

            //Umple state machine hook. (Status)
            if(task.getStatus() == Task.Status.InProgress) {
                task.release();
            }
        }
        else {
            task.setUserId(Facade.getInstance().getUser(user.getSelectedItemPosition()-1).getId());
            //Umple state machine hook. (Status)
            task.setUserId();
        }

        task.setName(name.getText().toString());
        task.setDuration(duration.getText().toString());
        task.setFrequency(Task.Repeat.values()[repeat.getSelectedItemPosition()]);
        task.setComment(comment.getText().toString());
        task.setCompleted(checkbox.isChecked());

        //this handles whether or not the thing is new, if so, assign it a new id. else, edit the existing one
        if (task.getId() == null) {
            task.setId(Facade.getInstance().getUserRef().push().getKey());
        }

        try {
            Facade.getInstance().addTask(task);
            Facade.getInstance().publishTasks();
            finish();
        }
        catch(Exception e) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Incorrect information. Please check all fields for missing data.")
                    .setNeutralButton("Okay", null)
                    .show();
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

    private int repeatToInt(Task.Repeat in)
    {
        for(int i = 0; i < Task.Repeat.values().length; i++) {
            if(Task.Repeat.values()[i].equals(in)) {
                return i;
            }
        }

        return 0;
    }
}
