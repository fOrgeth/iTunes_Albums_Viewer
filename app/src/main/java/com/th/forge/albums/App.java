package com.th.forge.albums;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.th.forge.albums.data.db.sharedpref.SharedPrefStorage;
import com.th.forge.albums.data.network.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ApiService apiService;
    private Retrofit retrofit;
    private static App instance;

    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (retrofit == null) {
            retrofit = buildRetrofit();
        }
        if (apiService == null) {
            apiService = retrofit.create(ApiService.class);
        }
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(buildHttpClient())
                .build();
    }

    private Gson getGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    private OkHttpClient buildHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public static ApiService getApiService() {
        return apiService;
    }

    //ToDO: DI
    public SharedPrefStorage getSharedPrefStorage() {
        return new SharedPrefStorage(this);
    }

}
