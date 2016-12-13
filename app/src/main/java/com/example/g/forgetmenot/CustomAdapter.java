package com.example.g.forgetmenot;

/**
 * Created by G on 13/12/16.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by G on 18/10/16.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems){
        this.context = context;
        this.rowItems = rowItems;
    }

    private class ViewHolder {
        TextView item_name;
        TextView time;
    }

    @Override
    public int getCount() {return rowItems.size();}

    public Object getItem(int position){return rowItems.get(position);}

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item,null);
            holder = new ViewHolder();

            holder.item_name = (TextView) convertView.findViewById(R.id.itemTV);
            holder.time = (TextView)convertView.findViewById(R.id.timeTV);


            RowItem row_post = rowItems.get(position);

            holder.item_name.setText(row_post.getItem_name());
            holder.time.setText(row_post.getTime());
        }


        return convertView;
    }



}
