package com.firstapp.avozer.fragments;

import static com.firstapp.avozer.AdapterClass.ARG1;
import static com.firstapp.avozer.AdapterClass.ARG2;
import static com.firstapp.avozer.AdapterClass.ARG3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.firstapp.avozer.AdapterClass;
import com.firstapp.avozer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DealDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealDetailsFragment extends Fragment {

    private String mTitle;
    private String mSummary;
    private String mDateAndTime;

    public DealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG1);
            mSummary = getArguments().getString(ARG2);
            mDateAndTime = getArguments().getString(ARG3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_details, container, false);
        TextView type = view.findViewById(R.id.work_type_detail_find);
        TextView comment = view.findViewById(R.id.textViewVersion);
        TextView whenNeed = view.findViewById(R.id.date_and_time_detail_find);

        // test
        type.setText(mTitle);
        comment.setText(mSummary);
        whenNeed.setText(mDateAndTime);

        //end test


        Button backToListButton = view.findViewById(R.id.buttonBackToList);
        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(
                        R.id.action_dealDetailsFragment_to_findRequestFormFragment);
            }
        });


        return view;
    }
}
