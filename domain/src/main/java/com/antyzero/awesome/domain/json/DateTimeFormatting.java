package com.antyzero.awesome.domain.json;

import com.antyzero.awesome.domain.Constants;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Date-time tools for JSON feed
 */
public final class DateTimeFormatting {

    private DateTimeFormatting() {
        throw new IllegalAccessError();
    }

    public static final DateTimeFormatter DATE =
            DateTimeFormat.forPattern( "d MMMM yyyy" ).withLocale( Constants.LOCALE_NORWAY );

    public static final DateTimeFormatter TIME =
            DateTimeFormat.forPattern( "HH:mm" ).withLocale( Constants.LOCALE_NORWAY );
}
