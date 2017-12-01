package net.sudormrf.chorechart;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DateTimeFragment.OnDateTimeSetListener} interface
 * to handle interaction events.
 * Use the {@link DateTimeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateTimeFragment extends Fragment implements View.OnClickListener {

    private OnDateTimeSetListener mListener;

    public DateTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DateTimeFragment.
     */
    public static DateTimeFragment newInstance() {
        DateTimeFragment fragment = new DateTimeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View res = inflater.inflate(R.layout.fragment_date_time, container, false);
        LinearLayout layout = (LinearLayout) res.findViewById(R.id.datetime_layout);
        layout.setOnClickListener(this);
        return res;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDateTimeSetListener) {
            mListener = (OnDateTimeSetListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDateTimeSetListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onClick(View view)
    {
        DialogFragment timepicker = new TimePickerFragment();
        timepicker.show(getFragmentManager(), "datePicker");
        DialogFragment datepicker = new DatePickerFragment();
        datepicker.show(getFragmentManager(), "datePicker");
    }

    public void updateDateTime(Calendar datetime)
    {
        buildDate(datetime);
        mListener.onDateTimeSet(datetime);
    }

    private void buildDate(Calendar datetime)
    {
        TextView dateText = (TextView) getView().findViewById(R.id.datetime);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        dateText.setText(fmt.format(datetime.getTime()));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDateTimeSetListener {
        void onDateTimeSet(Calendar datetime);
    }
}
