package com.antyzero.awesome.domain.json;

import com.antyzero.awesome.domain.DateTimeFormatting;

import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class DateTimeFormattingTest {

    @Test
    public void testDateFormatting(){

        DateTimeFormatter dateFormatter = DateTimeFormatting.DATE;

        dateFormatter.parseLocalDate( "29 mars 2012" );
        dateFormatter.parseLocalDate( "18 januar 2012" );
        dateFormatter.parseLocalDate( "13 februar 2012" );
        dateFormatter.parseLocalDate( "7 mars 2011" );
    }

    @Test
    public void testTimeFormatting(){

        DateTimeFormatter timeFormatter = DateTimeFormatting.TIME;

        timeFormatter.parseLocalDate( "00:00" );
        timeFormatter.parseLocalDate( "23:45" );
        timeFormatter.parseLocalDate( "9:12" );
    }
}