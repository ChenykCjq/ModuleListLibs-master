package com.example.chenyk.chenyknotes.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.adapter.ViewPagerAdapter;

import java.util.List;

/**
 * Created by chenyk on 2016/7/8.
 * ViewPage辅助
 */
public abstract class BaseViewPageActivity extends FragmentActivity {
    private ViewPager mVPActivity;
    private PagerAdapter mPgAdapter;
    private LinearLayout vpLlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_data_comm_layout);
        initControlView();
    }

    /**
     * 初始化view
     */
    protected void initControlView() {
        mVPActivity = (ViewPager) findViewById(R.id.vp_activity);
        vpLlayout = (LinearLayout) findViewById(R.id.vp_llayout);

        mPgAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                setListFragments());
        mVPActivity.setAdapter(mPgAdapter);
        mVPActivity.setCurrentItem(0);
        mVPActivity.setOnPageChangeListener(new MyOnPageChangeListener());
        int countsPage = setListFragments().size();
        mVPActivity.setOffscreenPageLimit(countsPage);/* 预加载页面 */
        //动态添加文本
        for (int countPage = 0; countPage < countsPage; countPage++) {
            TextView vpTv = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            vpTv.setText(setTitleText()[countPage]);
            vpTv.setGravity(Gravity.CENTER);
            vpTv.setPadding(10, 10, 10, 10);
            if (countPage == 0) {
                vpTv.setBackgroundColor(0xff83cef6);
                vpTv.setTextColor(Color.WHITE);
            } else {
                vpTv.setBackgroundColor(0xfff5f5f5);
                vpTv.setTextColor(0xff999999);
            }
            vpLlayout.addView(vpTv, layoutParams);
            vpTv.setOnClickListener(new MyOnClickListener(countPage));
        }
    }

    /**
     * 页卡切换监听
     */
    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            setStyleWithSelectedLocation(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    /**
     * 根据被选中的位置设置样式
     *
     * @param location
     */
    private void setStyleWithSelectedLocation(int location) {
        for (int i = 0; i < vpLlayout.getChildCount(); i++) {
            TextView mTextView = (TextView) vpLlayout.getChildAt(i);
            if (location == i) {
                mTextView.setBackgroundColor(0xff83cef6);
                mTextView.setTextColor(Color.WHITE);
            } else {
                mTextView.setBackgroundColor(0xfff5f5f5);
                mTextView.setTextColor(0xff999999);
            }
        }
    }

    /**
     * 标题点击监听
     */
    protected class MyOnClickListener implements OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            setStyleWithSelectedLocation(index);
            mVPActivity.setCurrentItem(index);
        }
    }

    /**
     * 设置标题文本
     * @return
     */
    protected abstract String[] setTitleText();

    /**
     * 设置碎片集合
     * @return
     */
    protected abstract List<Fragment> setListFragments();
}
