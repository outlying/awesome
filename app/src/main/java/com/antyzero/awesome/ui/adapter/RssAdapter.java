package com.antyzero.awesome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antyzero.awesome.R;
import com.antyzero.awesome.domain.DateTimeFormatting;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;

import java.util.List;

/**
 *
 */
public class RssAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final List<Item> itemList;

    public RssAdapter( Context context, List<Item> itemList ) {
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem( int position ) {
        return itemList.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return getItem( position ).getGuid().hashCode();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        ViewHolder viewHolder;

        if( convertView == null ) {

            convertView = layoutInflater.inflate( R.layout.adapter_news_item, parent, false );

            viewHolder = new ViewHolder( convertView );
            convertView.setTag( viewHolder );
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem( position );

        String dateTime = DateTimeFormatting.DATE_TIME.print( item.getPubDate().getTime() );

        viewHolder.textViewTitle.setText( item.getTitle() );
        viewHolder.textViewDescription.setText( item.getDescription().getValue() );
        viewHolder.textViewDateTime.setText( dateTime );

        return convertView;
    }

    /**
     * ViewHolder pattern
     */
    private static final class ViewHolder {

        private final TextView textViewTitle;
        private final TextView textViewDescription;
        private final TextView textViewDateTime;

        public ViewHolder( View view ) {

            textViewTitle = (TextView) view.findViewById( R.id.textViewTitle );
            textViewDescription = (TextView) view.findViewById( R.id.textViewDescription );
            textViewDateTime = (TextView) view.findViewById( R.id.textViewDateTime );
        }
    }
}
