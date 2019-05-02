package com.th.forge.albums.data;

import com.th.forge.albums.data.db.model.Album;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static List<Album> getAlbums() {
        List<Album> sample = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Album album = new Album("Title " + i + 1,
                    "https://is2-ssl.mzstatic.com/image/thumb/Music/v4/07/be/69/07be695d-31cb-a84c-c9d8-de3dd33fe414/source/100x100bb.jpg",
                    (i + 1) * 3);
            sample.add(album);
        }
        return sample;
    }
}
