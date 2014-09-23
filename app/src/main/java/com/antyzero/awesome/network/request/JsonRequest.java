package com.antyzero.awesome.network.request;

import com.antyzero.awesome.network.response.JsonResponse;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import java.net.URI;

/**
 * Get JSON feed
 */
public class JsonRequest extends SpringAndroidSpiceRequest<JsonResponse> {

    public static final URI URL = URI.create( "http://rexxars.com/playground/testfeed/" );

    public static final long CACHE_EXPIRY_DURATION = 60_000l;

    public JsonRequest() {
        super( JsonResponse.class );
    }

    @Override
    public JsonResponse loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( URL, getResultType() );
    }
}
