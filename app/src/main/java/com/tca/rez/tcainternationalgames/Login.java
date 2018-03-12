package com.tca.rez.tcainternationalgames;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

public class Login extends AsyncTask<String, String, String> {

    String userName;
    String password;
    Activity activity;
    private ProgressDialog pd;

    public Login(String userName, String password, Activity activity) {
        this.userName = userName;
        this.password = password;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show( activity, "Signing in",
                "Please wait while we are signing you in.." );
    }

    @Override
    protected void onPostExecute(String msg)
    {
        pd.dismiss();
        Context context = activity.getApplicationContext();
        CharSequence text = "Error loging in";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText( activity.getApplicationContext(), text, duration );
        toast.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            // open a connection to the site
            URL url = new URL( "https://internationalgames-mikerada6.c9users.io/password_verify.php" );
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput( true );
            PrintStream ps = new PrintStream( con.getOutputStream() );
            // send your parameters to your site
            ps.print( "user=" + userName );
            ps.print( "&password=" + password );

            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            // close the print stream
            ps.close();
            BufferedReader in = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
            String temp = "";
            String line = null;
            while ((line = in.readLine()) != null) {
                temp += line;
            }
            if (temp.equals( "yes" )) {

                //log in
                Intent i = new Intent( activity, GameLandingPage.class );
                i.putExtra( "com.tca.rez.tcainternationalgames.user", userName + "" ); //Optional parameters
                pd.dismiss();
                activity.startActivity( i );
            } else {

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }
}
