package com.tca.rez.tcainternationalgames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Rez on 3/7/2018.
 */

public class DownloadGameData extends AsyncTask<String, String, String> {

    private ProgressDialog pd;
    Activity activity;
    String user;

    public DownloadGameData(Activity a, String user) {
        this.activity = a;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... strings) {

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

        GameLandingPage.tvName.setText(name);
        return "done";
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(activity, "Downloading",
                "Please wait while we download game data..");
    }

    @Override
    protected void onPostExecute(String msg) {
        pd.dismiss();

    }
}