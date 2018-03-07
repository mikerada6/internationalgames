package com.tca.rez.tcainternationalgames;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AddScoreTime extends AppCompatActivity {
    SeekBar spiritBar;
    TextView spirit;
    Button submit;
    double spiritScore;
    double gameScore;
    String country;
    TextView minute;
    TextView second;
    TextView milisecond;
    String user;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score_time);
        Intent in = getIntent();
        country = in.getStringExtra("com.tca.rez.tcainternationalgames.country");
        flag = in.getStringExtra("com.tca.rez.tcainternationalgames.flag");
        user = in.getStringExtra("com.tca.rez.tcainternationalgames.user");
        TextView tvCountry = (TextView) findViewById(R.id.tvCountryScoreTime);
        spirit = (TextView) findViewById(R.id.tvSpiritScoreAddTime);
        minute = (TextView) findViewById(R.id.tvMinuteAddTime);
        second = (TextView) findViewById(R.id.tvSecondAddTime);
        milisecond = (TextView) findViewById(R.id.tvMilliAddTime);
        tvCountry.setText(country);
        try {
            ImageView img = (ImageView) findViewById(R.id.imgFlag);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(flag).getContent());
            img.setImageBitmap(bitmap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        country = tvCountry.getText().toString();
        spiritBar = (SeekBar) findViewById(R.id.sbSpiritScoreAddTime);
        spiritBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                spiritScore = i / 2.0;
                spirit.setText(i / 2.0 + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        submit = (Button) findViewById(R.id.butSubmitTimeScore);
        final String finalCountry = country;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameScore = Integer.parseInt(minute.getText().toString()) * 60 + Integer.parseInt(second.getText().toString()) + Integer.parseInt(milisecond.getText().toString()) / 1000.0;
                String urlString = "https://internationalgames-mikerada6.c9users.io/addScore.php";
                try {
                    // open a connection to the site
                    URL url = new URL(urlString);
                    URLConnection con = url.openConnection();
                    // activate the output
                    con.setDoOutput(true);
                    PrintStream ps = new PrintStream(con.getOutputStream());
                    // send your parameters to your site
                    ps.print("gameID=" + 1);
                    ps.print("&teamID=" + 2);
                    ps.print("&core=" + gameScore);
                    ps.print("&spiritScore=" + spiritScore);
                    ps.print("&userID=" + 3);


                    // we have to get the input stream in order to actually send the request
                    con.getInputStream();

                    // close the print stream
                    ps.close();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        goBack();
    }

    public void goBack() {
        Intent i = new Intent(this, GameLandingPage.class);
        i.putExtra("com.tca.rez.tcainternationalgames.user", user + ""); //Optional parameters
        startActivity(i);
    }
}


