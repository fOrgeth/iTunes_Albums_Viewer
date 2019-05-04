package com.th.forge.albums.ui.albumslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.Album;
import com.th.forge.albums.utils.GlideApp;
import com.th.forge.albums.utils.MyGlide;

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
        private Album album;
        FragmentTransactionCallback callback;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            thumbnail.setClickable(true);

            title = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        public void bind(Album album, FragmentTransactionCallback callback) {
            this.callback = callback;
            //ToDO: DRY
            thumbnail.setOnClickListener((view) -> {
                callback.showDetail(album.getCollectionId());
            });
            this.album = album;
            String url = this.album.getArtWorkUrl();
            if (url != null) {
                GlideApp.with(itemView.getContext()).load(url).into(thumbnail);
            }
            title.setText(album.getTitle());
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick");
            callback.showDetail(album.getCollectionId());
        }
    }
}
