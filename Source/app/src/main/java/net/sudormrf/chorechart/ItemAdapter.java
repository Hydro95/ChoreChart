package net.sudormrf.chorechart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ryanc on 2017-12-01.
 */

public class ItemAdapter extends ArrayAdapter<String> {

    ItemAdapter(Context context, List<String> lists){super(context, 0, lists); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get the data item for this position
        String item = getItem(position);
        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
        //Lookup view for data population

        TextView itemName = convertView.findViewById(R.id.itemStringName);

        //populate the data into the template view using the data object

        itemName.setText(item);

        // Return the completed view to render on screen
        return convertView;
    }
}