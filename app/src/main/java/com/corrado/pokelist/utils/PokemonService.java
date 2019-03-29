package com.corrado.pokelist.utils;

import com.corrado.pokelist.models.Pokemon;
import com.corrado.pokelist.models.Pokemons;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Corrado on 26/03/2019.
 */
public interface PokemonService {

    @GET("pokemon/")
    Call<Pokemons> getPokemons(@Query("offset") int offset, @Query("limit") int limit);

    @GET("pokemon/{id}/")
    Call<Pokemon> getDetailPokemon(@Path("id") int id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
