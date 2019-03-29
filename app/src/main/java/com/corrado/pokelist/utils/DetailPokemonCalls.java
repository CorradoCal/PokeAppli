package com.corrado.pokelist.utils;

import android.support.annotation.Nullable;

import com.corrado.pokelist.models.Pokemon;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Corrado on 27/03/2019.
 */
public class DetailPokemonCalls {

    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Pokemon pokemon);
        void onFailure();
    }

    public static void fetchPokemon(DetailPokemonCalls.Callbacks callbacks, int id){

        // Create a weak reference
        final WeakReference<DetailPokemonCalls.Callbacks> callbacksWeakReference = new WeakReference<DetailPokemonCalls.Callbacks>(callbacks);

        // Get a Retrofit instance
        PokemonService pokemonService = PokemonService.retrofit.create(PokemonService.class);

        // Create the call
        Call<Pokemon> call = pokemonService.getDetailPokemon(id);

        call.enqueue(new Callback<Pokemon>() {

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }

}
