package com.firstapp.avozer.fragments;

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

    private static final String BUNDLE_TITLE = "jobType";
    private static final String BUNDLE_SUM = "comments";
    private static final String JOB_TYPE_ID = "work_type_detail_find";
    private static final String COMMENT_ID = "textViewVersion";
    private String mTitle;
    private String mSummary;

    public DealDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        String mTitle = bundle.getString(BUNDLE_TITLE, "");
        String mSummary = bundle.getString(BUNDLE_SUM, "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_details, container, false);

        TextView titleView = view.findViewById(R.id.work_type_detail_find);
        titleView.setText(mTitle);
        TextView summaryView = view.findViewById(R.id.textViewVersion);
        titleView.setText(mSummary);


        Button backToListButton = view.findViewById(R.id.buttonBackToList);
        backToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dealDetailsFragment_to_findRequestFormFragment);


            }
        });


        return view;
    }
}
