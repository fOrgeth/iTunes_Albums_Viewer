package com.th.forge.albums.ui.albumslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.th.forge.albums.R;
import com.th.forge.albums.data.db.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.AlbumsViewHolder> {
    private List<Album> albums = new ArrayList<>();

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
        viewHolder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums == null ? 0 : albums.size();
    }

    static class AlbumsViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;
        private TextView title;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
        }

        public void bind(Album album) {
            String url = album.getArtWorkUrl();
            if (url != null) {
                Glide.with(itemView.getContext()).load(url).into(thumbnail);
            }
            title.setText(album.getTitle());
        }
    }
}
