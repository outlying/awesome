package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.antyzero.awesome.R;
import com.antyzero.awesome.domain.log.LogParser;

import java.io.IOException;

/**
 * ...
 */
public final class LogFileFragment extends BaseFragment {

    private static final String TAG = "LogFileFragment";

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        try {
            LogParser.read( getResources().openRawResource( R.raw.varnish ) );
        } catch( IOException e ) {
            Log.w( TAG, "Unable to read log file", e );
        }
    }
}
