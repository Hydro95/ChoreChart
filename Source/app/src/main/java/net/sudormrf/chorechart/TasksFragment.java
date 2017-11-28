package net.sudormrf.chorechart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by jon on 24/11/17.
 */

public class TasksFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (int i = 0; i < 10; i++) {
            tasks.add(Facade.getInstance().addTask("Clean Car", "Now"));
        }

        TaskArrayAdapter adapter = new TaskArrayAdapter(getActivity(), tasks);

        ListView listView = (ListView) view.findViewById(R.id.taskList);
        listView.setAdapter(adapter);

        return view;
    }

}
