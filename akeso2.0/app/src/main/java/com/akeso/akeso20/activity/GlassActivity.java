package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.ble.BleInfo;
import com.akeso.akeso20.ble.CheckBleInstanse;

/**
 * Created by changfeifan on 16/4/7.
 */
public class GlassActivity extends Activity implements View.OnClickListener {
    private BleInfo bleInfo;
    private TextView tv_name;

    public static void show(Activity activity, String deviceName, String deviceAddress) {
        BleInfo bleInfo = new BleInfo();
        bleInfo.address = deviceAddress;
        bleInfo.name = deviceName;

        Intent intent = new Intent(activity, GlassActivity.class);
        intent.putExtra("ble_info", bleInfo);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glass);

        bleInfo = getIntent().getParcelableExtra("ble_info");

        setView();

        CheckBleInstanse.getInstance().CheckBle();
    }

    private void setView() {

        findViewById(R.id.iv_back).setOnClickListener(this);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText(bleInfo.name);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
