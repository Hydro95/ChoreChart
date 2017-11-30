package net.sudormrf.chorechart;

import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
