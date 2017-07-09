package com.example.sayyedshozibabbas.testapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    protected Button btnGet;
    protected Button btnPost;
    protected Button btnPut;
    protected Button btnDelete;
    protected TextView responseBox;

    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting elements to their variables
        btnGet = (Button) findViewById(R.id.btnGet);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnPut = (Button) findViewById(R.id.btnPut);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        responseBox = (TextView) findViewById(R.id.responseBox);
        responseBox.setMovementMethod(new ScrollingMovementMethod());

        gson = new Gson();

    }

    public void btnGetClick(View v) {
        String u = "http://192.168.15.110:3000/posts";
        String c = "UTF-8";
        RequestPerformer rp = new RequestPerformer();
        String response = "";

        try {
            response = rp.execute(u, c).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        responseBox.setText(response);
        List<jsonObj> posts = Arrays.asList(gson.fromJson(response, jsonObj[].class));
        responseBox.setText(posts.get(0).toString());
    }



}
