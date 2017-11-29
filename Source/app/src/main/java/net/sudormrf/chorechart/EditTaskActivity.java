package net.sudormrf.chorechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    //TODO: Add a way to tell this activity,whether its a new task or an exsiting one.
    //TODO: Create fragment that get a date/datetime from the user.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
    }
}
