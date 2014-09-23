package com.antyzero.awesome.domain.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class LogParser {

    private static final String REGEX_LINE = "(\\d{1,3}(?:\\.\\d{1,3}){3}).+?(\\w+:\\/\\/.+?)\\s";

    private static final Pattern PATTERN_LINE = Pattern.compile( REGEX_LINE );

    /**
     * Reads input stream of log file
     *
     * @throws java.io.IOException
     */
    public static void read( InputStream inputStream ) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        while (line != null) {

            Matcher matcher = PATTERN_LINE.matcher( line );

            if( matcher.find() ){

                String host = matcher.group( 1 );
                String URL = matcher.group( 2 );

            } else {
                throw new IllegalArgumentException( "Invalid line format" );
            }
        }
    }

}
