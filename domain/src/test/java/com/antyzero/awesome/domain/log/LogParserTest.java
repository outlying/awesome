package com.antyzero.awesome.domain.log;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.*;

public class LogParserTest {

    @Test
    public void testRead() throws Exception {

        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("/varnish.log");

        LogParser.read( inputStream );
    }
}