package com.th.forge.albums.ui.albumslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsListFragment extends MvpAppCompatFragment implements AlbumsListView {
    private static final String TAG = AlbumsListFragment.class.getSimpleName();

    @InjectPresenter
    AlbumsListPresenter albumsPresenter;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AlbumsListAdapter adapter;
    private CircularProgressView circularProgressView;
    private TextView txtNoAlbums;
    private FragmentTransactionCallback callback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        callback = (FragmentTransactionCallback) getActivity();
    }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_albums_list, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                albumsPresenter.searchAlbumsByTitle(s);
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_clear:
                albumsPresenter.onClearSearch();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initUi() {
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = recyclerView.getWidth();
                float columnWidth = width;
                if (getActivity() != null) {
                    columnWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                            getActivity().getResources().getDisplayMetrics());
                }
                if (width > 0) {
                    int columnCount = Math.round(width / columnWidth);
                    layoutManager = new GridLayoutManager(getActivity(), columnCount);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new AlbumsListAdapter(callback);
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
