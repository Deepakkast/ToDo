package com.example.deepak.localto_doapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "todoapp";
    EditText subject;
    TextView date1;
    EditText datepicker;
    //EditText dob;
    //EditText cal1;
    //Calendar currentdate;
    // int Year_x, day_x, month_x;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        subject = findViewById( R.id.subject );
        date1 = findViewById( R.id.tvday1 );
        Date d1 = Calendar.getInstance().getTime();
        SimpleDateFormat s2 = new SimpleDateFormat( "dd/MMM/yyyy" );
        String formattedDate = s2.format( d1 );
        final TextView date = findViewById( R.id.tvdate );
        date.setText( formattedDate );


        datepicker = findViewById( R.id.et_date );


        Date d2 = Calendar.getInstance().getTime();
        SimpleDateFormat s3 = new SimpleDateFormat( "EEEE" );
        String s4 = s3.format( d2 );
        TextView day = findViewById( R.id.tvday1 );
        day.setText( s4 );

        ImageButton ib1 = findViewById( R.id.ibcal );



        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal=Calendar.getInstance();
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                // int month=cal.get(Calendar.MONTH);
                //int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dp=new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        //String update_date=day + "/" + (month+1) + "/" + year;
                        //
                        // date1.setText( update_date );
                        //System.out.println(update_date);
                    }
                },year,month,day);


                dp.show();
            }
        });
        ib1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR );
                int month = cal.get( Calendar.MONTH );
                int day = cal.get( Calendar.DAY_OF_MONTH );


                DatePickerDialog dp = new DatePickerDialog( SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        datepicker.setText( i2 + "/" + (i1 + 1) + "/" + i );
                    }
                }, year, month, day );
                dp.show();
            }
        } );


        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab_check );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    POST();
                    Toast.makeText( SecondActivity.this, "data stored", Toast.LENGTH_SHORT ).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );


        //Intent in=new Intent(SecondActivity.this,FirstActivity.class);
        //startActivity(in);


    }


    //  public class POST extends AsyncTask<Void, Void, Void> {


    public void POST() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {

                    String BASE_URL = "https://demo-todo-rest.herokuapp.com";
                    URL url = new URL( BASE_URL );
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod( "POST" );
                    conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
                    System.out.println( conn.getURL() );
                    String token=getToken();
                    conn.setRequestProperty("Authorization",token);

                    String data2 = "{\"subject\":\"" + subject.getText().toString() + "\" ,\"date\":\"" + datepicker.getText().toString() + "\"}";
                    //String data1 = "{\"subject\":\"" + subject.getText().toString() + "\",\"date\":\"" + datepicker.getText().toString() + "}";
                    byte[] bobj = data2.getBytes( StandardCharsets.UTF_8 );
                    OutputStream os = new DataOutputStream( conn.getOutputStream() );
                    os.write( bobj );
                    Log.d( "TAg", "run: " );

                    //int responseCode = conn.getResponseCode();
                   // System.out.println( "your Responsecode is:" + responseCode );
                    System.out.println( "Your enter url is:" + url );


                    BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
                    Log.d( TAG,"Buffered Reader object created");
                    String container;
                    StringBuffer response = new StringBuffer();
                    //if (responseCode == 200) {
                    while ((container = br.readLine()) != null) {
                        response.append( container );
                        Log.d( "TAG", "runing in buffer: " );
                    }

                    System.out.println( response );
                    Log.d( TAG, "runing with responce: " + response );
//                    } else {
//                        System.out.println("connection failed");
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } ).start();


        Intent in = new Intent( SecondActivity.this, FirstActivity.class );

        startActivity( in );

    }


    public String getToken() {

        SharedPreferences sharedPreferences=getSharedPreferences( "MyPrefs",MODE_PRIVATE );
        String token=sharedPreferences.getString( "tokenkey","" );
        Log.d(TAG, "onpost: "+sharedPreferences.getString("tokenkey",""));


        return token;

    }
}

 /*  @Override
        protected Void doInBackground(Void... voids) {
            String BASE_URL = "http://trail-todo.herokuapp.com/";


            URL url = null;
            try {
                url = new URL( BASE_URL );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
                conn.getURL();
                System.out.println( conn.getURL() );
                conn.setDoOutput( true );

                String data2 = "{\"subject\":\"" + subject.getText().toString() + "\" ,\"date\":\"" + datepicker.getText().toString() + "\"}";
                //String data1 = "{\"subject\":\"" + subject.getText().toString() + "\",\"date\":\"" + datepicker.getText().toString() + "}";
                byte[] bobj = data2.getBytes( StandardCharsets.UTF_8 );
                OutputStream os = new DataOutputStream( conn.getOutputStream() );
                os.write( bobj );
                Log.d( "TAg", "run: " );

                int responseCode = conn.getResponseCode();
                System.out.println( "your Responsecode is:" + responseCode );
                System.out.println( "Your enter url is:" + url );
                BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
                String container;
                StringBuffer response = new StringBuffer();
                //if (responseCode == 200) {
                while ((container = br.readLine()) != null) {
                    response.append( container );
                    Log.d( "TAG", "run: " );
                }

                System.out.println( response );
                Log.d( TAG, "run: " + response );
//                    } else {
//                       System.out.println("connection failed");
//                    }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/





               /* try {
                    if (subject.getText().toString() != null || date.getText().toString() != null) {
                        Intent in = new Intent(SecondActivity.this, FirstActivity.class);

                        Log.d("TAG", "onClick: " + subject.getText().toString() + "" + date2.getText().toString());
                        final DatabaseHandler databaseHandler = new DatabaseHandler(SecondActivity.this);
                        //databaseHandler.addtodo(new ToDoRetro(subject.getText().toString(), date2.getText().toString()));
                        startActivity(in);
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }*/











