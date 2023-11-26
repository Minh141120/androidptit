package com.example.tuto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import model.Player;
import network.Client;

public class FindMapActivity extends AppCompatActivity {
    Button btnFindMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_map);

        btnFindMatch = findViewById(R.id.idFindMatch);
        btnFindMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(() -> {

                    Player p = new Player("Nguyen Ngoc Minh");
                    // request find match
                    // receive object opponent
                    Client c = new Client();
                    Player opponent = c.findMatch(p);
                    if(opponent != null){
                        Intent intent = new Intent(FindMapActivity.this, CaroGameActivity.class);
                        // intent.putExtra();
                        startActivity(intent);
                    }

                }).start();

            }
        });
    }
}