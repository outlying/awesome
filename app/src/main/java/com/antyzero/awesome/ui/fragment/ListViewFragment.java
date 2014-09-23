package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.antyzero.awesome.R;

/**
 * Base ListView fragment
 */
public abstract class ListViewFragment extends BaseFragment {

    private ListView listView;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate( R.layout.fragment_listview, container, false );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {

        listView = (ListView) view.findViewById( R.id.listView );
        listView.setEmptyView( view.findViewById( R.id.textViewEmpty ) );
    }

    /**
     * ListView access
     *
     * @return ListView
     */
    protected ListView getListView() {
        return listView;
    }
}
