package com.example.chenyk.chenyknotes.comm.commAdapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/6/28.
 * listview通用adapter
 */
public abstract class BaseCommAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mListViewDatas;

    public BaseCommAdapter(Context context) {
        mContext = context;
        if (mListViewDatas == null) {
            mListViewDatas = new ArrayList<>();
        }
    }

    /**
     * 获取所有数据
     *
     * @return
     */
    public List<T> getAllListDatas() {
        return mListViewDatas;
    }

    /**
     * 移除指定位置数据
     *
     * @param position
     */
    public T removeData(int position) {
        if (position < mListViewDatas.size()) {
            T t = mListViewDatas.remove(position);
            notifyDataSetChanged();
            return t;
        }
        return null;
    }

    /**
     * 添加数据，从尾部添加
     *
     * @param listDatas
     */
    public void addListDatas2Footer(List<T> listDatas) {
        if (listDatas != null) {
            mListViewDatas.addAll(listDatas);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据，从尾部添加
     *
     * @param listData
     */
    public void addListData2Footer(T listData) {
        if (listData != null) {
            mListViewDatas.add(listData);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据，从头部添加
     *
     * @param listDatas
     */
    public void addListDatas2Header(List<T> listDatas) {
        if (listDatas != null) {
            mListViewDatas.addAll(0, listDatas);
            notifyDataSetChanged();
        }
    }

    /**
     * 重置整个列表的数据
     *
     * @param listDatas
     */
    public void setListDatas(List<T> listDatas) {
        mListViewDatas.clear();
        if (listDatas != null) {
            mListViewDatas.addAll(listDatas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mListViewDatas == null ? 0 : mListViewDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mListViewDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mContext, parent, getLVItemViewLayoutID());
            convertView = viewHolder.getConvertView();
        } else viewHolder = (ViewHolder) convertView.getTag();
        T t = getItem(position);
        bindView(position, t, viewHolder);
        return convertView;
    }

    protected class ViewHolder {
        // 用于存储listView item的容器
        private SparseArray<View> mItemViews = new SparseArray<>();
        private View mConvertView;

        public ViewHolder(Context context, ViewGroup parent, int layoutId) {
            this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
            this.mConvertView.setTag(this);
            this.mItemViews = new SparseArray<>();
        }

        /**
         * 获取listView中item对应的view
         *
         * @return
         */
        public View getConvertView() {
            return this.mConvertView;
        }

        /**
         * 查找View
         *
         * @param viewPosition
         * @return
         */
        public View getView(int viewPosition) {
            View view = mItemViews.get(viewPosition);
            if (view == null) {
                view = mConvertView.findViewById(viewPosition);
                mItemViews.put(viewPosition, view);
            }
            return view;
        }
    }

    /**
     * 获取listview中的item布局
     *
     * @return
     */
    public abstract int getLVItemViewLayoutID();

    /**
     * 将业务数据绑定到具体的 tag上
     *
     * @param position
     * @param data
     * @param viewHolder
     */
    public abstract void bindView(int position, T data, ViewHolder viewHolder);
}
