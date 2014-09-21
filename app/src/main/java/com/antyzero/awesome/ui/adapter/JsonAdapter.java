package com.antyzero.awesome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by tornax on 21.09.14.
 */
public class JsonAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;

    public JsonAdapter(Context context) {
        layoutInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem( int position ) {
        return null;
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        return null;
    }
}
