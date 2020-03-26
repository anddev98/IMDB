package com.sematec.imdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.List;

import Movies.Movies;
import cz.msebera.android.httpclient.Header;

public class ImdbTitleRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_title_recycler);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        String address = "https://www.omdbapi.com/?s=" + title + "&apikey=27e17400";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                Movies list = gson.fromJson(response.toString(),Movies.class);
                if (list.getSearch() == null){
                    Toast.makeText(ImdbTitleRecyclerActivity.this, "Movie not found", Toast.LENGTH_LONG).show();
                }
                else {
                    RecyclerView titleRecycler = findViewById(R.id.titleRecycler);
                    ImdbTitleAdapter adapter = new ImdbTitleAdapter(list.getSearch());
                    titleRecycler.setAdapter(adapter);
                    titleRecycler.setLayoutManager(new LinearLayoutManager(ImdbTitleRecyclerActivity.this, RecyclerView.VERTICAL, false));
                }
                }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
