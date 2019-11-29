package com.corrado.pokelist.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.corrado.pokelist.R;

/**
 * Created by Corrado on 20/12/2019.
 */

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
