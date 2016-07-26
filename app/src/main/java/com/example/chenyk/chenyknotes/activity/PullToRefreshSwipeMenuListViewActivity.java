package com.example.chenyk.chenyknotes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.adapter.PullToRefreshSwipeMenuListViewAdapter;
import com.example.chenyk.chenyknotes.bean.PullToRefreshSwipeMenuListViewBean;

import java.util.LinkedList;
import java.util.List;

import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;
import edu.swu.pulltorefreshswipemenulistview.library.util.Utils;

public class PullToRefreshSwipeMenuListViewActivity extends Activity implements IXListViewListener, OnClickListener {
    private PullToRefreshSwipeMenuListView listView;
    private PullToRefreshSwipeMenuListViewAdapter adapterDemo;
    private List<PullToRefreshSwipeMenuListViewBean> mList = new LinkedList<>();
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh_swipemenu_listview_layout);
        initView();
        setListener();
        initListViewDatas();
        mHandler = new Handler();
        adapterDemo = new PullToRefreshSwipeMenuListViewAdapter(this);
        adapterDemo.setListDatas(mList);
        listView.setAdapter(adapterDemo);
        listView.setXListViewListener(this);
    }

    /**
     * 控件初始化
     */
    private void initView() {
        listView = (PullToRefreshSwipeMenuListView) findViewById(R.id.chenyk_listview);
    }

    /**
     * 设置监听
     */
    private void setListener() {
    }

    /**
     * 初始化数据
     */
    private void initListViewDatas() {
        String[] strsName = {"诸葛亮", "周瑜", "曹操","陆逊","司马懿","黄盖"};
        String[] strsSex = {"男", "女"};
        for (int i = 0; i < strsName.length; i++) {
            PullToRefreshSwipeMenuListViewBean modleDemo = new PullToRefreshSwipeMenuListViewBean();
            modleDemo.setName(strsName[i]);
            modleDemo.setSex(strsSex[i % 2]);
            mList.add(modleDemo);
        }

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setRefreshTime(Utils.getCurrentTime());
                adapterDemo.addListDatas2Header(mList);
                listView.stopRefresh();
                listView.stopLoadMore();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setRefreshTime(Utils.getCurrentTime());
                adapterDemo.addListDatas2Footer(mList);
                listView.stopRefresh();
                listView.stopLoadMore();
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }

    }
}
