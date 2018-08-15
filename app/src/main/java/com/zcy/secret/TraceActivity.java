package com.zcy.secret;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zhaichenyang on 2018/8/15.
 * 测试TraceView工具使用
 */

public class TraceActivity extends Activity {
    int count = 0;
    long longCount=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trace);
//        Debug.startMethodTracing("hello");
        //线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                printNum();
            }
        },"printNum_thread").start();

        //线程2
        new Thread(new Runnable() {
            @Override
            public void run() {
                calculate();
            }
        },"calculate_thread").start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Debug.stopMethodTracing();
    }

    private void printNum() {
        for (int i = 0; i < 20000; i++) {
            print();
        }
    }

    /**
     * 模拟一个自身占用时间不长，但调用却非常频繁的函数
     */
    private void print(){
        count=count++;
    }

    /**
     * 模拟一个调用次数不多，但每次调用却需要花费很长时间的函数
     */
    private  void  calculate(){
        for (int i = 0; i < 1000; i++) {
            if(i==10){
                Log.e("TraceActivity","10");
            }
            if(i==100){
                Log.e("TraceActivity","100");
            }
            if(i==500){
                Log.e("TraceActivity","500");
            }
            for (int j = 0; j < 1000; j++) {
                for (int l = 0; l < 100; l++) {
                    if(longCount>10){
                        longCount=-10;
                    }
                    longCount++;
                }
            }
        }
        Log.e("TraceActivity","true");
    }
}

