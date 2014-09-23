package com.antyzero.awesome.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.antyzero.awesome.R;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;

import java.util.List;

/**
 *
 */
public class RssAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;
    private final List<Item> itemList;

    public RssAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getGuid().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){

            convertView = layoutInflater.inflate(R.layout.adapter_rss, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);

        viewHolder.textViewTitle.setText(item.getTitle());

        return convertView;
    }

    /**
     * ViewHolder pattern
     */
    private static final class ViewHolder {

        private final TextView textViewTitle;

        public ViewHolder(View view) {

            textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
        }
    }
}
