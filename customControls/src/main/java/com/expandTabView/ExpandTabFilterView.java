package com.expandTabView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.customcontrolsmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyk on 2016/6/29.
 * ExpandTab过滤器
 */
public class ExpandTabFilterView extends RelativeLayout {
    private ListView mListView;
    private final List<String> items = new ArrayList<String>();
    private final List<String> itemsVaule = new ArrayList<String>();
    private OnSelectListener mOnSelectListener;
    private ListViewItemAdapter adapter;
    private String showText = "";

    public String getShowText() {
        return showText;
    }

    public ExpandTabFilterView(Context context, List<String> viewTextList, List<String> viewTextListId) {
        super(context);
        items.addAll(viewTextList);
        itemsVaule.addAll(viewTextListId);
        initView(context);
    }

    /**
     * UI初始化
     * @param context
     */
    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.expand_tabview_listview_layout, this, true);
        mListView = (ListView) findViewById(R.id.listView);
        adapter = new ListViewItemAdapter(context, items);
        adapter.setTextSize(16);
        adapter.setSelectedPositionNoNotify(0);
        showText = items.get(0);
        mListView.setAdapter(adapter);
        adapter.setOnItemClickListener(new ListViewItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mOnSelectListener != null) {
                    showText = items.get(position);
                    mOnSelectListener.getValue(Integer.valueOf(itemsVaule.get(position)), items.get(position));
                }
            }
        });
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void getValue(int distance, String showText);
    }

}

