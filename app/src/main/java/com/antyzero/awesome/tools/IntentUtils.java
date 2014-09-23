package com.antyzero.awesome.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Simple Intent utils
 */
public class IntentUtils {

    private IntentUtils() {
        throw new IllegalAccessError();
    }

    /**
     * Show web browser for given URL.
     *
     * @param activity required for starting another application
     * @param url      for web browser
     * @return {@code true} if all is OK, {@code false} in case no application can handle this intent
     */
    public static boolean webBrowser( @NonNull Activity activity, @NonNull String url ) {

        boolean result = false;

        Intent browserIntent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );

        if( browserIntent.resolveActivity( activity.getPackageManager() ) != null ) {
            activity.startActivity( browserIntent );
            result = true;
        }

        return result;
    }
}
