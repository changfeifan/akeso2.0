package com.akeso.akeso20.active;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.akeso.akeso20.R;

/**
 * Created by changfeifan on 16/4/14.
 */
public class ActiveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        findViewById(R.id.tv_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Active2Activity.show(ActiveActivity.this);
            }
        });
    }

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, ActiveActivity.class);
        activity.startActivity(intent);
    }
}
