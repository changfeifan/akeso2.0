package com.akeso.akeso20.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/4/26.
 */
public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LoginActivity.show(SplashActivity.this);
                finish();
//                MainActivity.this.finish();    // 结束启动动画界面
            }
        }, 2000);    //启动动画持续3秒钟

    }
}
