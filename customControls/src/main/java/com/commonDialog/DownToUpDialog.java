package com.commonDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.customcontrolsmodule.R;

/**
 * Created by chenyk on 2016/6/16.
 * 从下往上浮现的Dialog
 * 两个按钮和一个取消按钮
 */
public abstract class DownToUpDialog extends Dialog {
    private Button fristBtn, secondBtn, cancelBtn;

    public DownToUpDialog(Context context) {
        super(context, R.style.transparentFrameWindowStyle);
        View contentView = getLayoutInflater().inflate(R.layout.down_to_up_dialog, null);
        setContentView(contentView);
        fristBtn = (Button) contentView.findViewById(R.id.album_btn);
        secondBtn = (Button) contentView.findViewById(R.id.cameno_btn);
        cancelBtn = (Button) contentView.findViewById(R.id.cancel_btn);
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initWindow();
        setOnClickListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 默认从底部出来，从底部消失
     */
    protected void initWindow() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setWindowAnimations(R.style.dialog_bottom_animstyle);
    }

    /**
     * 设置第一个的文本信息
     *
     * @param tv
     */
    public DownToUpDialog setFirstText(String tv) {
        fristBtn.setText(tv);
        return this;
    }

    /**
     * 获取第一个文本字符
     */
    public String getFirstText() {
        return fristBtn.getText().toString();
    }

    /**
     * 获取第二个文本字符
     */
    public String getSecondText() {
        return secondBtn.getText().toString();
    }

    /**
     * 设置第二个的文本信息
     *
     * @param tv
     */
    public DownToUpDialog setSecondText(String tv) {
        secondBtn.setText(tv);
        return this;
    }

    /**
     * 设置监听器
     */
    private void setOnClickListener() {
        fristBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                fristBtnClickListener();
            }
        });
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                secondBtnClickListener();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public abstract void fristBtnClickListener();

    public abstract void secondBtnClickListener();

}
