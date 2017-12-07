package net.sudormrf.chorechart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Walter on 2017-12-06.
 */

public class ItemListAdapter extends ArrayAdapter<Item> {
    public ItemListAdapter(Context context, List<Item> items)
    {
        super(context, R.layout.item_list_row, items);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getRowLayout(position, convertView, parent);
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getRowLayout(position, convertView, parent);
    }

    private View getRowLayout(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View row = inflater.inflate(R.layout.item_list_row, parent, false);

        Item selected = getItem(position);

        TextView itemName = (TextView) row.findViewById(R.id.itemName);

        itemName.setText(selected.getName());

        return row;
    }

}
