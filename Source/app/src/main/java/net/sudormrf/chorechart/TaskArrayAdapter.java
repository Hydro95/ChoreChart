package net.sudormrf.chorechart;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jon on 24/11/17.
 */

//Code adapted from github wiki "Using an ArrayAdapter with ListView"
//at link: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class TaskArrayAdapter extends ArrayAdapter<Task> {

    TaskArrayAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout, parent, false);
        }
        // Lookup view for data population
        ImageView icon = convertView.findViewById(R.id.listIcon);
        TextView name = convertView.findViewById(R.id.listName);
        TextView deadline = convertView.findViewById(R.id.listInfo);

        RoundedBitmapDrawable rDrawable;
        if(task.hasAllocation() || task.getUser() != null) {
            rDrawable = ImageHelper.roundedImageFromBase64(getContext().getResources(), task.getUser().getIcon());
        }
        else {
            rDrawable = ImageHelper.roundedImage(getContext().getResources(), R.drawable.ic_logo_empty);
        }

        icon.setImageDrawable(rDrawable);

        name.setText(task.getName());

        //Drawing date properly
        String strDeadline = "Deadline: ";
        if(task.getDeadline() == null) {
            strDeadline += "N/A";
        }
        else {
            Calendar tmp = Calendar.getInstance();
            tmp.setTimeInMillis(Long.parseLong(task.getDeadline()));
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd kk:mm");
            strDeadline += fmt.format(tmp.getTime());
        }

        deadline.setText(strDeadline);
        // Return the completed view to render on screen
        return convertView;
    }
}
