package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.antyzero.awesome.R;

/**
 * Base ListView fragment
 */
public abstract class ListViewFragment extends BaseFragment {

    private ListView listView;
    private TextView textViewEmpty;
    private View containerLoader;

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

        textViewEmpty = (TextView) view.findViewById( R.id.textViewEmpty );

        containerLoader = view.findViewById( R.id.containerLoader );
    }

    @Override
    public void onStart() {
        super.onStart();

        // Sometimes, on fast page switching, loading persists even if list is not empty
        if(listView.getAdapter() != null && listView.getAdapter().getCount() > 0 ){
            hideLoading();
        }
    }

    /**
     * Hide loading and assign view to show when ListView is empty
     */
    protected void hideLoading(){
        listView.setEmptyView( textViewEmpty );
        containerLoader.setVisibility( View.GONE );
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
