package com.th.forge.albums.data.repository.singleAlbum;

import com.th.forge.albums.App;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.model.album.AlbumMapper;
import com.th.forge.albums.data.db.model.track.Track;
import com.th.forge.albums.data.db.model.track.TrackMapper;
import com.th.forge.albums.data.network.model.albumdetail.AlbumResponse;
import com.th.forge.albums.data.network.model.albumdetail.AlbumResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleAlbumRepository {
    private static final String TAG = SingleAlbumRepository.class.getSimpleName();

    private PresenterCallback presenter;
    private TrackMapper mapper;

    public SingleAlbumRepository(PresenterCallback presenter) {
        this.presenter = presenter;
        mapper = new TrackMapper();
    }

    public void loadAlbum(Long albumId) {
        final List<AlbumResult> albumResult = new ArrayList<>();
        final List<Track> trackList = new ArrayList<>();

        App.getApiService().getAlbumById(albumId).enqueue(new Callback<AlbumResponse>() {
            @Override
            public void onResponse(Call<AlbumResponse> call, Response<AlbumResponse> response) {
                if (response.body() != null) {
                    if (response.body().getResults() == null)
                        presenter.onError(R.string.empty_response_album_detail);
                    else {
                        albumResult.addAll(response.body().getResults());
                        for (int i = 1; i < albumResult.size(); i++) {
                            trackList.add(mapper.mapToEntity(albumResult.get(i)));
                        }
                        //ToDo: DRY
                        Album albumInfo = new Album(albumResult.get(0).getCollectionName(),
                                albumResult.get(0).getArtworkUrl100(),
                                albumResult.get(0).getCollectionId(),
                                albumResult.get(0).getTrackCount());
                        presenter.onAlbumLoaded(albumInfo, trackList);
                    }
                } else presenter.onError(R.string.null_response_body);

            }

            @Override
            public void onFailure(Call<AlbumResponse> call, Throwable t) {

            }
        });
    }
}
