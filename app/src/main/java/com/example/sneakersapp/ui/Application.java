package com.example.sneakersapp.ui;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.sneakersapp.R;

import java.lang.ref.WeakReference;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Application extends android.app.Application {

    public static final String CHANNEL_ID = "1";

    private static Application application;

    private WeakReference<Activity> mActivity;

    public static Application getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}
