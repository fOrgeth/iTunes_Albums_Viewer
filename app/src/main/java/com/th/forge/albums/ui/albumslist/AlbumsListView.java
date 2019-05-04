package com.th.forge.albums.ui.albumslist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.th.forge.albums.data.db.model.album.Album;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AlbumsListView extends MvpView {
    void showError(int error);
    void setupAlbums(List<Album> albums);
    void startLoading();
    void endLoading();

}
