package com.th.forge.albums.ui.albumdetail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AlbumDetailView extends MvpView {
    void showError(int errorResource);
    void setupAlbum();
    void startLoading();
    void endLoading();
}
