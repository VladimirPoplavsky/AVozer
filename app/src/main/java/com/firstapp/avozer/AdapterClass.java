package com.firstapp.avozer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    Context context;


    ArrayList<Deal> list1;

    public AdapterClass(Context context, ArrayList<Deal> list1) {
        this.context = context;
        this.list1 = list1;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Deal dealsToShowFromDB = list1.get(position);
        holder.jobType.setText(dealsToShowFromDB.type);
        holder.city.setText(dealsToShowFromDB.city);
        holder.when.setText(dealsToShowFromDB.whenNeedHelp);
        holder.comments.setText(dealsToShowFromDB.comments);

        if (dealsToShowFromDB.isExpanded()){
            holder.expandedView.setVisibility(View.VISIBLE);
        }
        else {
            holder.expandedView.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView jobType, city, when, comments;
        View expandedView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            jobType = itemView.findViewById(R.id.jobTypeCV);
            city = itemView.findViewById(R.id.cityNameCV);
            when = itemView.findViewById(R.id.whenCV);
            comments = itemView.findViewById(R.id.commentCV);
            expandedView = itemView.findViewById(R.id.expandButton);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Deal deal = list1.get(getAdapterPosition());
                    deal.setExpanded(!deal.isExpanded());



                }
            });

        }
    }


}
