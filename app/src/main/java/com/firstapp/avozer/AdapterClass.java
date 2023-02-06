package com.firstapp.avozer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    Context context;

    View publicView;

    ArrayList<Deal> list1;

    Deal dealsToShowFromDB;

    public static int selectedPosition;

    public AdapterClass(Context context, ArrayList<Deal> list1) {
        this.context = context;
        this.list1 = list1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView jobType = view.findViewById(R.id.jobTypeCV);
                TextView comments = view.findViewById(R.id.commentCV);
                System.out.println(jobType.getText().toString());
                System.out.println(comments.getText().toString());
                Bundle bundle = new Bundle();
                bundle.putString("jobType", jobType.getText().toString());
                bundle.putString("comments", comments.getText().toString());
                FragmentManager manager = getActivity().getSupportFragmentManager();
                Fragment fragment = manager.findFragmentById(
                        R.id.action_findRequestFormFragment_to_dealDetailsFragment);
                fragment.setArguments(bundle);
                Navigation.findNavController(view).navigate(
                        R.id.action_findRequestFormFragment_to_dealDetailsFragment);
            }
        };

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent ,false);
        //working on the details screen from this and below !!!
        publicView = view;
        CardView cardView = view.findViewById(R.id.cardView);
        cardView.setOnClickListener(clickListener);
        return new MyViewHolder(view);
    }

    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        dealsToShowFromDB = list1.get(position);
        holder.jobType.setText(dealsToShowFromDB.type);
        holder.city.setText(dealsToShowFromDB.city);
        holder.when.setText(dealsToShowFromDB.whenNeedHelp);
        holder.comments.setText(dealsToShowFromDB.comments);


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView jobType, city, when, comments;

        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            jobType = itemView.findViewById(R.id.jobTypeCV);
            city = itemView.findViewById(R.id.cityNameCV);
            when = itemView.findViewById(R.id.whenCV);
            comments = itemView.findViewById(R.id.commentCV);



        }
    }


}
