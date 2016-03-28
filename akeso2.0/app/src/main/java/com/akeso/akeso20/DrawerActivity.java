package com.akeso.akeso20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by changfeifan on 16/3/28.
 */
public class DrawerActivity extends Activity implements View.OnClickListener, View.OnTouchListener {
    private Button mLeftMenuBtn;
    private Button mRightMenuBtn;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout leftMenulayout;
    private RelativeLayout rightMessagelayout;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, DrawerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer);

        mLeftMenuBtn = (Button) findViewById(R.id.id_left_openBtn);
        mRightMenuBtn = (Button) findViewById(R.id.id_right_openBtn);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerLayout.setScrimColor(0xaf000000);

        initEvent();
        initLeftLayout();
        initRightLayout();

        mLeftMenuBtn.setOnClickListener(this);
        mRightMenuBtn.setOnClickListener(this);

        leftMenulayout.setOnTouchListener(this);
        rightMessagelayout.setOnTouchListener(this);
    }

    public void initLeftLayout() {

        //设置透明

        //左边菜单

        leftMenulayout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        View view2 = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
        TextView tv = (TextView) view2.findViewById(android.R.id.text1);
        tv.setText("左边测试菜单");
        leftMenulayout.addView(view2);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(DrawerActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initRightLayout() {

        //左边菜单
        rightMessagelayout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
        View view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setText("右边测试菜单");
        rightMessagelayout.addView(view);
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Toast.makeText(DrawerActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvent() {

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onDrawerSlide(View arg0, float arg1) {

            }

            @Override
            public void onDrawerOpened(View arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDrawerClosed(View arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    //左边菜单开关事件

    public void openLeftLayout() {

        if (mDrawerLayout.isDrawerOpen(leftMenulayout)) {

            mDrawerLayout.closeDrawer(leftMenulayout);

        } else {

            mDrawerLayout.openDrawer(leftMenulayout);
        }

    }

    // 右边菜单开关事件

    public void openRightLayout() {

        if (mDrawerLayout.isDrawerOpen(rightMessagelayout)) {

            mDrawerLayout.closeDrawer(rightMessagelayout);

        } else {

            mDrawerLayout.openDrawer(rightMessagelayout);

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_left_openBtn:
                openLeftLayout();
                break;
            case R.id.id_right_openBtn:
                openRightLayout();
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        return true;//阻止事件多重分发
    }

}
