package com.firstapp.avozer.fragments;

import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.firstapp.avozer.Deal;
import com.firstapp.avozer.Person;
import com.firstapp.avozer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link placeRequestFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class placeRequestFormFragment extends Fragment {

    // vars for time picker
    int hour, minute;

    // Deal data
    public String dealId;
    public String dealType;
    public String clientUid;
    public String helperUid;
    public String clientCity;

    public String timeCreated;
    public String whenNeedHelp;

    public String comment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public placeRequestFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment placeRequestFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static placeRequestFormFragment newInstance(String param1, String param2) {
        placeRequestFormFragment fragment = new placeRequestFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_place_request_form, container, false);;

        // Get current user city
        setCurrentClientCity(view);

        //Drop-down menu (select type: babysitter, dog walker, cleaner)
        String[] ways_to_help = getResources().getStringArray(R.array.ways_to_help);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, ways_to_help);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.what_do_you_need_list);
        autoCompleteTextView.setAdapter(arrayAdapter);
        // end Drop-down menu


        //Time picker
        Button timeButton = view.findViewById(R.id.time_picker_btn);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(timeButton, view);
            }
        });
        //End time picker


        //Save and publish request
        Button saveAndPublishBtn = view.findViewById(R.id.save_request_btn);
        saveAndPublishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRequest(view);
            }
        });


        return view;
    }

    private void saveRequest(View view) {
        /* currentTimeMillis is unique value so I can use it
         as request ID (because I did not find good method to get last child
         in firebase realtime database).
         Also this value will be used to store time creating request
         */
        dealId = String.valueOf(System.currentTimeMillis());


        // get type of deal
        TextView typeTextView = view.findViewById(R.id.what_do_you_need_list);
        dealType = typeTextView.getText().toString();


        // Default value of helper is * (empty). Will be updated when deal will be
        // accepted by any helper
        helperUid = "*";


        // Time when offer was created
        long milliseconds = System.currentTimeMillis();
        DateFormat simple = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm:ss");
        Date date = new Date(milliseconds);
        timeCreated = simple.format(date);


        // Time when need a help
        DatePicker dealDate = view.findViewById(R.id.datePicker);
        String day, month, year;
        day = String.valueOf(dealDate.getDayOfMonth());
        month = String.valueOf(dealDate.getMonth() + 1);
        year = String.valueOf(dealDate.getYear());

        whenNeedHelp = day + "/" + month + "/" + year + " " + hour + ":" + minute;


        // Get user comment
        EditText userComment = view.findViewById(R.id.dealComment);
        comment = userComment.getText().toString();



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        Deal deal = new Deal(clientCity, dealId, dealType, clientUid, helperUid,
                timeCreated, whenNeedHelp,
                false, false, comment);
        DatabaseReference databaseReference = firebaseDatabase.getReference("deals").child(deal.dealID);

        databaseReference.setValue(deal);


    }

    public void setCurrentClientCity(View view)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        clientUid = user.getUid();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().
                getReference().child("users").child(clientUid);


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Person person = snapshot.getValue(Person.class);
                clientCity = person.city;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void pickTime(Button timeButton, View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        // Analog clock design of time picker
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                onTimeSetListener, hour, minute, true);


        // Spinner style design of time picker
//        int style = AlertDialog.THEME_HOLO_DARK;
//        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), style,
//                onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

}