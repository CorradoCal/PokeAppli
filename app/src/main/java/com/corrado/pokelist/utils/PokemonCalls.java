package com.corrado.pokelist.utils;

import android.support.annotation.Nullable;

import com.corrado.pokelist.models.Pokemons;

import java.lang.ref.WeakReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Corrado on 26/03/2019.
 */
public class PokemonCalls {

    // 1 - Creating a callback
    public interface Callbacks {
        void onResponse(@Nullable Pokemons pokemons);
        void onFailure();
    }

    public static void fetchPokemons(Callbacks callbacks, int offset, int limit){

        // Create a weak reference
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);

        // Get a Retrofit instance
        PokemonService pokemonService = PokemonService.retrofit.create(PokemonService.class);

        // Create the call
        Call<Pokemons> call = pokemonService.getPokemons(offset, limit);

        call.enqueue(new Callback<Pokemons>() {

            @Override
            public void onResponse(Call<Pokemons> call, Response<Pokemons> response) {

                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Pokemons> call, Throwable t) {

                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailure();
            }
        });
    }

}
