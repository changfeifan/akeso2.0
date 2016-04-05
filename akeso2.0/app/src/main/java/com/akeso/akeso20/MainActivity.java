package com.akeso.akeso20;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.akeso.akeso20.guide.ProductTour3Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProductTour3Activity.show(MainActivity.this);
                MainActivity.this.finish();    // 结束启动动画界面
            }
        }, 2000);    //启动动画持续3秒钟

    }

}
