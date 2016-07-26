package com.example.chenyk.chenyknotes.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.comm.commAdapter.BaseCommAdapter;
import com.example.chenyk.chenyknotes.bean.PullToRefreshSwipeMenuListViewBean;

/**
 * Created by chenyk on 2016/6/28.
 */
public class PullToRefreshSwipeMenuListViewAdapter extends BaseCommAdapter<PullToRefreshSwipeMenuListViewBean> {
    public PullToRefreshSwipeMenuListViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLVItemViewLayoutID() {
        return R.layout.pulltorefresh_swipemenu_listview_item_layout;
    }

    @Override
    public void bindView(int position, PullToRefreshSwipeMenuListViewBean data, BaseCommAdapter.ViewHolder viewHolder) {
        TextView tvName = (TextView) viewHolder.getView(R.id.tv_name);
        TextView tvSex = (TextView) viewHolder.getView(R.id.tv_sex);
        tvName.setText(data.getName());
        tvSex.setText(data.getSex());
    }
}
