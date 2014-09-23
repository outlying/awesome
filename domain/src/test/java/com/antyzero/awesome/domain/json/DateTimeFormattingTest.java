package com.antyzero.awesome.domain.json;

import org.junit.Test;

public class DateTimeFormattingTest {

    @Test
    public void testDateFormatting(){

        DateTimeFormatting.DATE.parseLocalDate( "29 Mars 2012" );
        DateTimeFormatting.DATE.parseLocalDate( "18 Januar 2012" );
        DateTimeFormatting.DATE.parseLocalDate( "13 Februar 2012" );
        DateTimeFormatting.DATE.parseLocalDate( "7 Mars 2011" );
    }
}