package com.antyzero.awesome.network;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson2.Jackson2ObjectPersisterFactory;
import com.octo.android.robospice.persistence.springandroid.xml.SimpleSerializerObjectPersisterFactory;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.feed.RssChannelHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 */
public class AwesomeSpiceService extends SpringAndroidSpiceService {

    public static final int DESIRED_THREAD_AMOUNT = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    public CacheManager createCacheManager( Application application ) throws CacheCreationException {

        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister( new Jackson2ObjectPersisterFactory( application ) );

        // TODO will it work with RSS ?
        cacheManager.addPersister( new SimpleSerializerObjectPersisterFactory( application ) );

        return cacheManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RestTemplate createRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        RssChannelHttpMessageConverter rssConverter = new RssChannelHttpMessageConverter();

        final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add( jsonConverter );
        listHttpMessageConverters.add( rssConverter );

        restTemplate.setMessageConverters( listHttpMessageConverters );

        return restTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getThreadCount() {

        int cores = Runtime.getRuntime().availableProcessors();

        return Math.min( cores, DESIRED_THREAD_AMOUNT );
    }
}
