package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/3/30.
 */
public class HelpActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        findViewById(R.id.iv_back).setOnClickListener(this);

    }

    public static void show(Activity activity){
        Intent intent = new Intent(activity, HelpActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
