package com.example.chenyk.chenyknotes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.Utils.ToastUtil;
import com.example.chenyk.chenyknotes.fragment.SlideMenuLeftFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by chenyk on 2016/7/7.
 * 侧滑菜单
 */
public class SlideMenuActivity extends SlidingFragmentActivity implements OnClickListener {

    private Activity mActivity = SlideMenuActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu_main_layout);

        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setSecondaryMenu(R.layout.silde_menu_right_layout);
    }

    @Override
    protected Fragment getSlideMenuFragment() {
        return new SlideMenuLeftFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
