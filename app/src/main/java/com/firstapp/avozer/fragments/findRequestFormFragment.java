package com.firstapp.avozer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.avozer.AdapterClass;
import com.firstapp.avozer.Deal;
import com.firstapp.avozer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link findRequestFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class findRequestFormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    AdapterClass adapterClass;


    private ArrayList<Deal> dealsList = new ArrayList<Deal>();


    public findRequestFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment findRequestFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static findRequestFormFragment newInstance(String param1, String param2) {
        findRequestFormFragment fragment = new findRequestFormFragment();
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
        View view = inflater.inflate(R.layout.fragment_find_request_form, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        dataInitialize();

        getDeals();

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        list = new ArrayList<Deal>();
        adapterClass = new AdapterClass(getActivity(), dealsList);
        recyclerView.setAdapter(adapterClass);


        adapterClass.notifyDataSetChanged();
    }

    private void getDeals() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        String currentUid = user.getUid();
        String dealUid = "";
        String myDate = "";
        SimpleDateFormat sdf = null;
        Date d = null;
        long currentTimeMillis = System.currentTimeMillis();
        long dealTimeMillis = 0;
        int listSize = ProfileFragment.list.size();
        Deal tempDeal;
        boolean helperIsFound;

        for (int i = 0; i < listSize - 1; i++) {

            helperIsFound = ProfileFragment.list.get(i).helperIsFound;

            if(!helperIsFound) {
                dealUid = ProfileFragment.list.get(i).clientUid;
                myDate = ProfileFragment.list.get(i).whenNeedHelp;
                sdf = new SimpleDateFormat("d/M/y H:m");
                try {
                    d = sdf.parse(myDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                assert d != null;
                dealTimeMillis = d.getTime();

                if (!(currentUid.equals(dealUid))
                        && currentTimeMillis < dealTimeMillis) {
                    dealsList.add(ProfileFragment.list.get(i));
                }
            }
        }

        // dummy element to prevent exception
        if(dealsList.size() == 0){
            Toast.makeText(getActivity(), "There are any open requests at the moment",Toast.LENGTH_SHORT).show();

        }
    }
}


//        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
////                    list.add(dataSnapshot.getValue(Deal.class));
////                }
//                list.add(snapshot.getValue(Deal.class));
//                adapterClass.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });


//    private void dataInitialize() {
//        recyclerView = getView().findViewById(R.id.findRequestFormFragment);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("deals").child("dealID");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        list = new ArrayList<>();
//        adapterClass = new AdapterClass(getActivity(), list);
//        recyclerView.setAdapter(adapterClass);
//
//        ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    DealsToShowFromDB dealsToShowFromDB = dataSnapshot.getValue(DealsToShowFromDB.class);
//                    list.add(dealsToShowFromDB);
//                }
//                adapterClass.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {}
//        });
//    }
//
//
//      private void dataInitialize() {
//        recyclerView =recyclerView.findViewById(R.id.findRequestFormFragment);
//      databaseReference= FirebaseDatabase.getInstance().getReference("Deals/type");
//    recyclerView.setHasFixedSize(true);
//    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//     list = new ArrayList<>();
//     adapterClass = new AdapterClass(getActivity(),list);
//    recyclerView.setAdapter(adapterClass);
//
//
//       ValueEventListener valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
//         @Override
//       public void onDataChange(@NonNull DataSnapshot snapshot) {
//         for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//
//           DealsToShowFromDB dealsToShowFromDB = dataSnapshot.getValue(DealsToShowFromDB.class);
//         list.add(dealsToShowFromDB);
//
//
//     }
//     adapterClass.notifyDataSetChanged();
//
//    }
//
//     @Override
//    public void onCancelled(@NonNull DatabaseError error) {}
//
//
//       }
//
//     }