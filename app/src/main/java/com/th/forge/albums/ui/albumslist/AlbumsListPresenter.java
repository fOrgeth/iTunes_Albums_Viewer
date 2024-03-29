package com.th.forge.albums.ui.albumslist;


import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.albums.App;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.sharedpref.SharedPrefStorage;
import com.th.forge.albums.data.repository.albums.AlbumsRepository;
import com.th.forge.albums.data.repository.albums.PresenterCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@InjectViewState
public class AlbumsListPresenter extends MvpPresenter<AlbumsListView> implements PresenterCallback {
    private static final String TAG = AlbumsListPresenter.class.getSimpleName();
    private AlbumsRepository repository;
    private SharedPrefStorage sharedPrefStorage;

    //ToDo: DI
    public AlbumsListPresenter() {
        repository = new AlbumsRepository(this);
        sharedPrefStorage = App.getInstance().getSharedPrefStorage();
    }

    void showError(@StringRes int error) {
        getViewState().endLoading();
        getViewState().showError(error);
    }

    void loadAlbums() {
        getViewState().startLoading();
        if (sharedPrefStorage.getStoredQuery() == null) {
            repository.loadAlbums();
        } else {
            searchAlbumsByTitle(sharedPrefStorage.getStoredQuery());
        }
    }

    void searchAlbumsByTitle(@NonNull String s) {
        getViewState().startLoading();
        sharedPrefStorage.storeQuery(s);
        repository.loadAlbums(s);
    }

    @Override
    public void onAlbumsLoaded(List<Album> albums) {
        getViewState().endLoading();
        if (albums.size() == 0) {
            getViewState().showError(R.string.albums_not_found);
        } else {
            Log.d(TAG, "onLoaded else");
            getViewState().setupAlbums(getAlbumsSortedAphabetically(albums));
        }
    }

    private List<Album> getAlbumsSortedAphabetically(List<Album> albums) {
        List<Album> sortedAlbums = new ArrayList<>(albums);
        Collections.sort(sortedAlbums, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        return sortedAlbums;
    }

    @Override
    public void onError(int errorResource) {
        showError(errorResource);
    }

    void onClearSearch() {
        sharedPrefStorage.storeQuery(null);
        loadAlbums();
    }
}
