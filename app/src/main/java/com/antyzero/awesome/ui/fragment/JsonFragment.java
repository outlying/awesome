package com.antyzero.awesome.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.antyzero.awesome.R;
import com.antyzero.awesome.ui.adapter.JsonAdapter;

/**
 * ...
 */
public final class JsonFragment extends BaseFragment {

    private ListView listView;

    private ListAdapter jsonAdapter;

    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );

        jsonAdapter = new JsonAdapter( activity );
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_json, container, false );
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {

        listView = (ListView) view.findViewById( R.id.listView );

        listView.setAdapter( jsonAdapter );
    }
}
