package com.zcy.secret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button btn_encode;
    Button btn_decode;
    Button btn_trace;
    TextView tv_content;
    String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_encode = findViewById(R.id.encode);
        btn_decode = findViewById(R.id.decode);
        btn_trace = findViewById(R.id.trace);
        tv_content = findViewById(R.id.content);
        btn_encode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = "c47Ye35t0235I513";
                try {
                    AesUtils aesCryptor = new AesUtils(key);
                    ss = URLEncoder.encode(aesCryptor.encrypt("33021200081112"));
                    Log.e("encode", ss);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tv_content.setText(ss);
            }
        });
        btn_decode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = "c47Ye35t0235I513";
                try {
                    AesUtils aesCryptor = new AesUtils(key);
                    ss = aesCryptor.decrypt(URLDecoder.decode(ss));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                tv_content.setText(ss);

            }
        });
        btn_trace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TraceActivity.class);
                startActivity(intent);
            }
        });
    }
}
