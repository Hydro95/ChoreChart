package net.sudormrf.chorechart;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnDateTimeSetListener mListener;

    public DateTimeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateTimeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateTimeFragment newInstance(String param1, String param2) {
        DateTimeFragment fragment = new DateTimeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        String newDate = String.format("%d-%d-%d %d:%d", datetime.get(Calendar.YEAR),
                datetime.get(Calendar.MONTH),
                datetime.get(Calendar.DAY_OF_MONTH),
                datetime.get(Calendar.HOUR_OF_DAY),
                datetime.get(Calendar.MINUTE));
        dateText.setText(newDate);
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
