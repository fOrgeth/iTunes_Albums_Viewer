package com.th.forge.albums.data.db.model.album;

public class Album {
    private String title;
    private String artWorkUrl;
    private Long collectionId;
    private Long trackCount;

    //ToDo: tmp, rm
    public Album(String title, String artWorkUrl, Long collectionId, Long trackCount) {
        this.title = title;
        this.artWorkUrl = artWorkUrl;
        this.collectionId = collectionId;
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

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Long trackCount) {
        this.trackCount = trackCount;
    }
}
