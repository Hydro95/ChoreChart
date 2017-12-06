package net.sudormrf.chorechart;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import java.util.List;

public class MyTasks extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        List<Task> list;
        Intent intent = getIntent();
        int userIndex = intent.getIntExtra("userIndex", -1);
        if(userIndex == -1)
            list = Facade.getInstance().getTasks();
        else
            list = Facade.getInstance().getUser(userIndex).getTasks();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TasksFragment fragment = new TasksFragment(list);
        fragmentTransaction.add(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }
}
