package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/4/7.
 */
public class GlassActivity extends Activity implements View.OnClickListener {
    private TextView tv_name;
    private TextView tv_serial;
    private TextView tv_battery;
    SharedPreferences sharedPreferences;
//    CheckBleInstanse checkBleInstanse = CheckBleInstanse.getInstance();

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, GlassActivity.class);
        activity.startActivityForResult(intent, 1001);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass);

        setView();

    }

    private void setView() {

        findViewById(R.id.iv_back).setOnClickListener(this);

        sharedPreferences = getSharedPreferences("test", MODE_PRIVATE);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_serial = (TextView) findViewById(R.id.tv_serial);
        tv_battery = (TextView) findViewById(R.id.tv_battery);

        tv_name.setText(sharedPreferences.getString("name", ""));
        tv_serial.setText(sharedPreferences.getString("address", ""));
        if (!sharedPreferences.getString("battery", "").equals("")) {
            tv_battery.setText(sharedPreferences.getString("battery", "") + "%");
        }
        findViewById(R.id.tv_unpair).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_unpair:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "");
                editor.putString("address", "");
                editor.putString("battery", "0");
                editor.commit();

//                ActiveActivity.show(GlassActivity.this);
                setResult(RESULT_OK);
                finish();
                break;
            default:
                break;
        }
    }


}
