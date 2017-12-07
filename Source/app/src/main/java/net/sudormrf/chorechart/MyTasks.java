package net.sudormrf.chorechart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_generic_list);
        ArrayList<Task> tasks = new ArrayList<Task>();

        TaskArrayAdapter adapter;

        adapter = new TaskArrayAdapter(this, Facade.getInstance().getUser(
                getIntent().getStringExtra("userId")).getTasks());

        Log.d("test", Facade.getInstance().getUser(getIntent().getStringExtra("userId")).getName());
        Log.d("test", Integer.toString(Facade.getInstance().getUser(getIntent().getStringExtra("userId")).getTaskIds().size()));

        ListView listView = (ListView) findViewById(R.id.generic_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
                Intent intent = new Intent(getBaseContext(), EditTaskActivity.class);
                intent.putExtra("index", index);
                //TODO: ^^ this is going to give the wrong item change it to id from index
                startActivity(intent);
            }

        });
    }
}
