package com.th.forge.albums.data.db.model.album;

import android.util.Log;

import com.th.forge.albums.data.db.model.Mapper;
import com.th.forge.albums.data.network.model.albumslist.AlbumResult;

public class AlbumMapper implements Mapper<Album, AlbumResult> {

    @Override
    public AlbumResult mapFromEntity(Album type) {
        return null;
    }

    @Override
    public Album mapToEntity(AlbumResult type) {
        String title = type.getCollectionName() == null ? "Unknown" : type.getCollectionName();
        String artistName = type.getArtistName() == null ? "Unknown" : type.getArtistName();
        String newUrl = type.getArtworkUrl100() == null ? null : formatUrlToHigherRes(type.getArtworkUrl100());
        Long id = type.getCollectionId() == null ? 0L : type.getCollectionId();
        Long trackCount = type.getTrackCount() == null ? 0L : type.getTrackCount();
        Log.d("AlbumMapper", newUrl);
        return new Album(title,
                newUrl,
                artistName,
                id,
                trackCount);
    }

    private String formatUrlToHigherRes(String tmp) {
        int endpoint = tmp.lastIndexOf("/") + 1;
        return tmp.substring(0, endpoint) + "600x600bb.jpg";

    }
}
