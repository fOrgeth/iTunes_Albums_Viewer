package com.th.forge.albums.data.repository;

import com.th.forge.albums.data.db.model.album.Album;

import java.util.ArrayList;
import java.util.List;

public class Sample {

    public static List<Album> getAlbums() {
        List<Album> sample = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Album album = new Album("Title " + i + 1,
                    "https://is2-ssl.mzstatic.com/image/thumb/Music/v4/07/be/69/07be695d-31cb-a84c-c9d8-de3dd33fe414/source/600x600bb.jpg",
                    "artistName" + i,
                    (Long.valueOf(i)) * 5,
                    (Long.valueOf(i)) * 3);
            sample.add(album);
        }
        sample.get(1).setArtWorkUrl("https://is4-ssl.mzstatic.com/image/thumb/Music7/v4/f7/17/64/f717645e-7fb5-6cc5-fa54-3a82d4e0287b/source/600x600bb.jpg");
        sample.get(2).setArtWorkUrl("https://is2-ssl.mzstatic.com/image/thumb/Music20/v4/5b/4e/c7/5b4ec7ba-c8d1-fc83-5d7f-cd93dea2fe1a/source/600x600bb.jpg");
        sample.get(3).setArtWorkUrl("https://is1-ssl.mzstatic.com/image/thumb/Music/v4/23/78/67/23786733-c453-050e-3403-25f310977ed7/source/600x600bb.jpg");
        sample.get(4).setArtWorkUrl("https://is2-ssl.mzstatic.com/image/thumb/Music/v4/17/55/66/17556695-d09e-ce59-dd84-928fd8a99fe0/source/600x600bb.jpg");
        return sample;
    }
}
