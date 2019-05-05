package com.th.forge.albums.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.th.forge.albums.R;
import com.th.forge.albums.ui.albumdetail.AlbumDetailFragment;
import com.th.forge.albums.ui.albumslist.AlbumsListFragment;
import com.th.forge.albums.ui.albumslist.FragmentTransactionCallback;

import static com.th.forge.albums.utils.Constants.ALBUM_ID;

public class MainActivity extends AppCompatActivity implements FragmentTransactionCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToDo: saveInstance
        if(savedInstanceState==null){
            Fragment fragment = new AlbumsListFragment();
            ((AlbumsListFragment) fragment).setFragmentTransactionCallback(this);
            addFragment(fragment);
        }
    }


    public void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack("fragment")
                .commit();
    }

    @Override
    public void showDetail(Long collectionId) {
        Fragment fragment = new AlbumDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ALBUM_ID, collectionId);
        fragment.setArguments(bundle);
        addFragment(fragment);
    }
}
