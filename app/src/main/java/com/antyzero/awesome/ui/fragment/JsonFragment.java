package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.antyzero.awesome.R;

/**
 * ...
 */
public final class JsonFragment extends BaseFragment {

    private ListView listView;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_json, container, false );
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {

        listView = (ListView) view.findViewById( R.id.listView );
    }
}
