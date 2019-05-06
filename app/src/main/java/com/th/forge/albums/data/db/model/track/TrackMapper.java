package com.th.forge.albums.data.db.model.track;

import com.th.forge.albums.data.db.model.Mapper;
import com.th.forge.albums.data.network.model.albumdetail.AlbumResult;

import java.util.concurrent.TimeUnit;

public class TrackMapper implements Mapper<Track, AlbumResult> {
    @Override
    public AlbumResult mapFromEntity(Track type) {
        return null;
    }

    @Override
    public Track mapToEntity(AlbumResult type) {
        String formatedTrackTime = type.getTrackTimeMillis() == null ? "unknown" :
                getFormatedTrackTime(type.getTrackTimeMillis());
        String trackName = type.getTrackName() == null ? "Unknown" : type.getTrackName();
        return new Track(trackName,
                formatedTrackTime);
    }

    private String getFormatedTrackTime(Long timeInMillis) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(timeInMillis),
                TimeUnit.MILLISECONDS.toSeconds(timeInMillis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMillis)));

    }
}
