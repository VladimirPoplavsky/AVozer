package com.firstapp.avozer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firstapp.avozer.AdapterClass;
import com.firstapp.avozer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link findRequestFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class findRequestFormFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterClass adapterClass;


    public findRequestFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ///////
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        list = new ArrayList<Deal>();
        adapterClass = new AdapterClass(getActivity(), ProfileFragment.list);
        recyclerView.setAdapter(adapterClass);


        adapterClass.notifyDataSetChanged();


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
