package com.antyzero.awesome.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.antyzero.awesome.R;
import com.antyzero.awesome.ui.adapter.JsonAdapter;
import com.antyzero.awesome.ui.adapter.RssAdapter;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class RssFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private final List<Item> itemList = new ArrayList<>();

    private RssAdapter rssAdapter;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );

        rssAdapter = new RssAdapter( activity, itemList );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_json, container, false );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {

        listView = (ListView) view.findViewById( R.id.listView );

        listView.setAdapter( rssAdapter );
        listView.setOnItemClickListener(this);
    }

    /**
     * Move to RSS entry news
     *
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // TODO add intent
    }
}
