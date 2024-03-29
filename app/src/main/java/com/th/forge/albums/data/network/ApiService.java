package com.th.forge.albums.data.network;

import android.support.annotation.NonNull;

import com.th.forge.albums.data.network.model.albumdetail.AlbumResponse;
import com.th.forge.albums.data.network.model.albumslist.AlbumsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search?entity=album")
    Call<AlbumsResponse> getAlbums(@NonNull @Query("term") String searchString,
                                   @NonNull @Query("country") String countryCode,
                                   @Query("limit") String limit);

    @GET("lookup?entity=song")
    Call<AlbumResponse> getAlbumById(@NonNull @Query("id") Long albumId);
}
