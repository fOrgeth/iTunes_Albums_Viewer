package com.th.forge.albums.ui.albumdetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.track.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackListAdapter extends RecyclerView.Adapter<TrackListAdapter.TracksViewHolder> {
    private static final String TAG = TrackListAdapter.class.getSimpleName();
    private List<Track> trackList = new ArrayList<>();


    public void setupTracks(List<Track> trackList) {
        this.trackList = trackList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TracksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_track, viewGroup, false);
        return new TracksViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TracksViewHolder viewHolder, int position) {
        viewHolder.bind(trackList.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return trackList == null ? 0 : trackList.size();
    }

    static class TracksViewHolder extends RecyclerView.ViewHolder {
        private TextView trackTitle;
        private TextView trackTime;

        public TracksViewHolder(@NonNull View itemView) {
            super(itemView);
            trackTitle = itemView.findViewById(R.id.txt_track_title);
            trackTime = itemView.findViewById(R.id.txt_track_time);
        }

        public void bind(Track track, int trackIndex) {
            //ToDo: track index to separate TextView
            trackTitle.setText(String.format("%02d. %s", trackIndex, track.getTrackName()));
            trackTime.setText(track.getTrackTime());
        }
    }
}
