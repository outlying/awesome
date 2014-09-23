package com.antyzero.awesome.network;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

/**
 * ...
 */
public abstract class ExtendedRequestListener<T> implements RequestListener<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onRequestFailure( SpiceException spiceException ) {
        preResult();
        onFailure( spiceException );
        postResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void onRequestSuccess( T t ) {
        preResult();
        onSuccess( t );
        postResult();
    }

    /**
     * Called when request succeeded
     *
     * @param t server response
     */
    protected abstract void onSuccess( T t );

    /**
     * Called when request failed for some reason
     *
     * @param spiceException RoboSpice exception
     */
    protected abstract void onFailure( SpiceException spiceException );

    /**
     * Override this method if some pre result action is needed, despite the result
     */
    public void preResult() {

    }

    /**
     * Override this method if some post result action is needed, despite the result
     */
    public void postResult() {

    }
}
