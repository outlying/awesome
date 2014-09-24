package com.antyzero.awesome.domain.log;

import com.antyzero.awesome.domain.MapUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.antyzero.awesome.domain.MapUtil.Order.ASCENDING;
import static com.antyzero.awesome.domain.MapUtil.Order.DESCENDING;

/**
 *
 */
public class LogReader {

    private static final String REGEX_LINE = "(\\d{1,3}(?:\\.\\d{1,3}){3}).+?(\\w+:\\/\\/.+?)\\s";

    private static final Pattern PATTERN_LINE = Pattern.compile( REGEX_LINE );

    /**
     * Reads input stream of log file
     *
     * @throws java.io.IOException
     */
    public static AnalyzeResult analyze(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        Map<String,Integer> hostHits = new HashMap<>();
        Map<String,Integer> urlHits = new HashMap<>();

        while (line != null) {

            Matcher matcher = PATTERN_LINE.matcher( line );

            if( matcher.find() ){

                String host = matcher.group( 1 );
                String URL = matcher.group( 2 );

                incrementOnKey( hostHits, host );
                incrementOnKey( urlHits, URL );

            } else {
                throw new IllegalArgumentException( "Invalid log line format" );
            }

            line = reader.readLine();
        }

        hostHits = MapUtil.sortByValue( hostHits, DESCENDING );
        urlHits = MapUtil.sortByValue( urlHits, DESCENDING );

        return new AnalyzeResult(hostHits,urlHits);
    }

    /**
     * Increment integer value for given key
     *
     * @param map
     * @param key
     */
    private static void incrementOnKey( Map<String, Integer> map, String key ) {

        if(!map.containsKey( key )){
            map.put( key, 1 );
        } else {
            map.put( key, map.get( key ) + 1 );
        }
    }

    /**
     * Aggregates analyze results
     */
    public static final class AnalyzeResult {

        private final Map<String,Integer> hostHits;
        private final Map<String,Integer> urlHits;

        private AnalyzeResult(Map<String,Integer> hostHits,Map<String,Integer> urlHits){
            this.hostHits = hostHits;
            this.urlHits = urlHits;
        }

        public Map<String, Integer> getHostHits() {
            return hostHits;
        }

        public Map<String, Integer> getUrlHits() {
            return urlHits;
        }
    }
}
