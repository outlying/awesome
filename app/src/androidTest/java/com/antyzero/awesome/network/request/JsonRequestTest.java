package com.antyzero.awesome.network.request;

import android.test.ApplicationTestCase;

import com.antyzero.awesome.AwesomeApplication;
import com.antyzero.awesome.network.AwesomeSpiceService;
import com.antyzero.awesome.network.response.JsonResponse;

import org.springframework.web.client.RestTemplate;

import static org.fest.assertions.api.Assertions.assertThat;

public class JsonRequestTest extends ApplicationTestCase<AwesomeApplication> {

    private JsonRequest request;

    public JsonRequestTest() {
        super( AwesomeApplication.class );
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        AwesomeSpiceService awesomeSpiceService = new AwesomeSpiceService();

        RestTemplate restTemplate = awesomeSpiceService.createRestTemplate();

        request = new JsonRequest();
        request.setRestTemplate( restTemplate );
    }

    public void testLoadDataFromNetwork() throws Exception {

        JsonResponse response = request.loadDataFromNetwork();

        assertThat(response).isNotNull();
    }
}