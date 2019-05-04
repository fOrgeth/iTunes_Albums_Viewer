package com.th.forge.albums.data.repository.albums;

import com.th.forge.albums.data.db.model.Album;

import java.util.List;

public interface PresenterCallback {
    void onError(int errorResource);
    void onAlbumsLoaded(List<Album> albums);
}
