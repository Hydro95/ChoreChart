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
 * Created by Josh on 2017-11-24.
 */

//Code adapted from github wiki "Using an ArrayAdapter with ListView"
//at link: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class UserArrayAdapter extends ArrayAdapter<User> {

    UserArrayAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_card_layout, parent, false);
        }
        // Lookup view for data population
        ImageView icon = convertView.findViewById(R.id.userIcon);
        TextView name = convertView.findViewById(R.id.userName);
        TextView points = convertView.findViewById(R.id.userPoints);
        // Populate the data into the template view using the data object
        icon.setImageResource(user.icon);
        name.setText(user.name);
        points.setText(Integer.toString(user.points));
        // Return the completed view to render on screen
        return convertView;
    }
}

