package com.antyzero.awesome.network;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 */
public class AwesomeSpiceService extends SpringAndroidSpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {

        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister(new JacksonObjectPersisterFactory(application));

        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        // TODO add atom/rss converter

        final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add(jsonConverter);

        restTemplate.setMessageConverters(listHttpMessageConverters);

        return restTemplate;
    }
}
