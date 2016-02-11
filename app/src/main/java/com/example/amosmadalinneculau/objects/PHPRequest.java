package com.example.amosmadalinneculau.objects;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Amos Madalin Neculau on 11/02/2016.
 */
public class PHPRequest extends AsyncTask<String,Void,String> {

    protected URL url;
    protected String data;
    protected URLConnection connection;
    protected boolean connected;

    protected String email;
    protected String password;
    public PHPRequest(String email, String password){
        this.email = email;
        this.password = password;

    }

    protected void onPostExecute() {
        if(connected == true)
            loginSuccesfully(true);
        else
            loginSuccesfully(false);
    }

    public boolean loginSuccesfully(boolean toReturn){
        return toReturn;
    }


    @Override
    protected String doInBackground(String... params) {

        connected = false;
        try {
            url = new URL("http://intendev.ro/projectrun/login.php");

            data = URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");

            connection = url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write( data );
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(connection.getInputStream()));

            String response= null;
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }

            Log.i("status: ", sb.toString());

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
