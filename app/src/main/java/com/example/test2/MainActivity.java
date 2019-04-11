package com.example.test2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private MyHandler myHandler;

    private TextView tvHello;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate:运行");
        //查找View
        tvHello = findViewById(R.id.tv_hello);
        //初始化Handler
        myHandler = new MyHandler(this);
        Log.d(TAG, "onCreate: 发送延迟消息");
        //发送延时更新
        myHandler.sendEmptyMessageDelayed(1, 3000);
    }


    private static class MyHandler extends Handler {

        WeakReference<MainActivity> mWeakReference;

        private MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<MainActivity>(activity);
            Log.d(TAG, "MyHandler: 初始化");
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity mainActivity = mWeakReference.get();
            if (mainActivity != null) {
                switch (msg.what) {
                    case 1:
                        Log.d(TAG, "handleMessage: 收到延迟消息!");
                        mainActivity.tvHello.setText("你好世界!");
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: 销毁");
        //清楚消息
        myHandler.removeMessages(1);
    }
}
