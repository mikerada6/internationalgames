package com.tca.rez.tcainternationalgames;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    EditText userName, pass;
    String user;
    String Pass;
    TextView content;
    int count;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        content = (TextView) findViewById(R.id.content);
        final Button login = (Button) findViewById(R.id.butSubmit);
        count = 0;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        login.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                String email = userName.getText().toString();
                String password = pass.getText().toString();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    loginUser(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter your user name and password!", Toast.LENGTH_LONG)
                            .show();
                }
            }

        });
    }

    private void loginUser(final String user, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";

        progressDialog.setMessage("Logging in ...");
        showDialog();
        try {
            // open a connection to the site
            URL url = new URL("https://internationalgames-mikerada6.c9users.io/password_verify.php");
            URLConnection con = url.openConnection();
            // activate the output
            con.setDoOutput(true);
            PrintStream ps = new PrintStream(con.getOutputStream());
            // send your parameters to your site
            ps.print("user=" + user);
            ps.print("&password=" + password);

            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            // close the print stream
            ps.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String temp="";
            String line = null;
            while ((line = in.readLine()) != null) {
                temp+=line;
            }
            hideDialog();
            if(temp.equals("yes"))
            {
                //log in
                Intent i = new Intent(this, Main2Activity.class);
                i.putExtra("user", user); //Optional parameters
                startActivity(i);
            }
            else
            {
                content.setText("Error");
                content.setVisibility(View.VISIBLE);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        hideDialog();

    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
