package com.tca.rez.tcainternationalgames;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GameLandingPage extends AppCompatActivity {
    TextView tvName;
    ListView lvCoutnries;
    String[] countries = {"Bahamas", "Brazil", "Dominican Republic", "Egypt", "Fiji", "Haiti", "India", "Ireland", "Jamaica", "Puerto Rico", "Russia", "United States of America"};
    String[] teachers = {"Radaszkiewicz", "Smith", "Jeschon", "Radaszkiewicz", "Smith", "Jeschon", "Radaszkiewicz", "Smith", "Jeschon", "Radaszkiewicz", "Smith", "Jeschon"};
    String[] students = {"John", "Paul", "Jen", "John", "Paul", "Jen", "John", "Paul", "Jen", "John", "Paul", "Jen"};
    String[] flags = {
            "https://internationalgames-mikerada6.c9users.io/Bahamas.gif",
            "https://internationalgames-mikerada6.c9users.io/Brazil.gif",
            "https://internationalgames-mikerada6.c9users.io/Dominican%20Republic.gif",
            "https://internationalgames-mikerada6.c9users.io/Egypt.gif",
            "https://internationalgames-mikerada6.c9users.io/Fiji.gif",
            "https://internationalgames-mikerada6.c9users.io/Haiti.gif",
            "https://internationalgames-mikerada6.c9users.io/India.gif",
            "https://internationalgames-mikerada6.c9users.io/Ireland.gif",
            "https://internationalgames-mikerada6.c9users.io/Jamaica.gif",
            "https://internationalgames-mikerada6.c9users.io/Puerto%20Rico.gif",
            "https://internationalgames-mikerada6.c9users.io/russia.gif",
            "https://internationalgames-mikerada6.c9users.io/USA.gif"
    };
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_landing_page);

        Bundle extras = getIntent().getExtras();
        user = "help";

        if (extras != null) {
            user = extras.getString("com.tca.rez.tcainternationalgames.user");
            // and get whatever type user account id is
        }
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
            ps.print("user=" + user);

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
        tvName = (TextView) findViewById(R.id.tvName);
        tvName.setText(name);



        lvCoutnries = (ListView) findViewById(R.id.lvCountries);
        CountryAdapter countryAdapter = new CountryAdapter(this, countries, teachers, students, flags);
        lvCoutnries.setAdapter(countryAdapter);

        lvCoutnries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent addScoreActivity = new Intent(getApplicationContext(), AddScoreTime.class);
                addScoreActivity.putExtra("com.tca.rez.tcainternationalgames.country", countries[i]);
                addScoreActivity.putExtra("com.tca.rez.tcainternationalgames.flag", flags[i]);
                addScoreActivity.putExtra("com.tca.rez.tcainternationalgames.user", user+""); //Optional parameters
                startActivity(addScoreActivity);
            }
        });
    }
}
