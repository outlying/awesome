package com.antyzero.awesome.network.request;

import android.test.ApplicationTestCase;

import com.antyzero.awesome.AwesomeApplication;
import com.antyzero.awesome.network.AwesomeSpiceService;

import org.springframework.web.client.RestTemplate;

/**
 * TestCase for Spring requests
 */
public abstract class RequestTestCase extends ApplicationTestCase<AwesomeApplication> {

    private RestTemplate restTemplate;

    public RequestTestCase() {
        super(AwesomeApplication.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        AwesomeSpiceService awesomeSpiceService = new AwesomeSpiceService();

        restTemplate = awesomeSpiceService.createRestTemplate();
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
