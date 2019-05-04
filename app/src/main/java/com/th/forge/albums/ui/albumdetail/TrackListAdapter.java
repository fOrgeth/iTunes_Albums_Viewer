package com.th.forge.albums.ui.albumdetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TracksViewHolder> {
    private static final String TAG = TrackListAdapter.class.getSimpleName();



    @NonNull
    @Override
    public TracksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TracksViewHolder tracksViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class TracksViewHolder extends RecyclerView.ViewHolder{

        public TracksViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
