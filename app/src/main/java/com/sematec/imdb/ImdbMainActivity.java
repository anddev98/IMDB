package com.sematec.imdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImdbMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imdb_main);

        final EditText edtTitle = findViewById(R.id.edtTitle);
        Button btnShowOnline = findViewById(R.id.btnShowOnline);
        Button btnShowOffline = findViewById(R.id.btnShowOffline);

        btnShowOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String title = edtTitle.getText().toString();
                    if (title.isEmpty() ) {
                            Toast.makeText(ImdbMainActivity.this, "Please Enter Movie Title", Toast.LENGTH_LONG).show();
                        }

                    else if (!(title.matches("[a-zA-Z0-9-_]+"))){
                        edtTitle.setText("");
                        Toast.makeText(ImdbMainActivity.this, "Please Enter Correct Title", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent(ImdbMainActivity.this, ImdbTitleRecyclerActivity.class);
                        intent.putExtra("title", title);
                        startActivity(intent);
                    }
            }
        });

        btnShowOffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImdbMainActivity.this,ImdbOfflineRecyclerActivity.class);
                startActivity(intent);
            }
        });
    }
}
