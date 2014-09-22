package com.antyzero.awesome.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

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

    @Override
    public void onStart() {
        super.onStart();
        spiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
