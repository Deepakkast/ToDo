package com.example.deepak.localto_doapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.deepak.localto_doapp.Login.TAG;

class ServerResponseAdapter extends RecyclerView.Adapter<ServerResponseAdapter.UserViewHolder> {

    private List<TaskDTO> users;
    private Context context;

    ServerResponseAdapter(Context context, List<TaskDTO> users) {
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
        if (context instanceof FirstActivity) {
            if (users.get(position).getDone().equals("false")) {
                holder.nameDisplay.setText(Html.fromHtml(" " + users.get(position).getSubject()));
                holder.classDisplay.setText(Html.fromHtml("" + users.get(position).getDate()));

                holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        Log.d("TAG", "onCheckedChanged: " + b);
                        if (b) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                            builder.setTitle("Do you want to close").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DatabaseHandler db = new DatabaseHandler(context);
                                    db.delete(users.get(position).getSubject());
                                    db.add_check(users.get(position).getSubject(), users.get(position).getDate());
                                    users.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, users.size());

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
            } else if (context instanceof ThirdActivity) {
                if (users.get(position).getDone().equals("true")) {
                    holder.nameDisplay.setText(Html.fromHtml(" " + users.get(position).getSubject()));
                    holder.classDisplay.setText(Html.fromHtml("" + users.get(position).getDate()));
                    holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            Log.d("TAG", "onCheckedChanged: " + b);
                            if (b) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                                builder.setTitle("Do you want to close").setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DatabaseHandler db = new DatabaseHandler(context);
                                        db.delete(users.get(position).getSubject());
                                        db.add_check(users.get(position).getSubject(), users.get(position).getDate());
                                        users.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, users.size());

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

            }
            holder.ib.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder=new AlertDialog.Builder(context,android.R.style.Theme_Material_Dialog_Alert);
                    builder.setTitle( "Do you want to Delete" ).setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new Delete(users.get( holder.getAdapterPosition()).getTask_id()).execute();

                            Log.d( TAG,"hyhtb"+users.get(position));

                            users.remove( holder.getAdapterPosition() );//remove item with adapter  from list
                            notifyItemRemoved( holder.getAdapterPosition());






                        }
                    } ).setNegativeButton( "no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    } );
                    builder.show();



                }
            } );



           holder.ib_updateing.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent( view.getContext(),Fourth.class );
                    String idt = Integer.toString( users.get( position ).getTask_id() );
                    intent.putExtra( "id",idt);
                    context.startActivity(intent);

                }
            } );

        }
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameDisplay, classDisplay;
        CheckBox cb;
        ImageButton ib;
        ImageButton ib_updateing;



        UserViewHolder(View v) {
            super(v);
            nameDisplay = v.findViewById(R.id.name1);
            classDisplay = v.findViewById(R.id.date);
            cb = v.findViewById(R.id.checkbox_meat);
            ib=v.findViewById( R.id.ib_delete );
            ib_updateing=v.findViewById( R.id.ib_update1 );

        }


    }

    @Override
    public int getItemCount() {

                                     return users.size();
                              }


    public class Delete extends AsyncTask<Void,Void,Void>
    { int id;


        public Delete(int auto_increment_id)
        {

            this.id=auto_increment_id;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url="https://demo-todo-rest.herokuapp.com";
            try {

                URL url1=new URL(String.format( "%s/%d/",url,id));
                HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod( "DELETE" );
                connection.setRequestProperty( "content-type","application/json; charset=utf-8" );
                System.out.println(connection.getURL());
                System.out.println("Your url is :"+url1);
               // int responcecode=connection.getResponseCode();
                //System.out.println("your responce code is:"+responcecode);
                String token=getToken();
                connection.setRequestProperty( "Authorization",token);
                BufferedReader br=new BufferedReader( new InputStreamReader(connection.getInputStream()) );
                Log.d( TAG,"you are in buffer" );
                String container;
                StringBuffer buffer=new StringBuffer(  );
                Log.d( TAG,"inputstream" );
                while ((container=br.readLine())!=null)
                {
                    buffer.append( container );
                }
                System.out.println(buffer);
                } catch (Exception e) {
                e.printStackTrace();
            }




            return null;
        }
        public String getToken()
        {
            SharedPreferences sharedPreferences=context.getSharedPreferences( "MyPrefs",MODE_PRIVATE );
            String token=sharedPreferences.getString( "tokenkey","");
            Log.d(TAG, "onDelete: "+sharedPreferences.getString("tokenkey",""));


            return token;
        }
    }
}
