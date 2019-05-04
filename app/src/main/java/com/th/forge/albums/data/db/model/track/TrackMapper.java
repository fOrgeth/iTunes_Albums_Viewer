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
        Long time = type.getTrackTimeMillis();
        String formatedTrackTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(time),
                TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
        return new Track(type.getTrackName(),
                formatedTrackTime);
    }
}
