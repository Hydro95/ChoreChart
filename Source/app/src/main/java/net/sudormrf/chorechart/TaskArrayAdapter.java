package net.sudormrf.chorechart;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jon on 24/11/17.
 */

//Code adapted from github wiki "Using an ArrayAdapter with ListView"
//at link: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class TaskArrayAdapter extends ArrayAdapter<Task> {

    TaskArrayAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_layout, parent, false);
        }
        // Lookup view for data population
        ImageView icon = (ImageView) convertView.findViewById(R.id.taskIcon);
        TextView name = (TextView) convertView.findViewById(R.id.taskName);
        TextView deadline = (TextView) convertView.findViewById(R.id.deadline);
        // Populate the data into the template view using the data object
        if(task.hasAllocation())
            icon.setImageResource(task.getUser().getIcon());
        else
            icon.setImageResource(R.drawable.add_new);
        name.setText(task.getName());
        deadline.setText("Deadline:" + task.getDeadline().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
