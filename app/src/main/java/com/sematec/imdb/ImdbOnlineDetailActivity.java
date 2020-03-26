package com.sematec.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import Details.Details;
import cz.msebera.android.httpclient.Header;

public class ImdbOnlineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_online_detail);
        Intent intent = getIntent();
        String ImdbId = intent.getStringExtra("id");
        String address  = "https://www.omdbapi.com/?i="+ImdbId+"&apikey=27e17400";
        final TextView txtOnlineTitle = findViewById(R.id.txtOnlineTitle);
        final TextView txtOnlineYear = findViewById(R.id.txtOnlineYear);
        final TextView txtOnlineDirector = findViewById(R.id.txtOnlineDirector);
        final TextView txtOnlineActors = findViewById(R.id.txtOnlineActors);
        final TextView txtOnlineCountry = findViewById(R.id.txtOnlineCountry);
        final TextView txtOnlineLanguage = findViewById(R.id.txtOnlineLanguage);
        final ImageView imgOnlinePoster = findViewById(R.id.imgOnlinePoster);
        final Button btnSave = findViewById(R.id.btnSave);

        final ImdbDatabase db = new ImdbDatabase(ImdbOnlineDetailActivity.this,"Imdb",null,1);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(address,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                Gson gson = new Gson();
                Details details = gson.fromJson(response.toString(),Details.class);

                final String title = details.getTitle();
                final String year = details.getYear();
                final String director = details.getDirector();
                final String country = details.getCountry();
                final String language = details.getLanguage();
                final String actors = details.getActors();
                txtOnlineTitle.setText(title);
                txtOnlineYear.setText(year);
                txtOnlineDirector.setText(director);
                txtOnlineCountry.setText(country);
                txtOnlineLanguage.setText(language);
                txtOnlineActors.setText(actors);
                final String imageUrl = details.getPoster();
                Picasso.get().load(imageUrl).into(imgOnlinePoster);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.insertMovie(title,year,director,country,language,actors,imageUrl);
                        Toast.makeText(ImdbOnlineDetailActivity.this,"Movie Save to Database",Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
