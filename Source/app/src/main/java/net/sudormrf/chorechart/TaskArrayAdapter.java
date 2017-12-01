package net.sudormrf.chorechart;

import android.content.Context;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        if(task.hasAllocation()) {
            rDrawable = ImageHelper.roundedImageFromBase64(getContext().getResources(), task.getUser().getIcon());
        }
        else {
            rDrawable = ImageHelper.roundedImage(getContext().getResources(), R.drawable.add_new);
        }

        icon.setImageDrawable(rDrawable);

        name.setText(task.getName());
        deadline.setText("Deadline: " + ((task.getDeadline() == null) ? "N/A" : task.getDeadline()));
        // Return the completed view to render on screen
        return convertView;
    }
}
