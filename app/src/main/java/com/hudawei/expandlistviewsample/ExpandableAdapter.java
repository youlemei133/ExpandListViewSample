package com.hudawei.expandlistviewsample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by hudawei on 2017/2/21.
 *
 */

public abstract class ExpandableAdapter<E,T> extends BaseExpandableListAdapter {
    private Context mContext;
    private List<E> mGroups;
    private List<List<T>> mItems;

    public ExpandableAdapter(Context context,List<E> groups, List<List<T>> items){
        mContext=context;
        mGroups=groups;
        mItems=items;
        if(mGroups.size()!=mItems.size()||mGroups.size()==0){
            throw new IllegalArgumentException("有"+groups.size()+"组，Item个数有"+items.size()+"\t请确保组个数与Item个数对应");
        }
    }
    /**
     * 获取组的个数
     * @return
     */
    @Override
    public int getGroupCount() {
        return mGroups!=null?mGroups.size():0;
    }

    /**
     * 指定组下面的Item个数
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        return mItems.get(groupPosition).size();
    }

    /**
     * 指定组绑定的数据
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    /**
     * 指定组中指定Item绑定的数据
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mItems.get(groupPosition).get(childPosition);
    }

    /**
     * 指定组的Id
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 指定组中指定Item的Id
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*1000+childPosition;
    }

    /**
     * id是否指向固定的Object
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * 获取指定组件的View
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder mHolder;
        if(convertView==null){
            mHolder=createGroupViewHolder();
            convertView= mHolder.inflateBindView(mContext);
            mHolder.initBindView(groupPosition,convertView);
            convertView.setTag(mHolder);
        }else{
            mHolder= (GroupViewHolder) convertView.getTag();
        }
        mHolder.bindViewData(groupPosition,convertView,mGroups.get(groupPosition));
        return convertView;
    }

    /**
     * 获取指定Item的View
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemViewHolder mHolder;
        if(convertView==null){
            mHolder=createItemViewHolder();
            convertView= mHolder.inflateBindView(mContext);
            mHolder.initBindView(groupPosition,childPosition,convertView);
            convertView.setTag(mHolder);
        }else{
            mHolder= (ItemViewHolder) convertView.getTag();
        }
        mHolder.bindViewData(groupPosition,childPosition,convertView,mItems.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public abstract GroupViewHolder createGroupViewHolder();
    public abstract ItemViewHolder createItemViewHolder();

    interface GroupViewHolder<E>{
        View inflateBindView(Context context);
        void initBindView(int groupPosition,View convertView);
        void bindViewData(int groupPosition,View convertView,E data);
    }
    interface ItemViewHolder<T>{
        View inflateBindView(Context context);
        void initBindView(int groupPosition,int childPosition,View convertView);
        void bindViewData(int groupPosition,int childPosition,View convertView,T data);
    }
}
