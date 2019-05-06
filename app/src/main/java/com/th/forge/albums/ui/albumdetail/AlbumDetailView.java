package com.th.forge.albums.ui.albumdetail;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.model.track.Track;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AlbumDetailView extends MvpView {
    void showError(int errorResource);
    void setupAlbum(Album albumInfo, List<Track> trackList);
    void startLoading();
    void endLoading();
}
