package com.th.forge.albums.ui.albumdetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.th.forge.albums.BuildConfig;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.data.db.model.track.Track;
import com.th.forge.albums.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class AlbumDetailFragment extends MvpAppCompatFragment implements AlbumDetailView {
    private static final String TAG = AlbumDetailFragment.class.getSimpleName();

    @InjectPresenter
    AlbumDetailPresenter albumPresenter;

    private TextView txtError;
    private TextView txtAlbumTitle;
    private TextView txtAlbumArtist;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CircularProgressView circularProgressView;
    private ImageView imgAlbumCover;
    private TrackListAdapter adapter;

    private Long albumId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            albumId = getArguments().getLong(BuildConfig.ALBUM_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album_detail, container, false);
        txtAlbumTitle = rootView.findViewById(R.id.txt_album_title);
        txtAlbumArtist = rootView.findViewById(R.id.txt_album_artist);
        txtError = rootView.findViewById(R.id.txt_chosen_album_error);
        circularProgressView = rootView.findViewById(R.id.cpv_chosen_album);
        recyclerView = rootView.findViewById(R.id.rv_track_list);
        imgAlbumCover = rootView.findViewById(R.id.img_album_cover);
        albumPresenter.loadChosenAlbum(albumId);
        initUi();
        return rootView;
    }

    private void initUi() {
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new TrackListAdapter();
        adapter.setupTracks(new ArrayList<>());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startLoading() {
        circularProgressView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showError(int errorResource) {
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(errorResource);
    }

    @Override
    public void setupAlbum(Album albumInfo, List<Track> trackList) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setupTracks(trackList);
        txtAlbumTitle.setText(albumInfo.getTitle());
        //ToDo: add artist field or remove
        txtAlbumArtist.setText(albumInfo.getArtistName());
        if (getActivity() != null && albumInfo.getArtWorkUrl() != null) {
            GlideApp.with(getActivity()).load(albumInfo.getArtWorkUrl()).into(imgAlbumCover);
        }
    }

    @Override
    public void endLoading() {
        circularProgressView.setVisibility(View.GONE);
    }
}
