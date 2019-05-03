package com.th.forge.albums.data.repository;

import android.os.Handler;
import android.util.Log;

import com.th.forge.albums.data.db.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRepository {
    public static final String TAG = AlbumsRepository.class.getSimpleName();
    private PresenterCallback presenter;

    //ToDo: DI
    public AlbumsRepository(PresenterCallback presenter) {
        this.presenter = presenter;
    }

    public void loadSampleAlbums() {
        List<Album> albums = new ArrayList<>();
        new Handler().postDelayed(() -> {
            albums.addAll(Sample.getAlbums());
            presenter.onAlbumsLoaded(albums);
        }, 2000);

        Log.d(TAG, "here");
    }
}
