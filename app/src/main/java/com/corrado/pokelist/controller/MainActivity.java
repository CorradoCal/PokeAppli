package com.corrado.pokelist.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.corrado.pokelist.R;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pokedexOpen(View view) {
        Intent intent = new Intent(this, PokedexActivity.class);
        startActivity(intent);
    }
}
