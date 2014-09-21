package com.antyzero.awesome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.antyzero.awesome.network.response.pojo.Entries;
import com.antyzero.awesome.network.response.pojo.Entry;

/**
 * Created by tornax on 21.09.14.
 */
public class JsonAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final Entries entries;

    public JsonAdapter( Context context, Entries entries ) {
        this.entries = entries;
        layoutInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Entry getItem( int position ) {
        return entries.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return getItem( position ).hashCode();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        // TODO views

        return null;
    }
}
