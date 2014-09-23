package com.antyzero.awesome.network.response.pojo;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * ...
 */
public class Entries extends ArrayList<Entry> {

    /**
     * Newest entries at the top
     */
    public static final Comparator<Entry> ORDER_DATE_DESC = new Comparator<Entry>() {

        @Override
        public int compare( Entry lhs, Entry rhs ) {
            return lhs.compareTo( rhs ) * -1;
        }
    };
}
