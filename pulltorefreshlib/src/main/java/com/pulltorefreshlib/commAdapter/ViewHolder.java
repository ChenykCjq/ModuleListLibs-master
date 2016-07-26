package com.pulltorefreshlib.commAdapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chenyk on 2016/6/27.
 */
public class ViewHolder {
    // 用于存储listView item的容器
    private SparseArray<View> mViews;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
        this.mViews = new SparseArray<View>();
    }

    /**
     * 获取item root view
     *
     * @return
     */
    public View getConvertView() {
        return this.mConvertView;
    }

    /**
     * 查找View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

}

