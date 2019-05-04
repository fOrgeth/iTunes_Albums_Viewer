package com.th.forge.albums.data.db.model.album;

import com.th.forge.albums.data.db.model.Mapper;
import com.th.forge.albums.data.network.model.albumslist.AlbumResult;

public class AlbumMapper implements Mapper<Album, AlbumResult> {

    @Override
    public AlbumResult mapFromEntity(Album type) {
        return null;
    }

    @Override
    public Album mapToEntity(AlbumResult type) {
        //ToDo: make url to higher resolution
        return new Album(type.getCollectionName(),
                type.getArtworkUrl100(),
                type.getCollectionId(),
                type.getTrackCount());
    }
}
