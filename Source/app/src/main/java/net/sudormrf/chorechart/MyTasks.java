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
        
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TasksFragment fragment = TasksFragment.newInstance(getIntent().getIntExtra("userIndex", -1));
        fragmentTransaction.add(R.id.main_fragment, fragment);
        fragmentTransaction.commit();

    }
}
