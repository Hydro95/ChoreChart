package net.sudormrf.chorechart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

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
            if (i==1)
                tasks.add(new Task("Clean Car", "You've already missed it...",
                        R.drawable.ic_logo_mil));
            else
                tasks.add(new Task());
        }

        TaskArrayAdapter adapter = new TaskArrayAdapter(getActivity(), tasks);

        ListView listView = (ListView) view.findViewById(R.id.taskList);
        listView.setAdapter(adapter);

        return view;
    }

}
