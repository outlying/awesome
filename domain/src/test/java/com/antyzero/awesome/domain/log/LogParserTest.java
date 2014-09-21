package com.antyzero.awesome.domain.log;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;

import static org.fest.assertions.api.Assertions.assertThat;

public class LogParserTest {

    public static final String VARNISH_LOG = "/varnish.log";

    private InputStream inputStream;

    @Before
    public void setUp() throws Exception {

        InputStream inputStream = LogParser.class.getResourceAsStream( VARNISH_LOG );

        assertThat( inputStream ).isNotNull();
    }

    @Test
    public void testRead() throws Exception {
        //LogParser.read( inputStream );
    }
}