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
        String newUrl = formatUrlToHigherRes(type.getArtworkUrl100());
        Log.d("AlbumMapper", newUrl);
        return new Album(type.getCollectionName(),
                newUrl,
                type.getArtistName(),
                type.getCollectionId(),
                type.getTrackCount());
    }

    private String formatUrlToHigherRes(String tmp) {
        int endpoint = tmp.lastIndexOf("/") + 1;
        return tmp.substring(0, endpoint) + "600x600bb.jpg";

    }
}
