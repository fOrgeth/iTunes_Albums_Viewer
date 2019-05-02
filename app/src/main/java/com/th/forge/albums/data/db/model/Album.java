package com.th.forge.albums.data.db.model;

public class Album {
    private String title;
    private String artWorkUrl;
    private Integer trackCount;

    //ToDo: tmp, rm
    public Album(String title, String artWorkUrl, Integer trackCount) {
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

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }
}
