package net.sudormrf.chorechart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 24/11/17.
 */

public class TasksFragment extends Fragment {

    private TaskArrayAdapter adapter;

    public TasksFragment() {
        super();
        adapter = new TaskArrayAdapter((getActivity(), Facade.getInstance().getTasks()));
    }

    //TODO: remove constructor taking arguments replace with newInstance method (cause this will have unexpected behavior)
    public TasksFragment(List<Task> list) {
        super();
        adapter = new TaskArrayAdapter(getActivity(), list);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic_list, container, false);
        ArrayList<Task> tasks = new ArrayList<Task>();

        ListView listView = (ListView) view.findViewById(R.id.generic_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
                Intent intent = new Intent(getContext(), EditTaskActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }

        });

        return view;
    }

}
