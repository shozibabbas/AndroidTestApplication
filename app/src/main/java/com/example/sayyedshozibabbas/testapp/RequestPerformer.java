package com.example.sayyedshozibabbas.testapp;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Sayyed Shozib Abbas on 7/9/2017.
 */

public class RequestPerformer extends AsyncTask<String, Void, String> {
    protected String url;
    protected String charset;

    protected String doInBackground(String...strings) {
        url = strings[0];
        charset = strings[1];
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            InputStream response = connection.getInputStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            return (responseBody);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result) {

    }
}
