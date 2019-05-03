package com.th.forge.albums.data.db.model;

public class Album {
    private String title;
    private String artWorkUrl;
    private Long trackCount;

    //ToDo: tmp, rm
    public Album(String title, String artWorkUrl, Long trackCount) {
        this.title = title;
        this.artWorkUrl = artWorkUrl;
        this.trackCount = trackCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtWorkUrl() {
        return artWorkUrl;
    }

    public void setArtWorkUrl(String artWorkUrl) {
        this.artWorkUrl = artWorkUrl;
    }

    public Long getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Long trackCount) {
        this.trackCount = trackCount;
    }
}
