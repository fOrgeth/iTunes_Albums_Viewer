package com.th.forge.albums.data.repository;

import android.os.Handler;
import android.util.Log;

import com.th.forge.albums.App;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.Album;
import com.th.forge.albums.data.db.model.AlbumMapper;
import com.th.forge.albums.data.db.model.Mapper;
import com.th.forge.albums.data.network.ApiService;
import com.th.forge.albums.data.network.model.AlbumResult;
import com.th.forge.albums.data.network.model.AlbumsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsRepository {
    private static final String TAG = AlbumsRepository.class.getSimpleName();
    private PresenterCallback presenter;
    private AlbumMapper mapper;

    //ToDo: DI
    public AlbumsRepository(PresenterCallback presenter) {
        this.presenter = presenter;
        mapper = new AlbumMapper();
    }

    public void loadSampleAlbums() {
        List<Album> albums = new ArrayList<>();
        new Handler().postDelayed(() -> {
            albums.addAll(Sample.getAlbums());
            presenter.onAlbumsLoaded(albums);
        }, 2000);

        Log.d(TAG, "here");
    }

    public void loadAlbums() {
        final List<AlbumResult> albumResultList = new ArrayList<>();
        final List<Album> albums = new ArrayList<>();
        App.getApiService().getAlbums().enqueue(new Callback<AlbumsResponse>() {
            @Override
            public void onResponse(Call<AlbumsResponse> call, Response<AlbumsResponse> response) {
                if (response.body() != null) {
                    if (response.body().getAlbums() == null) presenter.onError(R.string.empty_response_albums_result);
                    else albumResultList.addAll(response.body().getAlbums());
                } else presenter.onError(R.string.null_response_body);
                for (AlbumResult a : albumResultList) {
                    albums.add(mapper.mapToEntity(a));
                }
                presenter.onAlbumsLoaded(albums);
            }

            @Override
            public void onFailure(Call<AlbumsResponse> call, Throwable t) {
                Log.d(TAG, "some error");
                presenter.onError(R.string.api_response_failure);
            }
        });
    }
}
