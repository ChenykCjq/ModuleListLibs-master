package com.expandTabView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.customcontrolsmodule.R;

import java.util.ArrayList;

/**
 * Created by chenyk on 2016/6/29.
 * 筛选菜单控件
 * 下拉动画，动态生成头部按钮个数
 */
public class ExpandTabView extends LinearLayout implements PopupWindow.OnDismissListener {

    private View selectedView;
    private ArrayList<String> mTextArray = new ArrayList<>();
    private ArrayList<View> mViewArrays = new ArrayList<>();
    private ArrayList<RelativeLayout> mViewArray = new ArrayList<>();
    private ArrayList<TextView> mTextViews = new ArrayList<>();
    private ArrayList<View> mViews = new ArrayList<>();
    private Context mContext;
    private final int SMALL = 0;
    private int displayWidth;
    private int displayHeight;
    private PopupWindow popupWindow;
    private int selectPosition;

    public ExpandTabView(Context context) {
        super(context);
        init(context);
    }

    public ExpandTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化页面
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        setOrientation(LinearLayout.HORIZONTAL);
    }

    /**
     * 根据选择的位置设置tabitem显示的值
     */
    public void setTitle(String valueText, int position) {
        if (position < mTextViews.size()) {
            mTextViews.get(position).setText(valueText);
        }
    }

    /**
     * 根据选择的位置获取tabitem显示的值
     */
    public String getTitle(int position) {
        if (position < mTextViews.size() && mTextViews.get(position).getText() != null) {
            return mTextViews.get(position).getText().toString();
        }
        return "";
    }

    /**
     * 设置tabitem的个数和初始值
     */
    public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
        if (mContext == null) {
            return;
        }
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mTextArray = textArray;
        mViewArrays = viewArray;
        for (int i = 0; i < viewArray.size(); i++) {
            final RelativeLayout r = new RelativeLayout(mContext);
            int maxHeight = (int) (displayHeight * 0.7);
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, maxHeight);
            r.addView(viewArray.get(i), rl);
            mViewArray.add(r);
            r.setTag(SMALL);
            View layout = inflater.inflate(R.layout.expand_tabview_layout, this, false);
            addView(layout);
            mViews.add(layout);
            LinearLayout line = (LinearLayout) inflater.inflate(R.layout.expand_tab_view_textview, this, false);
            if (i < viewArray.size() - 1) {
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(2, LinearLayout.LayoutParams.FILL_PARENT);
                addView(line, lp);
            }
            TextView textView = (TextView) layout.findViewById(R.id.expand_tabview_tv);
            mTextViews.add(textView);
            layout.setTag(i);
            textView.setText(mTextArray.get(i));
            r.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPressBack();
                }
            });
            r.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));
            layout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedView = view;
                    selectPosition = (Integer) selectedView.getTag();
                    startAnimation();
                    if (mOnButtonClickListener != null) {
                        mOnButtonClickListener.onClick(selectPosition);
                    }
                }
            });
        }
    }

    private void startAnimation() {
        if (popupWindow == null) {
            popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
            popupWindow.setFocusable(false);
            popupWindow.setOutsideTouchable(true);
        }

        if (selectedView.isClickable()) {
            if (!popupWindow.isShowing()) {
                showPopup(selectPosition);
            } else {
                popupWindow.setOnDismissListener(this);
                popupWindow.dismiss();
                hideView();
            }
        } else {
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                hideView();
            }
        }
        for (int position = 0; position < mViews.size(); position++) {
            if (position != selectPosition) {
                dismissPopwindowStyle(mViews.get(position));
            }
        }
    }

    /**
     * 显示筛选条件
     *
     * @param position
     */
    private void showPopup(int position) {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.show();
        }
        if (popupWindow.getContentView() != mViewArray.get(position)) {
            popupWindow.setContentView(mViewArray.get(position));
        }
        showPopwindowStyle();
        popupWindow.showAsDropDown(this, 0, 0);
    }

    /**
     * 设置已选中样式
     */
    private void showPopwindowStyle() {
        if (selectedView != null) {
            TextView textView = (TextView) selectedView.findViewById(R.id.expand_tabview_tv);
            ImageView imageView = (ImageView) selectedView.findViewById(R.id.expand_tabview_img);
            textView.setTextColor(getResources().getColor(R.color.expand_tab_view_select));
            imageView.setImageResource(R.drawable.ic_arrows_black_up);
        }
    }

    private void dismissPopwindowStyleDefult() {
        dismissPopwindowStyle(selectedView);
    }

    /**
     * 设置未选中样式
     *
     * @param selectView
     */
    private void dismissPopwindowStyle(View selectView) {
        if (selectView != null) {
            TextView textView = (TextView) selectView.findViewById(R.id.expand_tabview_tv);
            ImageView imageView = (ImageView) selectView.findViewById(R.id.expand_tabview_img);
            textView.setTextColor(getResources().getColor(R.color.dark_grey));
            imageView.setImageResource(R.drawable.ic_arrows_black_down);
        }
    }

    /**
     * 如果菜单成展开状态，则让菜单收回去
     */
    public boolean onPressBack() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            hideView();
            return true;
        } else {
            return false;
        }

    }

    /**
     * 将pop菜单隐藏并改变字体样式
     */
    private void hideView() {
        View tView = mViewArray.get(selectPosition).getChildAt(0);
        if (tView instanceof ViewBaseAction) {
            ViewBaseAction f = (ViewBaseAction) tView;
            f.hide();
        }
        dismissPopwindowStyleDefult();
    }

    @Override
    public void onDismiss() {
        dismissPopwindowStyleDefult();
        popupWindow.setOnDismissListener(null);
    }

    private OnButtonClickListener mOnButtonClickListener;

    /**
     * 设置tabitem的点击监听事件
     */
    public void setOnButtonClickListener(OnButtonClickListener l) {
        mOnButtonClickListener = l;
    }

    /**
     * 自定义tabitem点击回调接口
     */
    public interface OnButtonClickListener {
        void onClick(int selectPosition);
    }

    /**
     * 获取ExpandTabView过滤器的位置
     *
     * @param tabView
     * @return
     */
    public int getFilterViewPositon(View tabView, ArrayList<View> viewArrayList) {
        for (int i = 0; i < viewArrayList.size(); i++) {
            if (viewArrayList.get(i) == tabView) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将过滤器中包含指定字符的内容替换成初始化时的内容，并显示到过滤器标题上
     *
     * @param tabView
     * @param showText
     * @param containsText 包含的字符
     */
    public void isSetStrToTabView(View tabView, String showText, String containsText) {
        int tabPosition = getFilterViewPositon(tabView, mViewArrays);
        if (tabPosition >= 0 && !getTitle(tabPosition).equals(showText)) {
            if (showText.contains(containsText)) {
                setTitle(mTextArray.get(tabPosition), tabPosition);
            } else {
                setTitle(showText, tabPosition);
            }
        }
    }
}

