package com.antyzero.awesome.network.request;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;

import static org.fest.assertions.api.Assertions.assertThat;

public class RssRequestTest extends RequestTestCase {

    public void testLoadDataFromNetwork() throws Exception {

        RssRequest request = new RssRequest();
        request.setRestTemplate(getRestTemplate());

        Channel response = request.loadDataFromNetwork();

        assertThat(response).isNotNull();
    }
}