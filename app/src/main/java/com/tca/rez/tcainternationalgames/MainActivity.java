package com.tca.rez.tcainternationalgames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    EditText userName, pass;
    String user;
    String Pass;
    TextView content;
    int count;
    ProgressDialog progressDialog;
    Activity current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy( policy );
            //your codes here

        }
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        current = this;
        userName = (EditText) findViewById( R.id.username );
        pass = (EditText) findViewById( R.id.password );
         content = (TextView) findViewById( R.id.content );
        final Button login = (Button) findViewById( R.id.butSubmit );
        count = 0;
        login.setOnClickListener( new Button.OnClickListener() {
            public void onClick(View view) {
                Login login = new Login( userName.getText().toString(), pass.getText().toString(), current );
                login.execute();
            }
        } );
    }
}
