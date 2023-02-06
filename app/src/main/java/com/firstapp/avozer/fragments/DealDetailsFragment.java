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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DealDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DealDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DealDetailsFragment newInstance(String param1, String param2) {
        DealDetailsFragment fragment = new DealDetailsFragment();
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
        View view =inflater.inflate(R.layout.fragment_deal_details, container, false);
        TextView type = view.findViewById(R.id.work_type_detail_find);

        // test
        type.setText(ProfileFragment.list.get(AdapterClass.selectedPosition).type);

        //end test


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