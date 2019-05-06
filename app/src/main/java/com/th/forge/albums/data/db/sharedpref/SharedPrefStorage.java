package com.th.forge.albums.data.db.sharedpref;

import android.content.Context;
import android.preference.PreferenceManager;

public class SharedPrefStorage {
    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private Context context;

    //ToDo: DI
    public SharedPrefStorage(Context context) {
        this.context = context;
    }

    public String getStoredQuery() {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY, null);
    }

    public void storeQuery(String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }
}
