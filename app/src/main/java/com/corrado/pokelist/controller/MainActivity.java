package com.corrado.pokelist.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.corrado.pokelist.models.Pokemons;
import com.corrado.pokelist.models.Result;
import com.corrado.pokelist.utils.PokemonCalls;
import com.corrado.pokelist.utils.Utils;
import com.corrado.pokelist.views.PokemonAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.corrado.pokelist.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PokemonCalls.Callbacks,
        PokemonAdapter.OnItemClickListener {

    // Declare RecyclerView
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;


    // Declare list of pokemons & Adapter
    private List<Result> mResultList;
    private List<Result> mCacheList;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.mCacheList = new ArrayList<>();
        loadData();

        // if is online load from internet else load from cache
        if (Utils.isOnline(this)) {
            PokemonCalls.fetchPokemons(this, 0, 60);
        } else {
            Utils.showAlertConnection(this);
            confRecyclerView();
            updateList(mCacheList);
        }

    }

    @Override
    public void onResponse(@Nullable Pokemons pokemons) {
        Log.d("MainActivity", "getCount: "+ pokemons.getCount());
        Log.d("MainActivity", "getName: "+ pokemons.getResults().get(0).getName());

        this.mResultList = new ArrayList<>();
        mResultList.addAll(pokemons.getResults());
        if (!mResultList.isEmpty()) {
            saveData();
        }
        confRecyclerView();
        updateList(pokemons.getResults());
    }

    @Override
    public void onFailure() {

    }

    private void confRecyclerView(){
        // Reset list
        this.mResultList = new ArrayList<>();
        // Create adapter passing the list of users
        this.adapter = new PokemonAdapter(this.mResultList, this, this);
        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);
        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter.notifyDataSetChanged();
    }

    private void updateList(List<Result> results){
        mResultList.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Result result) {
        //Log.i("MainActivity", "onItemClick: ");
        //Toast.makeText(MainActivity.this, result.getName(), Toast.LENGTH_SHORT).show();

        String id = result.getUrl().replace("https://pokeapi.co/api/v2/pokemon/", "");
        id = id.replace("/", "");
        Log.d("MainActivity", "id: "+ id);

        Intent intent = new Intent(MainActivity.this, DetailPokemonActivity.class);
        intent.putExtra("id", Integer.parseInt(id));
        startActivity(intent);
        Animatoo.animateZoom(this);  //fire the zoom animation
    }

    // save list of pokemons into SharedPreferences
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mResultList);
        editor.putString("pokemon list", json);
        editor.apply();
    }

    // load list of pokemons from SharedPreferences
    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("pokemon list", null);
        Type type = new TypeToken<ArrayList<Result>>() {}.getType();
        mCacheList = gson.fromJson(json, type);

        if (mCacheList == null) {
            mCacheList = new ArrayList<>();
        }

        Log.d("MainActivity", "loadData: "+ mCacheList.size());
    }
}
