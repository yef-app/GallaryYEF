package com.example.lenovo.yefgallary;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import android.view.View;
import android.widget.ProgressBar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<MyModel>myModels;
    String pageurl="https://pixabay.com/api/?key=10860748-83b5347a866cfeb8e4c85c3e2&q=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
        myModels=new ArrayList<>();
        new MyTask().execute(pageurl+"fruits");
        Log.i("pageurl",pageurl+"fruits");

        if(isOnline()){
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            }
            else {
                recyclerView.setLayoutManager(new GridLayoutManager(this,1));
            }

            recyclerView.setAdapter(new MyAdapter(this,myModels));

        }
        else {
            AlertDialog alertDialog=new AlertDialog.Builder(this).create();
            alertDialog.setTitle("alert");
            alertDialog.setMessage("please check internet");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setCancelable(false);
            alertDialog.show();

        }
    }
    private boolean isOnline() {
        ConnectivityManager connectivityManager= (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isConnected()|| ! networkInfo.isAvailable()){
            return false;
        }
        return true;
    }

    class MyTask extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url=new URL(strings[0]);
                HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                Scanner scanner=new Scanner(inputStream);
                scanner.useDelimiter("\\A");
                if(scanner.hasNext()){
                    return scanner.next();
                }
                else {
                    return null;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            if (s!=null) {
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    // Toast.makeText(MainActivity.this, ""+frurl, Toast.LENGTH_SHORT).show();
                    Log.i("Str", String.valueOf(jsonObject));
                    JSONArray jsonArray=jsonObject.getJSONArray("hits");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject obj=jsonArray.getJSONObject(i);
                        String pageurl=obj.getString("largeImageURL");
                        String tag=obj.getString("tags");
                        myModels.add(new MyModel(pageurl,tag));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}


