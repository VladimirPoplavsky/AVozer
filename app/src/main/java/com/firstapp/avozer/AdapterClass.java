package com.firstapp.avozer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    public static final String ARG1 = "jobeType";
    public static final String ARG2 = "comment";
    public static final String ARG3 = "dateAndTime";
    public static final String ARG4 = "city";
    public static String ARG5 = "clientFirstName";
    public static String ARG6 = "clientLirstName";
    public static String ARG7 = "clientPhone";
    public static String ARG8 = "clientEmail";
    public static String ARG9 = "dealId";

    //
    public String clientId;
    public String dealId;
    public String clientFirstName = "";
    public String clientLastName = "";
    public String clientPhone = "";
    public String clientEmail = "";

    Context context;

    View publicView;

    ArrayList<Deal> list1;

    Deal dealsToShowFromDB;

//    public static int selectedPosition;

    public AdapterClass(Context context, ArrayList<Deal> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        //working on the details screen from this and below !!!
        publicView = view;
        CardView cardView = view.findViewById(R.id.cardView);


        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView jobType = view.findViewById(R.id.jobTypeCV);
                TextView comments = view.findViewById(R.id.commentCV);
                TextView when = view.findViewById(R.id.whenCV);
                TextView city = view.findViewById(R.id.cityNameCV);
                TextView client = view.findViewById(R.id.clientIdCV);
                TextView dealId = view.findViewById(R.id.dealIdCV);
                clientId = client.getText().toString();
                getUserData();
//                System.out.println(jobType.getText().toString());
//                System.out.println(comments.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString(ARG1, jobType.getText().toString());
                bundle.putString(ARG2, comments.getText().toString());
                bundle.putString(ARG3, when.getText().toString());
                bundle.putString(ARG4, city.getText().toString());

                bundle.putString(ARG5, clientFirstName);
                bundle.putString(ARG6, clientLastName);
                bundle.putString(ARG7, clientPhone);
                bundle.putString(ARG8, clientEmail);

                bundle.putString(ARG9, dealId.getText().toString());


                try {
                    Navigation.findNavController(view).navigate(
                            R.id.action_findRequestFormFragment_to_dealDetailsFragment, bundle);
                } catch (RuntimeException e) {
                    try {
                        Navigation.findNavController(view).navigate(
                                R.id.action_myRequestsAccepted_to_dealDetailsFragment, bundle);
                    } catch (RuntimeException e1) {
                        try {
                            Navigation.findNavController(view).navigate(
                                    R.id.action_myRequestsWaiting_to_dealDetailsFragment, bundle);
                        } catch (RuntimeException e2) {
                            try {
                                Navigation.findNavController(view).navigate(
                                        R.id.action_upcomingDealsResponded_to_dealDetailsFragment, bundle);
                            } catch (RuntimeException e3) {
                                Navigation.findNavController(view).navigate(
                                        R.id.action_recentDealsResponded_to_dealDetailsFragment, bundle);
                            }
                        }

                    }
                }

            }
        };

        cardView.setOnClickListener(clickListener);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        dealsToShowFromDB = list1.get(position);
        holder.jobType.setText(dealsToShowFromDB.type);
        holder.city.setText(dealsToShowFromDB.city);
        holder.when.setText(dealsToShowFromDB.whenNeedHelp);
        holder.comments.setText(dealsToShowFromDB.comments);
        holder.clientId.setText(dealsToShowFromDB.clientUid);
        holder.dealId.setText(dealsToShowFromDB.dealID);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView jobType, city, when, comments;
        TextView clientId, dealId;

        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            jobType = itemView.findViewById(R.id.jobTypeCV);
            city = itemView.findViewById(R.id.cityNameCV);
            when = itemView.findViewById(R.id.whenCV);
            comments = itemView.findViewById(R.id.commentCV);
            clientId = itemView.findViewById(R.id.clientIdCV);
            dealId = itemView.findViewById(R.id.dealIdCV);
        }
    }

    public void getUserData() {

        // if there any open deals, so clientId = "",
        // because there is single dummy element in list to prevent exception
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child(clientId);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Person client = snapshot.getValue(Person.class);
                assert client != null;
                clientFirstName = client.firstName;
                clientLastName = client.lastName;
                clientPhone = client.phone;
                clientEmail = client.email;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
