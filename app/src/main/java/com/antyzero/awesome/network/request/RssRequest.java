package com.antyzero.awesome.network.request;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import java.net.URI;

/**
 * Get RSS feed
 */
public class RssRequest extends SpringAndroidSpiceRequest<Channel> {

    public static final URI URL = URI.create( "http://www.vg.no/rss/feed/forsiden/?frontId=1" );

    public static final long CACHE_EXPIRY_DURATION = 60_000l;

    public RssRequest() {
        super( Channel.class );
    }

    @Override
    public Channel loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject( URL, Channel.class );
    }
}
