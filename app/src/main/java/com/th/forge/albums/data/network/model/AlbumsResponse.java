package com.th.forge.albums.data.network.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsResponse {

    @SerializedName("resultCount")
    @Expose
    private Long resultCount;
    @SerializedName("albums")
    @Expose
    private List<AlbumResult> albums = null;

    public Long getResultCount() {
        return resultCount;
    }

    public void setResultCount(Long resultCount) {
        this.resultCount = resultCount;
    }

    public List<AlbumResult> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumResult> albums) {
        this.albums = albums;
    }

}