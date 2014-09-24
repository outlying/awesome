package com.antyzero.awesome.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.antyzero.awesome.R;
import com.antyzero.awesome.domain.log.LogReader;
import com.antyzero.awesome.network.ExtendedRequestListener;
import com.antyzero.awesome.ui.view.ItemHits;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.SpiceRequest;

import java.io.InputStream;
import java.util.Map;

import static com.antyzero.awesome.domain.log.LogReader.AnalyzeResult;

/**
 * ...
 */
public final class LogFileFragment extends BaseFragment {

    private static final String TAG = "LogFileFragment";
    public static final int MAXIMUM_ITEMS = 5;

    private ViewGroup containerHosts;
    private ViewGroup containerFiles;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
     * @param analyzeResult
     */
    private void updateUi(AnalyzeResult analyzeResult) {

        if (analyzeResult == null) {
            return;
        }

        Map<String, Integer> hostHits = analyzeResult.getHostHits();

        final int sizeHosts = Math.min(hostHits.size(), MAXIMUM_ITEMS);

        int i = 0;

        for (Map.Entry<String, Integer> entry : hostHits.entrySet()) {

            if (i == sizeHosts) {
                break;
            }

            ItemHits itemHits = new ItemHits(getActivity());

            itemHits.setTitle(entry.getKey());
            itemHits.setCounter(String.valueOf(entry.getValue()));

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
}
