package com.akeso.akeso20;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.akeso.akeso20.active.ActiveActivity;
import com.akeso.akeso20.ble.DrawerActivity;
import com.akeso.akeso20.thread.GetMe;

import org.json.JSONObject;

public class MainActivity extends Activity {
    private String mDeviceName;
    private String mDeviceAddress;

    public static void show(Activity activity){
        Intent intent=new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences=getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        mDeviceName = sharedPreferences.getString("name", "");
        mDeviceAddress = sharedPreferences.getString("address", "");

        new GetMe(sharedPreferences.getString("token", "")) {
            @Override
            protected void onPostExecute(JSONObject object) {
                super.onPostExecute(object);
                if (object.optInt("code") == 200) {
                    sharedPreferences.edit().putString("user",object.optString("user")).commit();
//                    MainActivity.show(LoginActivity.this);
                    if (mDeviceAddress.equals("")||mDeviceName.equals("")){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActiveActivity.show(MainActivity.this);
                                MainActivity.this.finish();    // 结束启动动画界面
                            }
                        }, 2000);    //启动动画持续3秒钟
                    }else {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DrawerActivity.show(MainActivity.this);
                                MainActivity.this.finish();    // 结束启动动画界面
                            }
                        }, 2000);    //启动动画持续3秒钟
                    }
                } else {

                }
            }
        }.execute();


    }

}
