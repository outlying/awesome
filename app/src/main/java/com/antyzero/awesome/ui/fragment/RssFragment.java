package com.antyzero.awesome.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.antyzero.awesome.R;
import com.antyzero.awesome.network.request.RssRequest;
import com.antyzero.awesome.network.response.pojo.Entry;
import com.antyzero.awesome.tools.IntentUtils;
import com.antyzero.awesome.ui.adapter.RssAdapter;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class RssFragment extends ListViewFragment implements AdapterView.OnItemClickListener {

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
    public void onStart() {
        super.onStart();

        getSpiceManager().execute(
                new RssRequest(),
                new RssRequestListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView listView = getListView();

        listView.setAdapter(rssAdapter);
        listView.setOnItemClickListener(this);
    }

    /**
     * Move to RSS entry news
     *
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Item item = rssAdapter.getItem(position);

        if(!IntentUtils.webBrowser(getActivity(), item.getLink())){
            Toast.makeText(getActivity(), R.string.error_missing_application_web, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Update UI with new data
     *
     * @param items list of new JSON feed entries
     */
    private void updateUi( List<Item> items ) {

        if( items == null ) {
            return;
        }

        itemList.clear();
        itemList.addAll( items );
        rssAdapter.notifyDataSetChanged();
    }

    /**
     * Wait for server response
     */
    private class RssRequestListener implements RequestListener<Channel> {

        @Override
        public void onRequestFailure( SpiceException spiceException ) {
            Toast.makeText(getActivity(), R.string.request_failure_rss, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess( Channel channel ) {
            updateUi( channel.getItems() );
        }
    }
}
