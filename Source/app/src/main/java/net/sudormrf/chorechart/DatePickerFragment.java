package net.sudormrf.chorechart;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Walter on 2017-11-29.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    private Calendar storedDate;
    private OnDateSetListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Initialize variable to hold gotten date.
        storedDate = Calendar.getInstance();

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker dp, int year, int month, int day)
    {
        storedDate.set(Calendar.YEAR, year);
        storedDate.set(Calendar.MONTH, month);
        storedDate.set(Calendar.DAY_OF_MONTH, day);
        listener.onDateSet(storedDate);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof OnDateSetListener) {
            listener = (OnDateSetListener) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement DatePickerFragment.OnDateSetListener");
        }
    }

    public Calendar getDate()
    {
        return storedDate;
    }

    public interface OnDateSetListener {
        void onDateSet(Calendar date);
    }
}
