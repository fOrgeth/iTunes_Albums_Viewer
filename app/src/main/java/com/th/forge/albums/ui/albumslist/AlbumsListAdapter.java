package com.th.forge.albums.ui.albumslist;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.album.Album;
import com.th.forge.albums.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;


public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumsViewHolder> {
    private static final String TAG = AlbumsListAdapter.class.getSimpleName();
    private List<Album> albums = new ArrayList<>();
    private FragmentTransactionCallback callback;

    public AlbumsListAdapter(FragmentTransactionCallback callback) {
        this.callback = callback;
    }

    public void setupAlbums(List<Album> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_albums, viewGroup, false);
        return new AlbumsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsViewHolder viewHolder, int position) {
        viewHolder.bind(albums.get(position), callback);
    }

    @Override
    public int getItemCount() {
        return albums == null ? 0 : albums.size();
    }

    static class AlbumsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView thumbnail;
        private TextView title;
        private TextView trackCount;
        private Album album;
        FragmentTransactionCallback callback;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            thumbnail.setClickable(true);
            trackCount = itemView.findViewById(R.id.txt_track_count);
            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Album album, FragmentTransactionCallback callback) {
            this.callback = callback;
            //ToDO: DRY
            thumbnail.setOnClickListener((view) -> callShowDetail(view, album));
            this.album = album;
            String url = this.album.getArtWorkUrl();
            if (url != null) {
                GlideApp.with(itemView.getContext()).load(url).into(thumbnail);
            }
            trackCount.setText(album.getTrackCount().toString());
            title.setText(album.getTitle());
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick");
            callShowDetail(view, album);
        }

        private void callShowDetail(View view, Album album) {
            callback.showDetail(view, album.getCollectionId(), album.getTitle());
        }
    }
}
