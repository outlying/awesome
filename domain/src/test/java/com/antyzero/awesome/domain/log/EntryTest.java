package com.antyzero.awesome.domain.log;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

public class EntryTest {

    @Test
    public void testEntryCreation() throws IOException {

        String logLine = IOUtils.toString( this.getClass().getResourceAsStream( "singleline.log" ), "UTF-8" );

        new Entry( logLine );
    }
}