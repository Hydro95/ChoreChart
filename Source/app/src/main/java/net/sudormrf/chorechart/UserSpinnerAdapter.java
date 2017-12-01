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
 * Created by Walter on 2017-12-01.
 */

public class UserSpinnerAdapter extends ArrayAdapter<User>
{
    UserSpinnerAdapter(Context context, List<User> users) {
        super(context, R.layout.user_row_spinner, users);
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
        View row = inflater.inflate(R.layout.user_row_spinner, parent, false);

        User selected = getItem(position);
        ImageView userIcon = (ImageView) row.findViewById(R.id.rowUserIcon);
        TextView userName = (TextView) row.findViewById(R.id.rowUserName);

        userIcon.setImageBitmap(ImageHelper.bitmapFromBase64(selected.getIcon()));
        userName.setText(selected.getName());

        return row;
    }

}
