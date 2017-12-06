package net.sudormrf.chorechart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Josh on 2017-11-24.
 */

public class PeopleFragment extends Fragment {

    UserArrayAdapter adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_people, container, false);

        adapter = new UserArrayAdapter(getActivity(), Facade.getInstance().getUsers());

        GridView gridView = view.findViewById(R.id.peopleGrid);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int index, long id) {
                Intent intent = new Intent(getContext(), UserModifierActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);
            }

        });

        return view;
    }

}
