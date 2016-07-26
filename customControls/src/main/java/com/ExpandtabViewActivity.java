package com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.customcontrolsmodule.R;
import com.expandTabView.ExpandTabFilterView;
import com.expandTabView.ExpandTabView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chenyk on 2016/6/29.
 */
public class ExpandtabViewActivity extends Activity {
    private ExpandTabView mExpandTabView;
    private Activity mActivity;
    private ArrayList<View> mViewArray;
    private ArrayList<String> mTextArray;
    private ExpandTabFilterView expandTabFilterViewLeft, expandTabFilterViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);
        mActivity = ExpandtabViewActivity.this;
        initView();
        initTabViewData();
        setListener();
    }

    private void initView() {
        mExpandTabView = (ExpandTabView) findViewById(R.id.expandtab_view);
    }

    /**
     * 初始化expandtabView数据
     */
    private void initTabViewData() {
        mViewArray = new ArrayList<>();
        mTextArray = new ArrayList<>();
        expandTabFilterViewLeft = new ExpandTabFilterView(mActivity,
                Arrays.asList("所有", "国语", "粤语", "闽南语", "英语", "韩语"),
                Arrays.asList("0", "1", "2", "3", "4", "5"));
        expandTabFilterViewRight = new ExpandTabFilterView(mActivity,
                Arrays.asList("所有", "张杰", "韩红", "杨坤", "周杰伦", "那英", "杨钰莹"),
                Arrays.asList("10", "20", "30", "40", "50", "60", "70"));
        mViewArray.add(expandTabFilterViewLeft);
        mViewArray.add(expandTabFilterViewRight);
        mTextArray.add("歌曲语种");
        mTextArray.add("演唱歌手");
        mExpandTabView.setValue(mTextArray, mViewArray);
    }

    private void setListener() {
        expandTabFilterViewLeft.setOnSelectListener(new ExpandTabFilterView.OnSelectListener() {
            @Override
            public void getValue(int id, String showText) {
                onRefreshData(id, expandTabFilterViewLeft, showText);
            }
        });
        expandTabFilterViewRight.setOnSelectListener(new ExpandTabFilterView.OnSelectListener() {
            @Override
            public void getValue(int id, String showText) {
                onRefreshData(id, expandTabFilterViewRight, showText);
            }
        });
    }

    /**
     * 根据过滤器选择内容进行数据筛选及列表更新
     *
     * @param tabView
     * @param showText
     */
    private void onRefreshData(int id, View tabView, String showText) {
        mExpandTabView.onPressBack();
        mExpandTabView.isSetStrToTabView(tabView, showText, "所有");
        Toast.makeText(ExpandtabViewActivity.this, "筛选结果：文本 = " + showText + ",Id = " + id, Toast.LENGTH_SHORT).show();
    }
}
