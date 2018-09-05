package com.example.deepak.localto_doapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class RetroRecycleAdapter extends RecyclerView.Adapter<RetroRecycleAdapter.MyViewHolder>
{
    private Context context;
    private List<TaskDTO> mylist;

    public RetroRecycleAdapter(Context context, List<TaskDTO> mylist) {
        this.context = context;
        this.mylist = mylist;
    }


    @Override
    public RetroRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_row,parent,false);



        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetroRecycleAdapter.MyViewHolder holder, int position) {
            holder.nameDisplay.setText(mylist.get(position).getSubject());
            holder.classDisplay.setText(mylist.get(position).getDate());
            notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameDisplay, classDisplay;



        MyViewHolder(View v) {
            super(v);
            nameDisplay = v.findViewById(R.id.name1);
            classDisplay = v.findViewById(R.id.date);


        }


    }

}
