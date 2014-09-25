package com.antyzero.awesome.domain;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UrlUtilTest {

    public static final String DEFAULT_VALUE = "default_value";

    @Test
    public void testFileName() throws Exception {

        assertThat(UrlUtil.fileName("http://google.pl/cat.jpg", DEFAULT_VALUE)).startsWith("cat.jpg");
        assertThat(UrlUtil.fileName("http://google.pl/asd/cat.jpg", DEFAULT_VALUE)).startsWith("cat.jpg");
        assertThat(UrlUtil.fileName("http://google.pl/", DEFAULT_VALUE)).startsWith(DEFAULT_VALUE);
        assertThat(UrlUtil.fileName("http://google.pl/nothing/", DEFAULT_VALUE)).startsWith(DEFAULT_VALUE);
        assertThat(UrlUtil.fileName("http://google.pl/?dog=false", DEFAULT_VALUE)).startsWith(DEFAULT_VALUE);
        assertThat(UrlUtil.fileName("http://google.pl/smile/?dog=false", DEFAULT_VALUE)).startsWith(DEFAULT_VALUE);
    }
}