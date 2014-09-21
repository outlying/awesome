package com.antyzero.awesome.domain.log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.fest.assertions.api.Assertions.assertThat;

public class LogParserTest {

    private InputStream inputStream;

    @Before
    public void setUp() throws Exception {

        inputStream = getClass().getResourceAsStream( "/varnish" );

        assertThat( inputStream ).isNotNull();
    }

    @Test
    public void testRead() throws Exception {
        LogParser.read( inputStream );
    }

    @After
    public void tearDown() throws Exception {
        inputStream.close();
    }
}