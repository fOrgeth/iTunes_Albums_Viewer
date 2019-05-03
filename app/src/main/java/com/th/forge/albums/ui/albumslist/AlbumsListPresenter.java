package com.th.forge.albums.ui.albumslist;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.Album;
import com.th.forge.albums.data.repository.AlbumsRepository;
import com.th.forge.albums.data.repository.PresenterCallback;

import java.util.List;

@InjectViewState
public class AlbumsListPresenter extends MvpPresenter<AlbumsListView> implements PresenterCallback {
    public static final String TAG = AlbumsListPresenter.class.getSimpleName();

    void showError(int error) {
        getViewState().showError(error);
    }

    void loadAlbums() {
        getViewState().startLoading();
        new AlbumsRepository(this).loadSampleAlbums();
    }

    void searchByAlbum() {

    }

    @Override
    public void onAlbumsLoaded(List<Album> albums) {
        getViewState().endLoading();
        if (albums.size() == 0) {
            getViewState().showError(R.string.albums_not_found);
        } else {
            Log.d(TAG,"onLoaded else");
            getViewState().setupAlbums(albums);
        }
    }
}
