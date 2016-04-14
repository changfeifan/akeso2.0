package com.akeso.akeso20.active;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.ble.DrawerActivity;

/**
 * Created by changfeifan on 16/4/14.
 */
public class Active4Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_4);

        ImageView iv_content = (ImageView) findViewById(R.id.welcome_content);
        TextView tv_heading=(TextView)findViewById(R.id.heading);
        TextView tv_content=(TextView)findViewById(R.id.content);

        SharedPreferences mySharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        if (mySharedPreferences.getString("name", "").equals("")
                || mySharedPreferences.getString("address", "").equals("")) {
            iv_content.setImageResource(R.drawable.guide_4_1);
            tv_heading.setText("设备尚未连接");
            tv_content.setText("您的设备尚未链接，您可以进入主界面后\n点击右上角图标尝试重新链接。");
        } else {
            iv_content.setImageResource(R.drawable.guide_4);
            tv_heading.setText("配对连接成功");
            tv_content.setText("恭喜您连接成功，您可以马上体验我们\n的产品，开始关注您的眼健康状态。");

        }

        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerActivity.show(Active4Activity.this);
            }
        });
    }

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, Active4Activity.class);
        activity.startActivity(intent);
    }
}
