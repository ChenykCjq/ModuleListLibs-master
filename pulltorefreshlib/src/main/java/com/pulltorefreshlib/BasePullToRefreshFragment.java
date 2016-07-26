package com.pulltorefreshlib;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;

import com.pulltorefreshlib.commAdapter.BaseReuseAdapter;

import java.util.List;

import edu.swu.pulltorefreshswipemenulistview.library.PullToRefreshSwipeMenuListView;
import edu.swu.pulltorefreshswipemenulistview.library.pulltorefresh.interfaces.IXListViewListener;

/**
 * Created by chenyk on 2016/6/27.
 */
public abstract class BasePullToRefreshFragment<T> extends Fragment implements AdapterView.OnItemClickListener {

    protected PullToRefreshSwipeMenuListView mListView;
    protected BaseReuseAdapter<T> mAdapter;

    //数据记录
    private int mPageSize;// 每页取得的数据长度
    private int mPageNum;//  开始页码
    private int mTotalCount = -1;
    private int mTotalPageNum = -1;
    //是加载更多，否则是刷新
    protected boolean isAddData;
    //上次加载数据查看到的位置
    private int mLastViewItemIndex;
    private boolean isRefreshing;
    private boolean isFirstRefreshed;//刷新过第一次

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = createAdapter();
        mPageNum = createPageBegin();
        mPageSize = createPageSize();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setXListViewListener(new IXListViewListener() {
            @Override
            public void onLoadMore() {
                requestLoadMore();
            }

            @Override
            public void onRefresh() {
                requestReload();
            }
        });
        if (isAutoFirstRefresh()) requestReload();
    }

    private void requestLoadMore() {
        if (isRefreshing) {
            mListView.stopLoadMore();
            return;
        }
        isRefreshing = true;
        isAddData = true;
        mPageNum++;
        mLastViewItemIndex = mListView.getFirstVisiblePosition();
        requestNewDatas(isAddData, mPageSize, mPageNum);
    }

    //是否刷新过
    protected boolean isDoneFirstRefresh() {
        return isFirstRefreshed;
    }

    //是否进入页面就自动刷新第一次
    protected boolean isAutoFirstRefresh() {
        return true;
    }

    //是否所有数据已经加载了
    protected boolean isAllDataLoaded() {
        if (isUsePageTotalForFlag()) {
            return mTotalPageNum != -1 && mTotalPageNum <= mPageNum;
        }
        return mTotalCount != -1 && mTotalCount <= mAdapter.getCount();
    }

    /**
     * 以总页数作为判断标准
     *
     * @return
     */
    protected boolean isUsePageTotalForFlag() {
        return false;
    }

    protected void requestReload() {
        if (!isFirstRefreshed) {
            isFirstRefreshed = true;
        }
        if (isRefreshing) {
            mListView.stopRefresh();
            return;
        }
        isRefreshing = true;
        isAddData = false;
        mPageNum = createPageBegin();
        mLastViewItemIndex = 0;
        requestNewDatas(isAddData, mPageSize, mPageNum);
    }

    /**
     * 成功获取到数据，进行更新
     *
     * @param total 必须给定 总共还有多少数据
     * @param datas
     */
    protected void refreshListData(int total, List<T> datas) {
        mTotalCount = total;
        refreshData(datas);
    }

    private void refreshData(List<T> datas) {
        mListView.resetListView();
        handleNewData(datas);
        mListView.setSelection(mLastViewItemIndex);
        isRefreshing = false;
    }

    /**
     * 如何处理新数据,一般不需要重写，eporder 需要整体排序，需要重写
     *
     * @param datas
     */
    protected void handleNewData(List<T> datas) {
        if (isAddData) {
            mAdapter.addDatas(datas);
        } else {
            mAdapter.setDatas(datas);
        }
    }

    /**
     * 获取数据失败
     *
     * @param reason
     */
    protected void refreshListDataFail(String reason) {
        mListView.resetListView();
        if (isAddData) {
            mPageNum--;
        }
        isRefreshing = false;
    }

    /**
     * 初始化:生成适配器
     *
     * @return
     */
    protected abstract BaseReuseAdapter<T> createAdapter();

    /**
     * 初始化:一页数据大小
     *
     * @return
     */
    protected abstract int createPageSize();

    /**
     * 初始化:起始页索引
     *
     * @return
     */
    protected int createPageBegin() {
        return 1;
    }

    /**
     * 请求数据,真实加载数据的具体实现
     */
    protected abstract void requestNewDatas(boolean add, int pagesize, int pagenum);

}
