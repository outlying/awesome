package com.antyzero.awesome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antyzero.awesome.R;
import com.antyzero.awesome.network.response.pojo.Entries;
import com.antyzero.awesome.network.response.pojo.Entry;

/**
 * Adapter for JSON data feed items
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

        ViewHolder viewHolder;

        if( convertView == null ) {

            convertView = layoutInflater.inflate( R.layout.adapter_json, parent, false );

            viewHolder = new ViewHolder( convertView );
            convertView.setTag( viewHolder );
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        Entry entry = getItem( position );

        viewHolder.textViewTitle.setText( entry.getTitle() );

        return convertView;
    }

    /**
     * ViewHolder pattern
     */
    private static final class ViewHolder {

        private final TextView textViewTitle;

        public ViewHolder( View view ) {

            textViewTitle = (TextView) view.findViewById( R.id.textViewTitle );
        }
    }
}
