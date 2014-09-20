package com.antyzero.awesome.domain.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LogParser {


    /**
     * Reads input stream of log file
     *
     * @throws java.io.IOException
     */
    public static void read(InputStream inputStream) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();

        List<Entry> logEntries = new ArrayList<Entry>(  );

        while (line != null) {
            logEntries.add( new Entry( line ) );
        }
    }
}
