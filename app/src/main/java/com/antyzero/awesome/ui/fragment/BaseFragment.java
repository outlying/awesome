package com.antyzero.awesome.ui.fragment;

import android.app.Fragment;

import com.antyzero.awesome.network.AwesomeSpiceService;
import com.octo.android.robospice.SpiceManager;

/**
 * Some common logic
 */
public abstract class BaseFragment extends Fragment {

    private SpiceManager spiceManager = new SpiceManager( AwesomeSpiceService.class );

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
