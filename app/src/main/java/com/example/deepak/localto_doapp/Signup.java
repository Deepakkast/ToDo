package com.example.deepak.localto_doapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.example.deepak.localto_doapp.Login.MyPREFERENCES;
import static com.example.deepak.localto_doapp.Login.pass;
import static com.example.deepak.localto_doapp.Signup.TAG;
import static com.example.deepak.localto_doapp.Signup.name_signup;

public class Signup extends AppCompatActivity {

    public static String TAG = "todoapp";
    public static final String name_signup = "namekey_signup";
    public static final String password_signup = "password_signup";
    EditText username_signup;
    EditText passwordfor_signup;
    Button submit;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.signup );


        username_signup = findViewById( R.id.et_username );
        passwordfor_signup = findViewById( R.id.ed_password );
        submit = findViewById( R.id.btn_submit );

        //here we are passing a unique name identifier for prefrance and mode application
        sharedPreferences = getSharedPreferences( "MyPrefs", Context.MODE_PRIVATE );

        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nm = username_signup.getText().toString();
                String pass = passwordfor_signup.getText().toString();

//               here we need edittor object to make change
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString( name_signup, nm );
                editor.putString( password_signup, pass );
                editor.commit();



                new Post().execute();
                if(nm.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText( getApplicationContext(),"signup fail",Toast.LENGTH_SHORT ).show();

                }

                else {
                    Intent in = new Intent( Signup.this, Login.class );
                    startActivity( in );
                }

            }
        } );

    }


    public class Post extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String BASE_URL = "https://demo-todo-rest.herokuapp.com/signup/";
                URL url = new URL( BASE_URL );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
                conn.getURL();
                System.out.println( conn.getURL() );
               // conn.setDoOutput( true );

                String data2 = "{\"username\":\"" + username_signup.getText().toString() + "\" ,\"password\":\"" + passwordfor_signup.getText().toString() + "\"}";
                //String data1 = "{\"subject\":\"" + subject.getText().toString() + "\",\"date\":\"" + datepicker.getText().toString() + "}";
                byte[] bobj = data2.getBytes( StandardCharsets.UTF_8 );
                OutputStream os = new DataOutputStream( conn.getOutputStream() );
                os.write( bobj );
                Log.d( "TAg", "run: " );

                //int responseCode = conn.getResponseCode();
                //System.out.println( "your Responsecode is:" + responseCode );
                System.out.println( "Your enter url is:" + url );
                BufferedReader br = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
                String container;
                StringBuffer response = new StringBuffer();
                //if (responseCode == 200) {
                while ((container = br.readLine()) != null) {
                    response.append( container );
                    Log.d( "TAG", "run: " );
                }

                //System.out.println( response );
                //Log.d( TAG, "run: " + response );



//                    } else {
//                        System.out.println("connection failed");
//                    }


                System.out.println( "data is" + response );
                Ressignup ressignup = new Gson().fromJson( response.toString(), Ressignup.class );


                System.out.println( ressignup.toString() );
                System.out.println( ressignup.getToken());
                System.out.println( ressignup.getUsername() );
                System.out.println( ressignup.getPassword() );

                //if(reslogin.getToken()!=null)
                {
//                    Toast.makeText(Login.this, "Loged in.............", Toast.LENGTH_LONG).show();
                    //  Log.d(TAG, "onClick: " + sharedpreferences.getString(name, ""));
                    //Intent in=new Intent( Login.this,FirstActivity.class );
                    //startActivity( in );
                }
                // else {
                //  Toast.makeText( getApplicationContext(),"login fail",Toast.LENGTH_SHORT ).show();

                //}





                //SharedPreferences sharedPreferences = getSharedPreferences( MyPREFERENCES, Context.MODE_PRIVATE );
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString( "tokenkey", ressignup.getToken() );
                editor.apply();
                Log.d( TAG, "doInBackgroundin_signup: " + sharedPreferences.getString( "tokenkey", "" ) );

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {


                        //Toast.makeText( getApplicationContext(), "login fail", Toast.LENGTH_SHORT ).show();

                    }
                } );

            }return null;
        }
    }
}