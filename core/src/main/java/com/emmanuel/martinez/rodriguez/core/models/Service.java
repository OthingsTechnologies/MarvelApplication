package com.emmanuel.martinez.rodriguez.core.models;

import com.emmanuel.martinez.rodriguez.core.models.characters_models.CharacterDataWrapper;
import com.emmanuel.martinez.rodriguez.core.models.comic_models.ComicDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Service {

    @GET("characters")
    Call<CharacterDataWrapper> getCharacters(@Query("apikey") String apikey , @Query("hash") String hash , @Query("ts") String timestamp , @Query("limit") int limit, @Query("offset") int offset);

    @GET("characters/{character_id}/comics")
    Call<ComicDataWrapper> getComics(@Path("character_id") int characterId , @Query("apikey") String apikey , @Query("hash") String hash , @Query("ts") String timestamp , @Query("limit") int limit, @Query("offset") int offset);


}
