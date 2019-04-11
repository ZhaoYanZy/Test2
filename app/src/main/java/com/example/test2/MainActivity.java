package com.example.test2;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {


    private MyHandler myHandler;

    private TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tv_hello);
        myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(1, 3000);
    }


    private static class MyHandler extends Handler {

        WeakReference<MainActivity> mWeakReference;

        private MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity mainActivity = mWeakReference.get();
            if (mainActivity != null) {
                switch (msg.what) {
                    case 1:
                        mainActivity.tvHello.setText("你好世界!");
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(1);
    }
}
