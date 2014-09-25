package com.antyzero.awesome.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.antyzero.awesome.R;
import com.antyzero.awesome.domain.UrlUtil;
import com.antyzero.awesome.domain.log.LogReader;
import com.antyzero.awesome.network.ExtendedRequestListener;
import com.antyzero.awesome.ui.view.ItemHits;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.antyzero.awesome.domain.log.LogReader.AnalyzeResult;

/**
 * ...
 */
public final class LogFileFragment extends BaseFragment {

    private static final String TAG = "LogFileFragment";

    // Maximum items
    public static final int MAXIMUM_ITEMS = 5;

    private ViewGroup containerHosts;
    private ViewGroup containerFiles;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {
        super.onStart();

        getSpiceManager().execute(
                new ProcessLog(getResources().openRawResource(R.raw.varnish)),
                new ProcessLogListener());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log_file, container, false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        containerHosts = (ViewGroup) view.findViewById(R.id.containerHosts);
        containerFiles = (ViewGroup) view.findViewById(R.id.containerFiles);
    }

    /**
     * Update with new data
     *
     * @param analyzeResult data source
     */
    private void updateUi(AnalyzeResult analyzeResult) {

        if (analyzeResult == null) {
            return;
        }

        // Add host results

        updateResults( containerHosts, analyzeResult.getHostHits(), new ValueAssignCallback() {

            float maxValue = 0f;

            @Override
            public void assign( ItemHits itemHits, Map.Entry<String, Integer> entry, int position ) {

                if( position == 0 ) {
                    maxValue = entry.getValue();
                }

                itemHits.setTitle( entry.getKey() );
                itemHits.setCounter( String.valueOf( entry.getValue() ) );
                itemHits.setPercent( ( (float) entry.getValue() ) / maxValue );
            }
        } );

        // Add URL results

        updateResults( containerFiles, analyzeResult.getUrlHits(), new ValueAssignCallback() {

            float maxValue = 0f;

            @Override
            public void assign( ItemHits itemHits, Map.Entry<String, Integer> entry, int position ) {

                if( position == 0 ) {
                    maxValue = entry.getValue();
                }

                String url = entry.getKey();

                String fileName = UrlUtil.fileName(url, getString(R.string.unknown_file_name));

                itemHits.setTitle( fileName );
                itemHits.setSubTitle( url );

                itemHits.setCounter( String.valueOf( entry.getValue() ) );
                itemHits.setPercent( ( (float) entry.getValue() ) / maxValue );
            }
        } );
    }

    /**
     * Add results to given container
     *
     * @param container target container
     * @param results map containing results to process
     * @param callback for value assignment
     */
    private void updateResults( ViewGroup container, Map<String, Integer> results, ValueAssignCallback callback ){

        final int sizeHosts = Math.min(results.size(), MAXIMUM_ITEMS);

        int i = 0;

        // Clear old results
        container.removeAllViews();

        for (Map.Entry<String, Integer> entry : results.entrySet()) {

            if (i == sizeHosts) {
                break;
            }

            ItemHits itemHits = new ItemHits(getActivity());

            container.addView(itemHits);

            callback.assign( itemHits, entry, i );

            i++;
        }
    }

    /**
     * Background log reading
     */
    private static final class ProcessLog extends SpiceRequest<AnalyzeResult> {

        private final InputStream inputStream;

        public ProcessLog(InputStream inputStream) {
            super(AnalyzeResult.class);
            this.inputStream = inputStream;
        }

        @Override
        public AnalyzeResult loadDataFromNetwork() throws Exception {
            return LogReader.analyze(inputStream);
        }
    }

    /**
     * Listen to results of log processing
     */
    private final class ProcessLogListener extends ExtendedRequestListener<AnalyzeResult> {

        @Override
        protected void onSuccess(AnalyzeResult analyzeResult) {

            updateUi(analyzeResult);
        }

        @Override
        protected void onFailure(SpiceException spiceException) {

            final String message = getString(R.string.error_log_file);

            Log.w(TAG, message, spiceException);

            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback for value assignment
     */
    private interface ValueAssignCallback {

        /**
         * Called after view is added to view hierarchy
         *
         * @param itemHits current view instance
         * @param valueObject data source
         * @param position which position is current
         */
        void assign( ItemHits itemHits, Map.Entry<String,Integer> valueObject, int position );
    }
}
