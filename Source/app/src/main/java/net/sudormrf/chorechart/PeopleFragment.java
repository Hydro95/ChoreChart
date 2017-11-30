package net.sudormrf.chorechart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Josh on 2017-11-24.
 */

public class PeopleFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_people, container, false);

        Facade.getInstance().publishUsers();

        UserArrayAdapter adapter = new UserArrayAdapter(getActivity(), Facade.getInstance().getUsers());

        GridView gridView = view.findViewById(R.id.peopleGrid);
        gridView.setAdapter(adapter);

        return view;
    }

}
