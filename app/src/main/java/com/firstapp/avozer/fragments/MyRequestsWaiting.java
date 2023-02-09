package com.firstapp.avozer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.avozer.AdapterClass;
import com.firstapp.avozer.Deal;
import com.firstapp.avozer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyRequestsWaiting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRequestsWaiting extends Fragment {


    RecyclerView recyclerView;

    AdapterClass adapterClass;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Deal> dealsList = new ArrayList<Deal>();


    public MyRequestsWaiting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRequestsWaiting.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRequestsWaiting newInstance(String param1, String param2) {
        MyRequestsWaiting fragment = new MyRequestsWaiting();
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
        View view = inflater.inflate(R.layout.fragment_my_requests_waiting, container, false);

        getDeals();

        super.onViewCreated(view, savedInstanceState);
//        dataInitialize();
        ///////
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        list = new ArrayList<Deal>();
        adapterClass = new AdapterClass(getActivity(), dealsList);
        recyclerView.setAdapter(adapterClass);


        adapterClass.notifyDataSetChanged();

        return view;
    }

    private void getDeals() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String currentUid = user.getUid();
        String dealUid = "";
        int listSize = ProfileFragment.list.size();
        boolean helperIsFound;

        for (int i = 0; i < listSize - 1; i++) {
            helperIsFound = ProfileFragment.list.get(i).helperIsFound;
            dealUid = ProfileFragment.list.get(i).clientUid;

            if (currentUid.equals(dealUid) && !helperIsFound) {
                dealsList.add(ProfileFragment.list.get(i));
            }
        }

        // dummy element to prevent exception
        if (dealsList.size() == 0) {
            Toast.makeText(getActivity(),
                    "There are any open requests at the moment",
                    Toast.LENGTH_SHORT).show();
        }
    }
}