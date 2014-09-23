package com.antyzero.awesome.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.antyzero.awesome.R;
import com.antyzero.awesome.network.request.JsonRequest;
import com.antyzero.awesome.network.response.JsonResponse;
import com.antyzero.awesome.network.response.pojo.Entries;
import com.antyzero.awesome.network.response.pojo.Entry;
import com.antyzero.awesome.tools.IntentUtils;
import com.antyzero.awesome.ui.adapter.JsonAdapter;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.List;

/**
 * ...
 */
public final class JsonFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;

    private JsonAdapter jsonAdapter;

    private final Entries entries = new Entries();

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );

        jsonAdapter = new JsonAdapter( activity, entries );
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

        listView.setAdapter( jsonAdapter );
        listView.setOnItemClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();

        getSpiceManager().getFromCacheAndLoadFromNetworkIfExpired(
                new JsonRequest(),
                JsonRequest.URL,
                JsonRequest.CACHE_EXPIRY_DURATION,
                new JsonRequestListener());
    }

    /**
     * Response to ListView item click
     *
     * {@inheritDoc}
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Entry entry = jsonAdapter.getItem(position);

        if(!IntentUtils.webBrowser(getActivity(), entry.getLink())){
            Toast.makeText(getActivity(), R.string.error_missing_application_web, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Update UI with new data
     *
     * @param entryList list of new JSON feed entries
     */
    private void updateUi( List<Entry> entryList ) {

        if( entryList == null ) {
            return;
        }

        entries.clear();
        entries.addAll( entryList );
        jsonAdapter.notifyDataSetChanged();
    }

    /**
     * Wait for server response
     */
    private class JsonRequestListener implements RequestListener<JsonResponse> {

        @Override
        public void onRequestFailure( SpiceException spiceException ) {
            Toast.makeText(getActivity(),R.string.request_failure_json, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess( JsonResponse jsonResponse ) {
            updateUi( jsonResponse );
        }
    }
}
