package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.antyzero.awesome.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ...
 */
public final class LogFileFragment extends BaseFragment {


    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {


    }

    /**
     *
     *
     * @throws IOException
     */
    private void readLogFile() throws IOException {

        InputStream inputStream = getResources().openRawResource( R.raw.varnish );
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        while (line != null) {


        }
    }
}
