package com.sematec.imdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Details.Details;

public class ImdbOfflineRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_offline_recycler);

        RecyclerView offlineRecycler = findViewById(R.id.offlineRecycler);
        final ImdbDatabase db = new ImdbDatabase(ImdbOfflineRecyclerActivity.this,"Imdb",null,1);
        List<Details> movieList;
        movieList = db.GetAllMovie();
        if (movieList.isEmpty()){

            Toast.makeText(ImdbOfflineRecyclerActivity.this,"Database is Empty",Toast.LENGTH_LONG).show();
        }
        else {
            ImdbOfflineAdapter adapter = new ImdbOfflineAdapter(movieList);
            offlineRecycler.setAdapter(adapter);
            offlineRecycler.setLayoutManager(new LinearLayoutManager(ImdbOfflineRecyclerActivity.this, RecyclerView.VERTICAL, false));
        }
    }
}
