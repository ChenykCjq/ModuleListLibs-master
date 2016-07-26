package com.example.chenyk.chenyknotes.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ImageViewAvatar.CircleImageView;
import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.bean.FunctionBean;
import com.example.chenyk.chenyknotes.comm.commAdapter.BaseCommAdapter;

/**
 * Created by chenyk on 2016/7/4.
 * 功能Adapter
 */
public class FunctionAdapter extends BaseCommAdapter<FunctionBean> {
    public FunctionAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLVItemViewLayoutID() {
        return R.layout.function_listview_item_layout;
    }

    @Override
    public void bindView(int position, FunctionBean data, ViewHolder viewHolder) {
        TextView functionTvName;
        CircleImageView circleImageView;
        circleImageView = (CircleImageView) viewHolder.getView(R.id.function_circle_img);
        functionTvName = (TextView) viewHolder.getView(R.id.function_tv_name);
        functionTvName.setText(data.getFunctionName());
        if (position % 2 == 0) {
            circleImageView.setImageResource(R.mipmap.pic_wangzw_two);
        } else circleImageView.setImageResource(R.mipmap.pic_wangzw_one);
    }
}
