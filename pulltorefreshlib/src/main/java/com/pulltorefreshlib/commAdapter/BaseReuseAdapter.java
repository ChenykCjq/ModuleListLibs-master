package com.pulltorefreshlib.commAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/6/27.
 */
public abstract class BaseReuseAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas;

    public BaseReuseAdapter(Context context) {
        this.mContext = context;
    }


    /**
     * 移除数据
     *
     * @param position
     */
    public T removeData(int position) {
        checkNullDatas();
        if(position < mDatas.size()){
            T t = mDatas.remove(position);
            notifyDataSetChanged();
            return t;
        }
        return null;
    }


    /**
     * 重置整个列表
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        checkNullDatas();
        mDatas.clear();
        if (datas != null) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 对列表添加数据，到末尾
     *
     * @param datas
     */
    public void addDatas(List<T> datas) {
        if (datas != null) {
            checkNullDatas();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addData(T data) {
        if (data != null) {
            checkNullDatas();
            mDatas.add(data);
            notifyDataSetChanged();
        }
    }

    public List<T> getAllDatas(){
        return mDatas;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (position < getCount()) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected void checkNullDatas() {
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder(mContext, parent, getItemViewLayoutId(position));
            convertView = viewHolder.getConvertView();
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        T t = getItem(position);
        bindView(position, t, viewHolder);
        return convertView;
    }

    /**
     * 获取Item的布局文件id
     *
     * @return
     */
    public abstract int getItemViewLayoutId(int position);

    /**
     * 将业务数据绑定到具体的 tag上
     *
     * @param position
     * @param data
     * @param viewHolder
     */
    public abstract void bindView(int position, T data, ViewHolder viewHolder);
}

