package com.antyzero.awesome.domain;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.antyzero.awesome.domain.MapUtil.Order.ASCENDING;
import static org.fest.assertions.api.Assertions.assertThat;


public class MapUtilTest {

    @Test
    public void testSortByValue() throws Exception {

        Random random = new Random( System.currentTimeMillis() );

        int initialCapacity = 1000;

        Map<String, Integer> testMap = new HashMap<>( initialCapacity );

        for( int i = 0; i < initialCapacity; ++i ) {
            testMap.put( "SomeString" + random.nextInt(), random.nextInt() );
        }

        testMap = MapUtil.sortByValue( testMap, ASCENDING );

        assertThat( testMap.size() ).isEqualTo( initialCapacity );

        Integer previous = null;

        for( Map.Entry<String, Integer> entry : testMap.entrySet() ) {

            assertThat( entry.getValue() ).isNotNull();

            if( previous != null ) {
                assertThat( entry.getValue() >= previous ).isTrue();
            }

            previous = entry.getValue();
        }
    }
}