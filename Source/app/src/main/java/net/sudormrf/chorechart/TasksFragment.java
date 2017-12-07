package net.sudormrf.chorechart;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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

    public static TasksFragment newInstance(String userId) {

        TasksFragment fragment = new TasksFragment();

        Bundle bundle = new Bundle();
        bundle.putString("userId", userId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_generic_list, container, false);
        ArrayList<Task> tasks = new ArrayList<Task>();

        Bundle args = getArguments();
        final String userId;
        if(args != null)
            userId = args.getString("userId");
        else
            userId = null;

        TaskArrayAdapter adapter;

        if(userId == null)
            adapter = new TaskArrayAdapter(getActivity(), Facade.getInstance().getTasks());
        else
            adapter = new TaskArrayAdapter(getActivity(), Facade.getInstance().getUser(userId).getTasks());

        ListView listView = (ListView) view.findViewById(R.id.generic_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
                Intent intent = new Intent(getContext(), EditTaskActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }

        });

        return view;
    }

}
