package com.antyzero.awesome.domain.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents single log line
 */
public final class Entry {

    private static final String REGEX_LINE = "(\\d{1,3}(?:\\.\\d{1,3}){3}).+?(\\w+:\\/\\/.+?)\\s";

    private static final Pattern PATTERN_LINE = Pattern.compile( REGEX_LINE );

    private final String host;
    private final String URL;

    /**
     * Creates entry object from log line
     *
     * @param logLine single line
     */
    Entry( CharSequence logLine ) {

        Matcher matcher = PATTERN_LINE.matcher( logLine );

        if( matcher.find() ){

            this.host = matcher.group( 1 );
            this.URL = matcher.group( 2 );
        }

        throw new IllegalArgumentException(
                String.format( "Unable to parse log line: %s", logLine ) );
    }

    public String getHost() {
        return host;
    }

    public String getURL() {
        return URL;
    }
}
