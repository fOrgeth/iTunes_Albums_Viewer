package com.th.forge.albums.ui.albumslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumsViewHolder> {

    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsViewHolder albumsViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class AlbumsViewHolder extends RecyclerView.ViewHolder {
        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
