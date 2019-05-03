package com.th.forge.albums.ui.albumslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsListFragment extends MvpAppCompatFragment implements AlbumsListView {
    public static final String TAG = AlbumsListFragment.class.getSimpleName();

    @InjectPresenter
    AlbumsListPresenter albumsPresenter;

    private RecyclerView recyclerView;
    private AlbumsListAdapter adapter;
    private CircularProgressView circularProgressView;
    private TextView txtNoAlbums;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_albums_list, container, false);
        circularProgressView = rootView.findViewById(R.id.cpv_albums);
        recyclerView = rootView.findViewById(R.id.rv_albums);
        txtNoAlbums = rootView.findViewById(R.id.txt_albums_error);
        initUi();
        albumsPresenter.loadAlbums();
        return rootView;
    }

    private void initUi() {
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float columnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160,
                        getActivity().getResources().getDisplayMetrics());
                int width = recyclerView.getWidth();
                if (width > 0) {
                    int columnCount = Math.round(width / columnWidth);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), columnCount));
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AlbumsListAdapter();
        adapter.setupAlbums(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(int error) {
        txtNoAlbums.setText(getString(error));
        txtNoAlbums.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupAlbums(List<Album> albums) {
        recyclerView.setVisibility(View.VISIBLE);
        txtNoAlbums.setVisibility(View.GONE);
        Log.d(TAG, "setupAlbums");
        adapter.setupAlbums(albums);
    }

    @Override
    public void startLoading() {
        recyclerView.setVisibility(View.GONE);
        txtNoAlbums.setVisibility(View.GONE);
        circularProgressView.setVisibility(View.VISIBLE);

    }

    @Override
    public void endLoading() {
        circularProgressView.setVisibility(View.GONE);
    }
}
