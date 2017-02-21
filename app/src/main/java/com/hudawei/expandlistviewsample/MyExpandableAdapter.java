package com.hudawei.expandlistviewsample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hudawei on 2017/2/21.
 *
 */

public class MyExpandableAdapter extends ExpandableAdapter<String,String> {
    public MyExpandableAdapter(Context context, List<String> groups, List<List<String>> items) {
        super(context, groups, items);
    }

    @Override
    public GroupViewHolder createGroupViewHolder() {
        return new MyGroupViewHolder();
    }

    @Override
    public ItemViewHolder createItemViewHolder() {
        return new MyItemViewHolder();
    }

    class MyGroupViewHolder implements GroupViewHolder<String>{
        private TextView text_group;

        @Override
        public View inflateBindView(Context context) {
           return LayoutInflater.from(context).inflate(R.layout.expand_group,null);
        }

        @Override
        public void initBindView(int groupPosition, View convertView) {
            text_group=(TextView)convertView.findViewById(R.id.text_group);
        }

        @Override
        public void bindViewData(int groupPosition, View convertView, String data) {
            text_group.setText(data);
        }
    }

    class MyItemViewHolder implements ItemViewHolder<String>{
        private TextView text_item;
        @Override
        public View inflateBindView(Context context) {
            return LayoutInflater.from(context).inflate(R.layout.expand_item,null);
        }

        @Override
        public void initBindView(int groupPosition, int childPosition, View convertView) {
            text_item=(TextView)convertView.findViewById(R.id.text_item);
        }

        @Override
        public void bindViewData(int groupPosition, int childPosition, View convertView, String data) {
            text_item.setText(data);
        }
    }
}
