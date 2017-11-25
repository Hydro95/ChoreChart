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
        ArrayList<User> users = new ArrayList();
        //generate some dummy users for the UI
        for (int i = 0; i < 5; i++) {
            String name = "Anon" + Integer.toString((int)Math.round(Math.random() * 99));
            User nextUser = new User(name,  R.drawable.ic_logo_mil);
            nextUser.setPoints((int)Math.round(Math.random() * 99));
            users.add(nextUser);
        }

        //create the add user button
        users.add(new User("New", R.drawable.add_new));

        UserArrayAdapter adapter = new UserArrayAdapter(getActivity(), users);

        GridView gridView = view.findViewById(R.id.peopleGrid);
        gridView.setAdapter(adapter);

        return view;
    }

}