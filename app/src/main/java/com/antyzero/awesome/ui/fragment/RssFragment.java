package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.antyzero.awesome.R;

/**
 *
 */
public final class RssFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;

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

        // listView.setAdapter(  );
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
