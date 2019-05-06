package com.th.forge.albums.data.repository.singleAlbum;

import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.model.track.Track;

import java.util.List;

public interface PresenterCallback {
    void onError(int errorResource);
    void onAlbumLoaded(Album albumInfo, List<Track> trackList);
}
