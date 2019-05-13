package com.th.forge.albums.ui.albumdetail;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.model.track.Track;
import com.th.forge.albums.data.repository.singleAlbum.PresenterCallback;
import com.th.forge.albums.data.repository.singleAlbum.SingleAlbumRepository;

import java.util.List;

@InjectViewState
public class AlbumDetailPresenter extends MvpPresenter<AlbumDetailView> implements PresenterCallback {

    void showError(@StringRes int errorResource) {
        getViewState().showError(errorResource);
    }

    void loadChosenAlbum(Long albumId) {
        getViewState().startLoading();
        new SingleAlbumRepository(this).loadAlbum(albumId);
    }


    @Override
    public void onAlbumLoaded(Album albumInfo, List<Track> trackList) {
        getViewState().endLoading();
        if (trackList.size() == 0 || albumInfo == null) {
            getViewState().showError(R.string.detail_album_loading_error);
        } else {
            getViewState().setupAlbum(albumInfo, trackList);
        }
    }

    @Override
    public void onError(@StringRes int errorResource) {
        showError(errorResource);
    }
}
