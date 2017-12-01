package net.sudormrf.chorechart;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryanc on 2017-11-29.
 */

//Code adapted from github wiki "Using an ArrayAdapter with ListView"
//at link: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView

public class ShoppingListArrayAdapter extends ArrayAdapter<ShoppingList> {

    ShoppingListArrayAdapter(Context context, List<ShoppingList> lists){super(context, 0, lists); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        ShoppingList list = getItem(position);

        System.out.println("HEY THIS IS THE LIST I WANT TO PRINT" + list);
        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }
        //Lookup view for data population
        ImageView listIcon = convertView.findViewById(R.id.listIcon);
        TextView listName = convertView.findViewById(R.id.listName);
        TextView listLocation = convertView.findViewById(R.id.listInfo);

        //populate the data into the template view using the data object

        listName.setText(list.getName());
        listLocation.setText(list.getLocation());

        // Return the completed view to render on screen
        return convertView;
    }


}
