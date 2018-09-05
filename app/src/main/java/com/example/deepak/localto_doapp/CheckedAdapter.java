package com.example.deepak.localto_doapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

class CheckedAdapter extends RecyclerView.Adapter<CheckedAdapter.CheckedViewHolder> {

    private List<ToDoRetro> users;
    private Context context;

    CheckedAdapter(Context context, List<ToDoRetro> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public CheckedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new CheckedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckedViewHolder holder, final int position) {
        holder.nameDisplay.setText(Html.fromHtml("" + users.get(position).getSubject()));
        holder.classDisplay.setText(Html.fromHtml("" + users.get(position).getDate()));



    }

    static class CheckedViewHolder extends RecyclerView.ViewHolder {
        TextView nameDisplay, classDisplay;
        CheckBox checkBox;

        CheckedViewHolder(View v) {
            super(v);
            nameDisplay = v.findViewById(R.id.name1);
            classDisplay = v.findViewById(R.id.date);
            //checkBox = v.findViewById(R.id.checkbox_meat);
        }


    }

    @Override
    public int getItemCount() {

        return users.size();
    }
}
