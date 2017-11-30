package net.sudormrf.chorechart;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Walter on 2017-11-29.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    private Calendar storedTime;
    private OnTimeSetListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Initialize variable to hold gotten time.
        storedTime = Calendar.getInstance();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, false);
    }

    public void onTimeSet(TimePicker dp, int hour, int minute)
    {
        storedTime.set(Calendar.HOUR_OF_DAY, hour);
        storedTime.set(Calendar.MINUTE, minute);
        listener.onTimeSet(storedTime);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof OnTimeSetListener) {
            listener = (OnTimeSetListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement TimePickerFragment.OnTimeSetListener");
        }
    }


    public Calendar getTime()
    {
        return storedTime;
    }

    public interface OnTimeSetListener {
        void onTimeSet(Calendar time);
    }
}
