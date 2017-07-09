package com.example.sayyedshozibabbas.testapp;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Sayyed Shozib Abbas on 7/9/2017.
 */

public class PostPerformer extends AsyncTask<String, Void, String> {

    protected String doInBackground(String...strings) {
        String url = strings[0];
        String charset = strings[1];
        String content1 = strings[2];
        String content2 = strings[3];
        String query;
        try {
            query = String.format("title=%s&author=%s", URLEncoder.encode(content1, charset), URLEncoder.encode(content2, charset));
            URLConnection connection = new URL(url + "?" + query).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            OutputStream output = connection.getOutputStream();
            output.write(query.getBytes(charset));
            InputStream response = connection.getInputStream();
            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            return (responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(String result) {

    }
}
