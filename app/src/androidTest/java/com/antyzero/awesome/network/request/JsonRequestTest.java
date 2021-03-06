package com.antyzero.awesome.network.request;

import com.antyzero.awesome.network.response.JsonResponse;

import static org.fest.assertions.api.Assertions.assertThat;

public class JsonRequestTest extends RequestTestCase {

    public void testLoadDataFromNetwork() throws Exception {

        JsonRequest request = new JsonRequest();
        request.setRestTemplate(getRestTemplate());

        JsonResponse response = request.loadDataFromNetwork();

        assertThat(response).isNotNull();
    }
}