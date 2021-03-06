package com.antyzero.awesome.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public final class MapUtil {

    private MapUtil() {
        throw new IllegalAccessError();
    }

    /**
     * Sort map by key value
     *
     * @param map to sort
     * @param order desired order
     * @param <K> type of key
     * @param <V> type of value, has to implement Comparable
     * @return sorted Map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map, Order order ) {

        List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );

        Comparator<Map.Entry<K, V>> comparatorAscending = new Comparator<Map.Entry<K, V>>() {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return ( o1.getValue() ).compareTo( o2.getValue() );
            }
        };

        Comparator<Map.Entry<K, V>> comparatorDescending = new Comparator<Map.Entry<K, V>>() {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return ( o1.getValue() ).compareTo( o2.getValue() )  * -1;
            }
        };

        Comparator<Map.Entry<K, V>> comparator;

        if(Order.ASCENDING.equals( order )){
            comparator = comparatorAscending;
        } else {
            comparator = comparatorDescending;
        }

        Collections.sort( list, comparator );

        Map<K, V> result = new LinkedHashMap<>();

        for (Map.Entry<K, V> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }

        return result;
    }

    /**
     * Argument enum for sorting
     */
    public enum Order {
        ASCENDING, DESCENDING;
    }
}
