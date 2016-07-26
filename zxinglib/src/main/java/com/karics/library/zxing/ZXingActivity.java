package com.karics.library.zxing;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zxinglib.R;
import com.google.zxing.WriterException;
import com.karics.library.zxing.android.CaptureActivity;
import com.karics.library.zxing.encode.CodeCreator;

/**
 * Created by chenyk on 2016/6/20.
 * 二维码生成或扫描页面
 */
public class ZXingActivity extends Activity {
    private static final int REQUEST_CODE_SCAN = 0x0000;

    ImageView qrCodeImage;
    Button creator, scanner;
    EditText qrCodeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zxing_layout);

        qrCodeImage = (ImageView) findViewById(R.id.ECoder_image);
        creator = (Button) findViewById(R.id.ECoder_creator);
        scanner = (Button) findViewById(R.id.ECoder_scaning);
        qrCodeUrl = (EditText) findViewById(R.id.ECoder_input);

        creator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String url = qrCodeUrl.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    Toast.makeText(ZXingActivity.this, "请输入Url或文本", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Bitmap bitmap = CodeCreator.createQRCode(url);
                    qrCodeImage.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        scanner.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ZXingActivity.this,
                        CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传结果
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SCAN:
                    if (data != null) {
                        String content = data.getStringExtra(CaptureActivity.DECODED_CONTENT_KEY);
                        Bitmap bitmap = data.getParcelableExtra(CaptureActivity.DECODED_BITMAP_KEY);
                        Toast.makeText(this, "解码结果：" + content, Toast.LENGTH_LONG).show();
                        qrCodeImage.setImageBitmap(bitmap);
                    }
                    break;
                default:
                    break;

            }
        }
    }
}

