package com.th.forge.albums.ui.albumslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.th.forge.albums.R;
import com.th.forge.albums.data.Sample;

public class AlbumsListFragment extends MvpAppCompatFragment implements AlbumsListView {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_albums_list, container, false);
        recyclerView = rootView.findViewById(R.id.rv_albums);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                float columnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140,
                        AlbumsListFragment.this.getActivity().getResources().getDisplayMetrics());
                int width = recyclerView.getWidth();
                int columnCount = Math.round(width / columnWidth);
                recyclerView.setLayoutManager(new GridLayoutManager(AlbumsListFragment.this.getActivity(), columnCount));
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        AlbumsListAdapter adapter = new AlbumsListAdapter();
        adapter.setupAlbums(Sample.getAlbums());
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}
