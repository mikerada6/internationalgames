package com.tca.rez.tcainternationalgames;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main2Activity extends AppCompatActivity {
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Bundle extras = getIntent().getExtras();
        String uri = "https://internationalgames-mikerada6.c9users.io/getName.php";
        URL url;
        String name = "";
        try {
            url = new URL(uri);
            URLConnection con = null;
            con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("user=" + "mikerada6");

            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            // close the print stream
            ps.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp = "";
            String line = null;
            while ((line = in.readLine()) != null) {
                name += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user = (TextView) findViewById(R.id.lblUser);
        user.setText(name);
    }

}
