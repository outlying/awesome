package com.antyzero.awesome.network.request;

import com.antyzero.awesome.network.response.JsonResponse;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import java.net.URI;

/**
 * Get JSON feed
 */
public class JsonRequest  extends SpringAndroidSpiceRequest<JsonResponse> {

    private static final URI url = URI.create( "http://rexxars.com/playground/testfeed/" );

    public JsonRequest() {
        super( JsonResponse.class );
    }

    @Override
    public JsonResponse loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( url, getResultType() );
    }
}
