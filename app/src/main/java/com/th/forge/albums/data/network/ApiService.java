package com.th.forge.albums.data.network;

import android.support.annotation.NonNull;

import com.th.forge.albums.data.network.model.albumslist.AlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search?term=*&entity=album&country=us&limit=50")
    Call<AlbumsResponse> getAlbums(/*@NonNull @Query("term") String searchString,
                     @NonNull @Query("country") String countryCode,
                     @Query("entity") String resultType,
                     @Query("limit") String limit*/);

    @GET("lookup?entity=song")
    Call<AlbumsResponse> getAlbumById(@NonNull @Query("id") Long albumId);
}
