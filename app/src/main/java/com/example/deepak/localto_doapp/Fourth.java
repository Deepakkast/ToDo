package com.example.deepak.localto_doapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.deepak.localto_doapp.Login.MyPREFERENCES;
import static com.example.deepak.localto_doapp.Login.TAG;
import static com.example.deepak.localto_doapp.Login.tokenkey;


public class Fourth extends AppCompatActivity {
    private static final String TAG = "Todoapp";
    public static final String tokenkey="token";
    EditText subject;
    EditText  dates;
    Button btn;
    EditText datepicker;
    ImageButton img;
    EditText auto_increment_id;
    Context context;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.fourthactivity );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        Intent intent = getIntent();
        String id = intent.getExtras().getString( "id" );
        Log.d( TAG, "onCreate: " + id );

        subject = findViewById( R.id.et_subject );
        dates = findViewById( R.id.et_date );
        //btn = findViewById( R.id.btn_update );
        //id = findViewById( R.id.et_id );
        //datepicker=findViewById( R.id.et_date);
        img = findViewById( R.id.ib_calander );
        Date d1 = Calendar.getInstance().getTime();
        SimpleDateFormat s2 = new SimpleDateFormat( "dd/MMM/yyyy" );
        String formattedDate = s2.format( d1 );
        final TextView date = findViewById( R.id.tvdate );
        date.setText( formattedDate );



        Date d2 = Calendar.getInstance().getTime();
        SimpleDateFormat s3 = new SimpleDateFormat( "EEEE" );
        String s4 = s3.format( d2 );
        TextView day = findViewById( R.id.tvday1 );
        day.setText( s4 );





        dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal=Calendar.getInstance();
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                // int month=cal.get(Calendar.MONTH);
                //int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dp=new DatePickerDialog(Fourth.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                       // String update_date=day + "/" + (month+1) + "/" + year;
                        //dates.setText( update_date );
                        //System.out.println(update_date);
                    }
                },year,month,day);


                dp.show();
            }
        });



        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cal=Calendar.getInstance();
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
               // int month=cal.get(Calendar.MONTH);
                //int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dp=new DatePickerDialog(Fourth.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        String update_date=day + "/" + (month+1) + "/" + year;
                        dates.setText( update_date );
                        //System.out.println(update_date);
                    }
                },year,month,day);


                dp.show();
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById( R.id.fab_check );


        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sub = subject.getText().toString();
                String dt = dates.getText().toString();
                Log.d( TAG,"date is" );
                Log.d(TAG,dates.getText().toString());

                Update details = new Update( sub, dt );
                //JsonArray js = new JsonArray();
                //JsonObject jProduct = new JsonObject();
try {
                JSONArray jProducts = new JSONArray();
                JSONObject jProduct1 = new JSONObject(  );
                String json = details.toString();
//                json = json.replace( "////", "" );

                    jProduct1 = new JSONObject(json);


                    Log.d(TAG,"put request" );
                    jProduct1.put( "subject", details.subject );
                    jProduct1.put( "date", details.date );
                    jProducts.put( jProduct1 );

                    String jsonData = jProduct1.toString();

                    Log.d( TAG, "jsondata " +jsonData.toString());

                    new PUT().execute(jProduct1.toString());


                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent in=new Intent( getApplicationContext(),FirstActivity.class );
                startActivity( in );
            }

        } );
    }
         class PUT extends AsyncTask<String, Void, Void> {


             Intent intent = getIntent();
             String id = intent.getExtras().getString("id");
             @Override
             protected Void doInBackground(String... params) {



                 try {
                     //String jsonData = params[0];
                     String jsonData = params[0]; // URL to call
                     //String jsonDat  = params[1];//data to post
                     String BASE_URl = "https://demo-todo-rest.herokuapp.com";
                     Log.d( TAG, "doInBackground first: " );
                     URL url = new URL( String.format( "%s/%s/", BASE_URl,id ) );

                     HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                     //conn.getResponseCode();
                     //int responce=conn.getResponseCode();
                     //System.out.println("responec code is"+responce);
                     conn.setRequestMethod( "PUT" );

                     //conn.setDoInput( true );
                     //conn.setDoOutput(true);
                     conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
                     String token=getToken();
                     conn.setRequestProperty( "Authorization",token);
                     Log.d( TAG,"token is:" );

                     System.out.println( "Your enter url is" + url );
                     OutputStream os = conn.getOutputStream();
                     os.write( jsonData.getBytes());
                     Log.d(TAG, "doInBackground second :"+ token);

                     //receives data
                     InputStream is = conn.getInputStream();
                     String result = "";
                     int byteCharacter;
                     while ((byteCharacter = is.read()) != -1) {
                         result += (char) byteCharacter;

                     }
                     System.out.println("data is:"+result);

                     //is.close();
                     //os.close();
                     conn.disconnect();



                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                 return null;



                 //return null;
             }

             public String getToken()
             {
                 SharedPreferences sharedPreferences=getSharedPreferences( MyPREFERENCES,Context.MODE_PRIVATE );
                 String token=sharedPreferences.getString( "tokenkey","");
                 Log.d(TAG, "onPut: "+sharedPreferences.getString("tokenkey",""));


                 return token;
             }

         }
    }
















