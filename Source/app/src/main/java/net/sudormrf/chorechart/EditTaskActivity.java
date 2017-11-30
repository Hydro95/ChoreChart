package net.sudormrf.chorechart;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class EditTaskActivity extends AppCompatActivity implements DateTimeFragment.OnFragmentInteractionListener {

    @Override
    //TODO: Add a way to tell this activity,whether its a new task or an existing one.
    //TODO: Create fragment that get a date/datetime from the user.
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
        UserArrayAdapter uAdapter = new UserArrayAdapter(this, userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        users.setAdapter(uAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    public void onFragmentInteraction(Uri uri)
    {

    }

}
