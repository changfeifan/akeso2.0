package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/3/30.
 */
public class DeviceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        
    }

    public static void show(Activity activity){
        Intent intent = new Intent(activity, DeviceActivity.class);
        activity.startActivity(intent);
    }


}
