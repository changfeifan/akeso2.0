package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/3/30.
 */
public class FileActivity extends Activity implements View.OnClickListener {

    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        findViewById(R.id.iv_back).setOnClickListener(this);

        setView();
    }

    private void setView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        if (getIntent().getStringExtra("name") != null)
            tv_title.setText(getIntent().getStringExtra("name") + "的健康档案");

    }

    public static void show(Activity activity, String name) {
        Intent intent = new Intent(activity, FileActivity.class);
        intent.putExtra("name", name);
        activity.startActivity(intent);
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
