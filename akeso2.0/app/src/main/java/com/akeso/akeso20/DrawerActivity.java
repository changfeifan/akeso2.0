package com.akeso.akeso20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akeso.akeso20.fragment.RecyclerViewFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

/**
 * Created by changfeifan on 16/3/28.
 */
public class DrawerActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private ImageView iv_mLeftMenu;
    private ImageView iv_mRightMenu;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout leftMenulayout;
    private RelativeLayout rightMessagelayout;
    private MaterialViewPager mViewPager;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, DrawerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        setTitle("");

        iv_mLeftMenu = (ImageView) findViewById(R.id.id_left_openBtn);
        iv_mRightMenu = (ImageView) findViewById(R.id.id_right_openBtn);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerLayout.setScrimColor(0xaf000000);

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);

        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 3) {
                    case 0:
                        return "视觉负担";
                    case 1:
                        return "视觉环境";
                    case 2:
                        return "视觉习惯";
//                    case 3:
//                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue_light,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
//                    case 3:
//                        return HeaderDesign.fromColorResAndUrl(
//                                R.color.red,
//                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());


        initEvent();
        initLeftLayout();
        initRightLayout();

        iv_mLeftMenu.setOnClickListener(this);
        iv_mRightMenu.setOnClickListener(this);

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
