package com.example.chenyk.chenyknotes.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.chenyk.chenyknotes.R;
import com.example.chenyk.chenyknotes.Utils.IDCardUtil;
import com.example.chenyk.chenyknotes.Utils.ToastUtil;

/**
 * Created by chenyk on 2016/7/4.
 */
public class IdCardVerifyActivity extends Activity {
    private EditText idCardInputET;
    private Button idCardVerifyBtn;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard_verify_layout);
        mActivity = IdCardVerifyActivity.this;
        idCardInputET = (EditText) findViewById(R.id.id_card_input_et);
        idCardVerifyBtn = (Button) findViewById(R.id.id_card_verify_btn);
        idCardVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //身份证号验证
                String idCardStr = idCardInputET.getText().toString();
                if (TextUtils.isEmpty(idCardStr)) {
                    ToastUtil.showNativeShortToast(mActivity, "请输入身份证号");
                    return;
                }
                if (IDCardUtil.IDCardValidate(idCardStr)) {
                    ToastUtil.showNativeShortToast(mActivity, "性别：" + IDCardUtil.getIdcardAge(idCardStr) + "，生日：" +
                            IDCardUtil.getIdcardSex(idCardStr));
                } else {
                    ToastUtil.showNativeShortToast(mActivity, IDCardUtil.getErrorInfo());
                }
            }
        });

    }
}
