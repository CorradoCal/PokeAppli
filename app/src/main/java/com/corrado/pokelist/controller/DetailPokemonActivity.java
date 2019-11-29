package com.corrado.pokelist.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.corrado.pokelist.models.Pokemon;
import com.corrado.pokelist.models.Type;
import com.corrado.pokelist.utils.DetailPokemonCalls;
import com.corrado.pokelist.R;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Corrado on 20/12/2019.
 */

public class DetailPokemonActivity extends AppCompatActivity implements DetailPokemonCalls.Callbacks {

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_types)
    TextView tv_types;
    @BindView(R.id.tv_height)
    TextView tv_height;
    @BindView(R.id.tv_weight)
    TextView tv_weight;

    @BindView(R.id.iv_front)
    ImageView iv_front;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    private int pokeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pokemon);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pokeId = extras.getInt("id");
            Log.d("DetailPokemonActivity", "pokeId: " + pokeId);

            DetailPokemonCalls.fetchPokemon(this, pokeId);
        }


    }

    @Override
    public void onResponse(@Nullable Pokemon pokemon) {
        Log.d("DetailPokemonActivity", "getName: "+pokemon.getName());

        String types = "";
        for (Type type : pokemon.getTypes()) {
            types += StringUtils.capitalize(type.getType().getName()) + " ";
        }

        tv_name.setText(StringUtils.capitalize(pokemon.getName().toUpperCase()));
        tv_types.setText(types);
        tv_height.setText(pokemon.getHeight() / (double) 10+" m");
        tv_weight.setText(pokemon.getWeight() / (double) 10+" kg");

        Glide.with(DetailPokemonActivity.this)
                .load(pokemon.getSprites().getFrontDefault())
                .into(iv_front);

        Glide.with(DetailPokemonActivity.this)
                .load(pokemon.getSprites().getBackDefault())
                .into(iv_back);

    }

    @Override
    public void onFailure() {

    }
}
