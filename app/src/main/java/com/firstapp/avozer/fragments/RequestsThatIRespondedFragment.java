package com.firstapp.avozer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.firstapp.avozer.R;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * A simple {@link Fragment} subclass.gfdgdf
 * Use the {@link RequestsThatIRespondedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestsThatIRespondedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RequestsThatIRespondedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RequestsThatIRespondedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RequestsThatIRespondedFragment newInstance(String param1, String param2) {
        RequestsThatIRespondedFragment fragment = new RequestsThatIRespondedFragment();
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
        View view = inflater.inflate(R.layout.fragment_requests_that_i_responded, container, false);

        Button upcomingDealsBtn = view.findViewById(R.id.upcoming_deals_i_responded);
        Button recentDealsBtn = view.findViewById(R.id.recent_deals_i_responded);
        upcomingDealsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.
                        action_requestsThatIRespondedFragment_to_upcomingDealsResponded);
            }
        });


        recentDealsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.
                        action_requestsThatIRespondedFragment_to_recentDealsResponded);
            }
        });

        MaterialToolbar toolbar = view.findViewById(R.id.topAppBar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).
                        navigate(R.id.action_requestsThatIRespondedFragment_to_profileFragment);
            }
        });



        return view;
    }
}