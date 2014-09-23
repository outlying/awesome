package com.antyzero.awesome.domain;

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

    public static final String PATTERN_TIME = "HH:mm";
    public static final String PATTERN_DATE = "d MMMM yyyy";
    public static final String PATTERN_DATE_TIME = PATTERN_DATE + ", " + PATTERN_TIME;

    public static final DateTimeFormatter DATE =
            DateTimeFormat.forPattern( PATTERN_DATE ).withLocale( Constants.LOCALE_NORWAY );

    public static final DateTimeFormatter TIME =
            DateTimeFormat.forPattern( PATTERN_TIME ).withLocale( Constants.LOCALE_NORWAY );

    public static final DateTimeFormatter DATE_TIME =
            DateTimeFormat.forPattern( PATTERN_DATE_TIME ).withLocale( Constants.LOCALE_NORWAY );
}
