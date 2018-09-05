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

public class Login extends AppCompatActivity {

    public static String TAG="todoapp";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String tokenkey="token";
    public static final String name = "nameKey";
    public static final String pass="passkey";
     EditText username;
     EditText password;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.login );
        //setContentView( R.layout.signup );
        final Button  login;
        final  Button signup;


        username=findViewById( R.id.editText1 );
        password=findViewById( R.id.editText2 );
        login=findViewById( R.id.btn_login );
        signup=findViewById( R.id.signup);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

         signup.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent in=new Intent( Login.this,Signup.class );
                 startActivity( in );

             }
         } );

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nm = username.getText().toString();
                String pas = password.getText().toString();

                if(nm.length()<=0||pas.length()<=0)
                {
                    Toast.makeText( getApplicationContext(),"plase enter",Toast.LENGTH_SHORT ).show();
                } else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.remove( "tokenkey" );
                    editor.apply();
                    editor.putString( name, nm );
                    editor.putString( pass, pas );
                    editor.commit();
                    new Post().execute();

                }

                // if(nm.equals( "admin") && pas.equals( "password" ))
                //{

                //   Toast.makeText( getApplicationContext(),"login fail",Toast.LENGTH_SHORT ).show();;
                //Log.d(TAG, "onClick: " + sharedpreferences.getString(name, ""));
                //Intent in=new Intent( Login.this,FirstActivity.class );
                //startActivity( in );
                //new Post().execute();


            // else  {
            //    Toast.makeText( getApplicationContext(),"login fail",Toast.LENGTH_SHORT ).show();
            // }








          }
        } );
        }
    public class Post extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                String BASE_URL = "https://demo-todo-rest.herokuapp.com/login/";
                URL url = new URL( BASE_URL );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod( "POST" );
                conn.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
                conn.getURL();
                System.out.println( conn.getURL() );
                conn.setDoOutput( true );

                String data2 = "{\"username\":\"" + username.getText().toString() + "\" ,\"password\":\"" + password.getText().toString() + "\"}";
                //String data1 = "{\"subject\":\"" + subject.getText().toString() + "\",\"date\":\"" + datepicker.getText().toString() + "}";
                byte[] store = data2.getBytes( StandardCharsets.UTF_8 );
                OutputStream os = new DataOutputStream( conn.getOutputStream() );
                os.write( store );
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


                System.out.println("data is"+response);
                Reslogin reslogin=new Gson().fromJson(response.toString(),Reslogin.class);




                System.out.println(reslogin.toString());
                System.out.println(reslogin.getToken());
                System.out.println(reslogin.getName());
                System.out.println(reslogin.getPass());


               // else {
                  //  Toast.makeText( getApplicationContext(),"login fail",Toast.LENGTH_SHORT ).show();

                //}



                SharedPreferences sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString( "tokenkey" ,reslogin.getToken());
                editor.apply();
                Log.d(TAG, "doInBackground: "+sharedPreferences.getString("tokenkey",""));

                if(reslogin.getToken()!=null)
                {
//                    Toast.makeText(Login.this, "Loged in.............", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: " + sharedpreferences.getString(name, ""));
                    // Intent in=new Intent( Login.this,FirstActivity.class );
                    //  startActivity( in );

                    Intent navigateToNextActivity = new Intent();
                    navigateToNextActivity.setClass( Login.this, FirstActivity.class );
                    navigateToNextActivity.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity( navigateToNextActivity );

                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread( new Runnable() {
                    @Override
                    public void run() {


                        //Toast.makeText( getApplicationContext(),"login fail",Toast.LENGTH_SHORT ).show();

                    }
                } );


            }return null;
        }
        }
    }


