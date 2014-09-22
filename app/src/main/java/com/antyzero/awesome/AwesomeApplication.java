package com.antyzero.awesome;

import android.app.Application;
import android.util.Log;

import roboguice.util.temp.Ln;

/**
 *
 */
public class AwesomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (!BuildConfig.DEBUG) {

            // Disable RoboSpice logging
            Ln.getConfig().setLoggingLevel(Log.ERROR);
        }
    }
}
