package com.th.forge.albums.ui.albumdetail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.th.forge.albums.data.repository.singleAlbum.PresenterCallback;

@InjectViewState
public class AlbumDetailPresenter extends MvpPresenter<AlbumDetailView> implements PresenterCallback {

    void showError(int errorResource){

    }

    void loadChosenAlbum(Long albumId){

    }


    @Override
    public void onAlbumLoaded() {

    }

    @Override
    public void onError() {

    }
}
