package com.th.forge.albums.data.db.model.track;

public class Track {
    private String trackName;
    private String trackTimeInMillis;

    public Track(String trackName, String trackTimeInMillis) {
        this.trackName = trackName;
        this.trackTimeInMillis = trackTimeInMillis;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackTime() {
        return trackTimeInMillis;
    }

    public void setTrackTime(String trackTimeInMillis) {
        this.trackTimeInMillis = trackTimeInMillis;
    }
}
