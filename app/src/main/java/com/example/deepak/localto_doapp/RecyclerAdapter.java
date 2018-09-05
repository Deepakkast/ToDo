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

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.UserViewHolder> {

    private List<ToDoRetro> users;
    private Context context;

    RecyclerAdapter(Context context, List<ToDoRetro> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.nameDisplay.setText(Html.fromHtml(" " + users.get(position).getSubject()));
        holder.classDisplay.setText(Html.fromHtml("" + users.get(position).getDate()));
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("TAG", "onCheckedChanged: " + b);
             if(b){
                 AlertDialog.Builder builder=new AlertDialog.Builder(context,android.R.style.Theme_Material_Dialog_Alert);
                 builder.setTitle("Do you want to close").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         DatabaseHandler db=new DatabaseHandler(context);
                         db.delete(users.get(position).getSubject());
                         db.add_check(users.get(position).getSubject(), users.get(position).getDate());
                         users.remove(position);
                         notifyItemRemoved(position);
                         notifyItemRangeChanged(position,users.size());

                     }
                 }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         holder.cb.setChecked(false);
                     }
                 }).show();


             }

            }

        });
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameDisplay, classDisplay;
        CheckBox cb;


        UserViewHolder(View v) {
            super(v);
            nameDisplay = v.findViewById(R.id.name1);
            classDisplay = v.findViewById(R.id.date);
            //cb = v.findViewById(R.id.checkbox_meat);


        }


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

/*    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View view) {

          //  super();
        }
    }*/
}
