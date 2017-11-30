package net.sudormrf.chorechart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ryanc on 2017-11-29.
 */

//Code adapted from github wiki "Using an ArrayAdapter with ListView"
//at link: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

public class ShoppingListArrayAdapter extends ArrayAdapter<ShoppingList> {

    public ShoppingListArrayAdapter(Context context, ArrayList<ShoppingList> lists){
        super(context, 0, lists);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        ShoppingList list = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }
        //Lookup view for data population
        ImageView listIcon = (ImageView) convertView.findViewById(R.id.listIcon);
        TextView listName = (TextView) convertView.findViewById(R.id.listName);
        TextView listLocation = (TextView) convertView.findViewById(R.id.listLocation);
        //populate the data into the template view using the data object
        listIcon.setImageResource(list.getIcon());
        listName.setText(list.getName());
        listLocation.setText(list.getLocation());
        // Return the completed view to render on screen
        return convertView;
    }


}
