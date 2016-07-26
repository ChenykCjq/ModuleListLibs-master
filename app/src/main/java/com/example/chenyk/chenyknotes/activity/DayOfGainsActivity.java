package com.example.chenyk.chenyknotes.activity;

import android.support.v4.app.Fragment;

import com.example.chenyk.chenyknotes.fragment.SlideMenuLeftFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/7/8.
 */
public class DayOfGainsActivity extends BaseViewPageActivity {
    String[] strTexts = {"按药企", "不明选项", "按医疗机构"};

    @Override
    protected String[] setTitleText() {
        return strTexts;
    }

    @Override
    protected List<Fragment> setListFragments() {
        List<Fragment> mListFragment = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SlideMenuLeftFragment slideMenuLeftFragment = new SlideMenuLeftFragment();
            mListFragment.add(slideMenuLeftFragment);
        }
        return mListFragment;
    }
}
