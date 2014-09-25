package com.antyzero.awesome.domain;

import java.net.URI;

/**
 *
 */
public class UrlUtil {

    private UrlUtil() {
        throw new IllegalAccessError();
    }

    /**
     * Get file name from given url
     *
     * @param url given
     * @param defaultValue use this value if file name is not found
     * @return file name or default value
     */
    public static String fileName( String url, String defaultValue){

        String result = defaultValue;

        final String path = URI.create(url).getPath();

        String[] parts = path.split("/");

        if( parts.length > 0 && !path.endsWith("/") ){

            result = parts[parts.length - 1];
        }

        return result;
    }
}
