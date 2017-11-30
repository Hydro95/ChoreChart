package net.sudormrf.chorechart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by ryanc on 2017-11-24.
 */

public class ShoppingListFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generic_list, container, false);

        //Facade.getInstance().publishShoppingLists();

        ShoppingListArrayAdapter adapter = new ShoppingListArrayAdapter(getActivity(), Facade.getInstance().getShoppingLists());
      
        ListView listView = (ListView) view.findViewById(R.id.generic_list);
        listView.setAdapter(adapter);

        return view;
    }
}
