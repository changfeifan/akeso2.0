package com.akeso.akeso20;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.akeso.akeso20.active.ActiveActivity;
import com.akeso.akeso20.ble.DrawerActivity;

public class MainActivity extends Activity {
    private String mDeviceName;
    private String mDeviceAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        mDeviceName = sharedPreferences.getString("name", "");
        mDeviceAddress = sharedPreferences.getString("address", "");

        if (mDeviceAddress.equals("")||mDeviceName.equals("")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ActiveActivity.show(MainActivity.this);
                    MainActivity.this.finish();    // 结束启动动画界面
                }
            }, 3000);    //启动动画持续3秒钟
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    DrawerActivity.show(MainActivity.this);
                    MainActivity.this.finish();    // 结束启动动画界面
                }
            }, 3000);    //启动动画持续3秒钟
        }

    }

}
