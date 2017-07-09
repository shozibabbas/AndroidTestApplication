package com.example.sayyedshozibabbas.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    protected Button btnGet;
    protected Button btnPost;
    protected Button btnPut;
    protected Button btnDelete;
    protected TextView responseBox;
    protected EditText contentBox;
    protected EditText contentBox2;

    private Gson gson;

    String u;
    String c;


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

        contentBox = (EditText) findViewById(R.id.contentBox);
        contentBox2 = (EditText) findViewById(R.id.contentBox2);

        u = "http://192.168.15.110:3000/posts";
        c = "UTF-8";

        gson = new Gson();

    }

    public void btnGetClick(View v) {
        GetPerformer rp = new GetPerformer();
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
        responseBox.setText(posts.toString());
    }

    public void btnPostClick(View v) {
        PostPerformer rp = new PostPerformer();
        String response = "";

        String content1 = contentBox.getText().toString();
        String content2 = contentBox2.getText().toString();

        try {
            response = rp.execute(u, c, content1, content2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        responseBox.setText(response);
        jsonObj post = gson.fromJson(response, jsonObj.class);
        responseBox.setText(post.toString());
    }



}
