package com.th.forge.albums.ui;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.th.forge.albums.R;
import com.th.forge.albums.ui.albumslist.FragmentTransactionCallback;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import static com.th.forge.albums.utils.Constants.ALBUM_ID;

public class MainActivity extends AppCompatActivity implements FragmentTransactionCallback {

    private NavController navController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupWithNavController(toolbar, navController);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) null);
    }

    @Override
    public void showDetail(View view, Long collectionId) {
        Bundle bundle = new Bundle();
        bundle.putLong(ALBUM_ID, collectionId);
        //ToDo: navController.navigate(R.id.action_list_to__detail, bundle); - don't work. Why?
        Navigation.findNavController(view).navigate(R.id.action_list_to__detail, bundle);
    }
}
