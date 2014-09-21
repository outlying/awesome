package com.antyzero.awesome.domain.log;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class EntryTest {

    @Test
    public void testEntryCreation() throws IOException {

        InputStream inputStream = this.getClass()
                .getResourceAsStream( "singleline.log" );

        String logLine = IOUtils.toString( inputStream, "UTF-8" );

        new Entry( logLine );
    }
}