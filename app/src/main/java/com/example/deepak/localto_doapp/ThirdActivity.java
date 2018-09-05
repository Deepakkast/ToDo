package com.example.deepak.localto_doapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    TextView  date;
    List<TaskDTO> li;
    RetroRecycleAdapter adapter;
    ServerResponseAdapter responseAdapter1;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        date = findViewById(R.id.TVdateShow);
        Date d1= Calendar.getInstance().getTime();
        SimpleDateFormat s2=new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate=s2.format(d1);
        final TextView date=findViewById(R.id.TVdateShow);
        date.setText(formattedDate);

        SimpleDateFormat s3 = new SimpleDateFormat("EEEE");
        Date d2 = new Date();
        String dayOfTheWeek = s3.format(d2);
        TextView week = findViewById(R.id.Txtday);
        week.setText(dayOfTheWeek);

        //DatabaseHandler db = new DatabaseHandler(this);
        //List<ToDoRetro> li1 = db.displayChecked();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //final CheckedAdapter recyclerAdapter = new CheckedAdapter(this, li1);
        //recyclerView.setAdapter(recyclerAdapter);



        /*new Thread(new Runnable() {
            @Override
            public void run() {
                String BASE_URL="http://192.168.100.6:8000/done/";

                try {
                    URL url = new URL(BASE_URL);
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responsecode=con.getResponseCode();
                    System.out.println("response code is "+responsecode);

                    BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));

                    StringBuffer response= new StringBuffer();
                    String container="";
                    while((container=br.readLine())!=null)
                    {
                        response.append(container);
                    }
                    br.close();
                    System.out.println(response);
                    Wrapper task=new Gson().fromJson(response.toString(),Wrapper.class);
                    System.out.println(task.toString());
                    li.addAll(task.getList());
                    responseAdapter1.notifyDataSetChanged();
                    //Log.d(TAG, "onCreate: ");
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                //Log.d(TAG, "run: running");

            }
        }).start();

        recyclerView = findViewById(R.id.re);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        li = new ArrayList<TaskDTO>();


        final ServerResponseAdapter recyclerAdapter1 = new ServerResponseAdapter(this, li);
        recyclerView.setAdapter(recyclerAdapter1);

*/

    }
}


