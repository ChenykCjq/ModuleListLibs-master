package com.example.chenyk.chenyknotes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.ExpandtabViewActivity;
import com.commonDialog.DownToUpDialog;
import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.Utils.ToastUtil;
import com.example.chenyk.chenyknotes.adapter.FunctionAdapter;
import com.example.chenyk.chenyknotes.bean.FunctionBean;
import com.example.chenyk.chenyknotes.fragment.SlideMenuLeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.karics.library.zxing.ZXingActivity;
import com.pickerview.DatePicker3dDialog;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/7/4.
 */
public class FunctionActivity extends Activity {
    private ListView mListView;
    private FunctionAdapter functionAdapter;
    private List<FunctionBean> functionBeenList = new ArrayList<>();
    private Activity mActivity;

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_layout);
        mActivity = FunctionActivity.this;
        initListViewDatas();
        mListView = (ListView) findViewById(R.id.function_listview);
        functionAdapter = new FunctionAdapter(FunctionActivity.this);
        mListView.setAdapter(functionAdapter);
        functionAdapter.setListDatas(functionBeenList);
        setListener();

    }

    private void initListViewDatas() {
        String[] functionNameStrs = {"高仿IOS时间选择器", "身份证号验证（可获取年龄及性别）", "从下往上弹出的对话框", "ExpandtabView"
                , "PullToRefreshSwipeMenuListView", "二维码生成与扫描", "自定义头像", "slidemenu侧滑菜单"};
        for (int i = 0; i < functionNameStrs.length; i++) {
            FunctionBean functionBean = new FunctionBean();
            functionBean.setFunctionName(functionNameStrs[i]);
            functionBeenList.add(functionBean);
        }
    }

    /**
     * 设置监听器
     */
    private void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:  //高仿IOS时间选择器
                        new DatePicker3dDialog(mActivity, "2016-08-10") {
                            @Override
                            public void onConfirm(String date) {
                                ToastUtil.showNativeShortToast(mActivity, date);
                            }
                        };
                        break;
                    case 1:
                        //
                        startActivity(IdCardVerifyActivity.class);
                        break;
                    case 2:
                        //从下往上的Dialog
                        new DownToUpDialog(mActivity) {
                            @Override
                            public void fristBtnClickListener() {

                            }

                            @Override
                            public void secondBtnClickListener() {

                            }
                        }.setFirstText("拍照").setSecondText("从本地相册选择").show();
                        break;
                    case 3:
                        //ExpandtabView
                        startActivity(ExpandtabViewActivity.class);
                        break;
                    case 4:
                        //PullToRefreshSwipeMenuListView
                        startActivity(PullToRefreshSwipeMenuListViewActivity.class);
                        break;
                    case 5:
                        //二维码生成与扫描
                        startActivity(ZXingActivity.class);
                        break;
                    case 6:
                        startActivity(CustomImageViewActivity.class);
                        break;
                    case 7:
                        startActivity(SlideMenuActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void startActivity(Class StartToActivity) {
        startActivity(new Intent(mActivity, StartToActivity));
    }
}
