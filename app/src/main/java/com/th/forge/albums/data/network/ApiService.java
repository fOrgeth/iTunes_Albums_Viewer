package com.th.forge.albums.data.network;

import com.th.forge.albums.data.network.model.AlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("search?term=*&entity=album&country=us&limit=25")
    Call<AlbumsResponse> getAlbums(/*@NonNull @Query("term") String searchString,
                     @NonNull @Query("country") String countryCode,
                     @Query("entity") String resultType,
                     @Query("limit") String limit*/);
}
