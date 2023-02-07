package com.firstapp.avozer.fragments;

import static com.firstapp.avozer.AdapterClass.ARG1;
import static com.firstapp.avozer.AdapterClass.ARG2;
import static com.firstapp.avozer.AdapterClass.ARG3;
import static com.firstapp.avozer.AdapterClass.ARG4;
import static com.firstapp.avozer.AdapterClass.ARG5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.firstapp.avozer.AdapterClass;
import com.firstapp.avozer.Deal;
import com.firstapp.avozer.Person;
import com.firstapp.avozer.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DealDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealDetailsFragment extends Fragment {

    private String mTitle;
    private String mSummary;
    private String mDateAndTime;
    private String mCity;
    private String mClientId;
    private String mClientFirstName;
    private String mClientLastName;
    private String mClientPhone;
    private String mClientEmail;

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
            mCity = getArguments().getString(ARG4);
            mClientId = getArguments().getString(ARG5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        getUserData();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().
                child("users").child(mClientId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Person client = snapshot.getValue(Person.class);
                mClientFirstName = client.firstName;
                mClientLastName = client.lastName;
                mClientPhone = client.phone;
                mClientEmail = client.email;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_details, container, false);
        TextView showType = view.findViewById(R.id.work_type_detail_find);
        TextView showComment = view.findViewById(R.id.textViewVersion);
        TextView showWhenNeed = view.findViewById(R.id.date_and_time_detail_find);
        TextView showCity = view.findViewById(R.id.city_detail_find);
        TextView clientFirstName = view.findViewById(R.id.first_name_detail_find);
        TextView clientLastName = view.findViewById(R.id.last_name_detail_find);
        TextView clientPhone = view.findViewById(R.id.phone_detail_find);
        TextView clientEmail = view.findViewById(R.id.email_detail_find);

        showType.setText(mTitle);
        showCity.setText(mCity);
        showComment.setText(mSummary);
        showWhenNeed.setText(mDateAndTime);
        clientFirstName.append(mClientFirstName);
        clientLastName.append(mClientLastName);
        clientPhone.append(mClientPhone);
        clientEmail.append(mClientEmail);


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

    public void getUserData(){

    }
}


