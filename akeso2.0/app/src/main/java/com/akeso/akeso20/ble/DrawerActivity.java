package com.akeso.akeso20.ble;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akeso.akeso20.R;
import com.akeso.akeso20.active.ActiveActivity;
import com.akeso.akeso20.activity.AboutActivity;
import com.akeso.akeso20.activity.ApartmentActivity;
import com.akeso.akeso20.activity.FileListActivity;
import com.akeso.akeso20.activity.GlassActivity;
import com.akeso.akeso20.activity.PersonalActivity;
import com.akeso.akeso20.activity.SettingActivity;
import com.akeso.akeso20.fragment.VisualBurdenViewFragment;
import com.akeso.akeso20.fragment.VisualEnvirmentViewFragment;
import com.akeso.akeso20.fragment.VisualHabitViewFragment;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by changfeifan on 16/3/28.
 */
public class DrawerActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    private final static String TAG = DrawerActivity.class.getSimpleName();


    private ImageView iv_mLeftMenu;
    private ImageView iv_mRightMenu;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout leftMenulayout;
    private RelativeLayout rightMessagelayout;
    private MaterialViewPager mViewPager;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    private String mDeviceName;
    private String mDeviceAddress;
    private BluetoothLeService mBluetoothLeService;
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<ArrayList<BluetoothGattCharacteristic>> mGattCharacteristics =
            new ArrayList<ArrayList<BluetoothGattCharacteristic>>();
    private boolean mConnected = false;
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    android.os.Handler handler = new android.os.Handler();
    SharedPreferences sharedPreferences;
    String avatarUrl;
    String nickname;
    String mobile;

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, DrawerActivity.class);
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        activity.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1001://解除绑定
                    unbindService(mServiceConnection);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        sharedPreferences = getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        mDeviceName = sharedPreferences.getString("name", "");
        mDeviceAddress = sharedPreferences.getString("address", "");

        setTitle("");

        iv_mLeftMenu = (ImageView) findViewById(R.id.id_left_openBtn);
        iv_mRightMenu = (ImageView) findViewById(R.id.id_right_openBtn);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mDrawerLayout.setScrimColor(0x4f000000);

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
                switch (position % 3) {
                    case 0:
                        return VisualBurdenViewFragment.newInstance();
                    case 1:
                        return VisualEnvirmentViewFragment.newInstance();
                    case 2:
                        return VisualHabitViewFragment.newInstance();
                    default:
                        return VisualBurdenViewFragment.newInstance();
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

                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.background_blue_deep,
                                getResources().getDrawable(R.drawable.background_guide));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.background_blue_deep,
                                getResources().getDrawable(R.drawable.background_drawer_center));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.background_blue_deep,
                                getResources().getDrawable(R.drawable.background_guide));
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

        if (mDeviceAddress.equals("") || mDeviceAddress == null) {
            Toast.makeText(this, "尚未选择连接设备，请点击右侧按钮选择镜框。", Toast.LENGTH_LONG).show();
        } else {
            Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
            bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
        }

    }

    public void initLeftLayout() {

        //左边菜单
        leftMenulayout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        View view2 = getLayoutInflater().inflate(R.layout.drawer_left, null);
        view2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        leftMenulayout.addView(view2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_close:
                        mDrawerLayout.closeDrawer(leftMenulayout);
                        break;
                    case R.id.ll_help:
                        SettingActivity.show(DrawerActivity.this);
                        break;
                    case R.id.ll_file:
                        FileListActivity.show(DrawerActivity.this);
                        break;
                    case R.id.ll_about:
                        AboutActivity.show(DrawerActivity.this);
                        break;
                    case R.id.ll_person:
                        PersonalActivity.show(DrawerActivity.this);
                        break;
                    case R.id.ll_apart:
                        ApartmentActivity.show(DrawerActivity.this);
                        break;
                    default:
                        break;
                }

            }
        };
        view2.setOnClickListener(listener);
        view2.findViewById(R.id.iv_close).setOnClickListener(listener);
        view2.findViewById(R.id.ll_help).setOnClickListener(listener);
        view2.findViewById(R.id.ll_file).setOnClickListener(listener);
        view2.findViewById(R.id.ll_about).setOnClickListener(listener);
        view2.findViewById(R.id.ll_person).setOnClickListener(listener);
        view2.findViewById(R.id.ll_apart).setOnClickListener(listener);

    }

    public void initRightLayout() {
        try {
            //右边菜单
            rightMessagelayout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
            View view = getLayoutInflater().inflate(R.layout.drawer_right, null);
            view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            rightMessagelayout.addView(view);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.iv_device:
                            if (mDeviceAddress.equals("") || mDeviceName.equals("")) {
                                ActiveActivity.show(DrawerActivity.this);
                            } else {
                                GlassActivity.show(DrawerActivity.this);
                            }
                            break;
                        case R.id.tv_update:
//                        update();
                            step_on();
                            break;
                        case R.id.tv_history:
                            getHistory();
                            break;
                        default:
                            break;
                    }
                }
            };
            view.setOnClickListener(listener);
            view.findViewById(R.id.iv_device).setOnClickListener(listener);
            view.findViewById(R.id.tv_update).setOnClickListener(listener);
            view.findViewById(R.id.tv_history).setOnClickListener(listener);
            //获取用户数据，来源MainActivity-GetMe接口
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("user", ""));
            String avatarUrl = jsonObject.getString("avatar");
            String nickname = jsonObject.getString("nickname");
            String mobile = jsonObject.getString("mobile");

            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(nickname + "的Akeso");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    public void closeAllLayout() {

        if (mDrawerLayout.isDrawerOpen(rightMessagelayout)) {
            mDrawerLayout.closeDrawer(rightMessagelayout);
        }
        if (mDrawerLayout.isDrawerOpen(leftMenulayout)) {
            mDrawerLayout.closeDrawer(leftMenulayout);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_left_openBtn:
                openLeftLayout();
                break;
            case R.id.id_right_openBtn:
                update();
                openRightLayout();
                updateRightLayout();
                break;

            default:
                break;
        }

    }

    private void updateRightLayout() {
        View view = rightMessagelayout.getChildAt(0);
        TextView tv_charge_time = (TextView) view.findViewById(R.id.tv_charge_time);
        LinearLayout ll_battery_info = (LinearLayout) view.findViewById(R.id.ll_battery_info);
        ImageView iv_device = (ImageView) view.findViewById(R.id.iv_device);
        if (mDeviceAddress.equals("")) {
            iv_device.setImageResource(R.drawable.ic_right_disconnect);
            tv_charge_time.setText("未连接");
            ll_battery_info.setVisibility(View.INVISIBLE);
        } else {
            iv_device.setImageResource(R.drawable.ic_right);
            tv_charge_time.setText("上次同步时间  今天 上午2:31");
            ll_battery_info.setVisibility(View.VISIBLE);

            ImageView iv_battery = (ImageView) view.findViewById(R.id.iv_battery);
            TextView tv_battery = (TextView) view.findViewById(R.id.tv_battery);
            if (!sharedPreferences.getString("battery", "").equals("")) {
                int num = Integer.valueOf(sharedPreferences.getString("battery", ""));
                tv_battery.setText("电池：" + num + "%");

                if (num > 66) {
                    iv_battery.setImageResource(R.drawable.state_full);
                } else if (num < 33) {
                    iv_battery.setImageResource(R.drawable.state_low_energy);
                } else {
                    iv_battery.setImageResource(R.drawable.state_half);
                }
            }
        }

    }

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        // TODO Auto-generated method stub
        return true;//阻止事件多重分发
    }


    //以下是蓝牙部分

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (!mBluetoothLeService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothLeService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                Log.e(getClass().toString(), BluetoothLeService.ACTION_GATT_CONNECTED);
                Toast.makeText(DrawerActivity.this, "已连接设备", Toast.LENGTH_SHORT).show();
                iv_mRightMenu.setImageResource(R.drawable.normal);
//                updateConnectionState(R.string.connected);
//                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                Log.e(getClass().toString(), BluetoothLeService.ACTION_GATT_DISCONNECTED);
                Toast.makeText(DrawerActivity.this, "失去连接，等待重连", Toast.LENGTH_SHORT).show();
                iv_mRightMenu.setImageResource(R.drawable.no_connect);
//                updateConnectionState(R.string.disconnected);
//                clearUI();
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                Log.e(getClass().toString(), BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
                Toast.makeText(DrawerActivity.this, "发现服务，请求数据", Toast.LENGTH_SHORT).show();
                // Show all the supported services and characteristics on the user interface.
                displayGattServices(mBluetoothLeService.getSupportedGattServices());
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                Toast.makeText(DrawerActivity.this, "得到数据", Toast.LENGTH_SHORT).show();
                iv_mRightMenu.setImageResource(R.drawable.normal);
                Log.e(getClass().toString(), BluetoothLeService.ACTION_DATA_AVAILABLE);
                UuidValue uuidValue = (UuidValue) intent.getExtras().get("uuid");
                displayData(uuidValue);
            }
        }
    };

    private void displayGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        String uuid = null;
        String unknownServiceString = getResources().getString(R.string.unknown_service);
        String unknownCharaString = getResources().getString(R.string.unknown_characteristic);
        ArrayList<HashMap<String, String>> gattServiceData = new ArrayList<HashMap<String, String>>();
        ArrayList<ArrayList<HashMap<String, String>>> gattCharacteristicData
                = new ArrayList<ArrayList<HashMap<String, String>>>();
        mGattCharacteristics = new ArrayList<ArrayList<BluetoothGattCharacteristic>>();

        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            HashMap<String, String> currentServiceData = new HashMap<String, String>();
            uuid = gattService.getUuid().toString();
            currentServiceData.put(LIST_NAME, SampleGattAttributes.lookup(uuid, unknownServiceString));
            currentServiceData.put(LIST_UUID, uuid);
            gattServiceData.add(currentServiceData);

            ArrayList<HashMap<String, String>> gattCharacteristicGroupData = new ArrayList<HashMap<String, String>>();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
            ArrayList<BluetoothGattCharacteristic> charas = new ArrayList<BluetoothGattCharacteristic>();

            // Loops through available Characteristics.
            for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                Log.e("1", "" + gattCharacteristic.getUuid());
                charas.add(gattCharacteristic);
                HashMap<String, String> currentCharaData = new HashMap<String, String>();
                uuid = gattCharacteristic.getUuid().toString();
                currentCharaData.put(
                        LIST_NAME, SampleGattAttributes.lookup(uuid, unknownCharaString));
                currentCharaData.put(LIST_UUID, uuid);
                gattCharacteristicGroupData.add(currentCharaData);


            }
            mGattCharacteristics.add(charas);
            gattCharacteristicData.add(gattCharacteristicGroupData);
        }
//        mGattServicesList.setAdapter(gattServiceAdapter);
    }

    private void getHistory() {
        if (mGattCharacteristics != null) {
            Toast.makeText(this, "获取历史数据", Toast.LENGTH_SHORT);
            for (int i = 0; i < mGattCharacteristics.size(); i++) {
                for (int j = 0; j < mGattCharacteristics.get(i).size(); j++) {
                    final BluetoothGattCharacteristic characteristic =
                            mGattCharacteristics.get(i).get(j);
                    if (characteristic.getUuid().toString().equals(SampleGattAttributes.Characteristics_History_data_off_on)) {
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
                            byte[] bytes = new byte[1];
                            bytes[0] = 0x01;
                            characteristic.setValue(bytes);
                            mBluetoothLeService.writeCharacteristic(characteristic);
                        }
                    }
                }
            }
        }
    }

    private void update() {
        if (mGattCharacteristics != null) {
            Toast.makeText(this, "更新ing", Toast.LENGTH_SHORT);
            for (int i = 0; i < mGattCharacteristics.size(); i++) {

                for (int j = 0; j < mGattCharacteristics.get(i).size(); j++) {

                    final BluetoothGattCharacteristic characteristic =
                            mGattCharacteristics.get(i).get(j);
                    if (characteristic.getService().getUuid().toString().equals(SampleGattAttributes.Service_Status_information_data)) {
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            mBluetoothLeService.readCharacteristic(characteristic);
                        }
                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    if (characteristic.getUuid().toString().equals(SampleGattAttributes.Characteristics_Acceleration_sensor_data_steps)) {
//                        final int charaProp = characteristic.getProperties();
//                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
//                            mBluetoothLeService.readCharacteristic(characteristic);
//                        }
//                    }

                }

            }
        }

    }


    private void step_on() {
        if (mGattCharacteristics != null) {
            Toast.makeText(this, "更新ing", Toast.LENGTH_SHORT);
            for (int i = 0; i < mGattCharacteristics.size(); i++) {

                for (int j = 0; j < mGattCharacteristics.get(i).size(); j++) {

                    final BluetoothGattCharacteristic characteristic =
                            mGattCharacteristics.get(i).get(j);
//                    if (characteristic.getUuid().toString().equals(SampleGattAttributes.Characteristics_Acceleration_sensor_data_steps)) {
//                        final int charaProp = characteristic.getProperties();
//                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
//                            byte[] bytes = new byte[1];
//                            bytes[0] = 0x01;
//                            characteristic.setValue(bytes);
//                            mBluetoothLeService.writeCharacteristic(characteristic);
//                            Log.e("open","打开了计步");
//                        }
//                    }
                    if (characteristic.getUuid().toString().equals(SampleGattAttributes.Characteristics_Acceleration_sensor_data_steps)) {
                        final int charaProp = characteristic.getProperties();
                        if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mBluetoothLeService.readCharacteristic(characteristic);
                                }
                            }, 500);
                        }
                    }
                }

            }

        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }


    private void displayData(UuidValue data) {
        if (data != null) {
            try {
                if (data.getUuid().equals(SampleGattAttributes.Characteristics_Status_information_data)) {
                    Log.e("信息", " 电量：" + data.getData()[0] + " 各个状态： " + data.getData()[1]);
                    sharedPreferences.edit().putString("battery", data.getData()[0] + "").commit();
                } else if (data.getUuid().equals(SampleGattAttributes.Characteristics_Ultraviolet_light_sensor_data)) {
                    Log.e("信息", " UV：" + data.getData()[0] + " 光强： " + byteToInt(data.getData()[1]) + byteToInt(data.getData()[2]));
                    getIntent().putExtra("light", byteToInt(data.getData()[1]) + byteToInt(data.getData()[2]) + "/" + data.getData()[0]);
                } else if (data.getUuid().equals(SampleGattAttributes.Characteristics_Temperature_and_humidity_sensor_data)) {
                    Log.e("信息", " 温度：" + data.getData()[0] + " 湿度： " + data.getData()[1]);
                    getIntent().putExtra("humidity", data.getData()[1] + "");
                    update();
                } else if (data.getUuid().equals(SampleGattAttributes.Characteristics_Acceleration_sensor_data)) {
                    Log.e("信息", " 加速度：" + data.getData()[0] + " x轴：" + byteToInt(data.getData()[1]) + byteToInt(data.getData()[2]) + " y轴" +
                            "" + byteToInt(data.getData()[3]) + byteToInt(data.getData()[4]) + "" +
                            " z轴：" + byteToInt(data.getData()[5]) + byteToInt(data.getData()[6]) + " 角度值：" + byteToInt(data.getData()[7]));
                    getIntent().putExtra("neck", data.getData()[0] + "");
                    getIntent().putExtra("angle", data.getData()[7] + "");
                } else if (data.getUuid().toString().equals(SampleGattAttributes.Characteristics_Acceleration_sensor_data_steps)) {
                    Log.e("信息", "步数：" + (byteToInt(data.getData()[0]) * (255 ^ 2) + byteToInt(data.getData()[1]) * 255 + byteToInt(data.getData()[2])) + "  " + byteToInt(data.getData()[0]) + byteToInt(data.getData()[1]) + byteToInt(data.getData()[2]));
                } else if (data.getUuid().toString().equals(SampleGattAttributes.Characteristics_History_data)) {
                    Log.e("信息", "温度：" + data.getData()[0] + " 湿度：" + data.getData()[1] + " UV：" + data.getData()[2] + " " +
                            "光强：" + (byteToInt(data.getData()[3]) * 255 + byteToInt(data.getData()[4])) + " 低头抬头状态：" + data.getData()[5] + "" +
                            " 眼镜角度：" + data.getData()[6] + " 年：" + data.getData()[7] + " 月：" + data.getData()[8] + " 日：" + data.getData()[9] + " 小时：" + data.getData()[10] + "" +
                            " 分钟： " + data.getData()[11] + " 秒： " + data.getData()[12] + " 记录位置： " + data.getData()[13] + " 记录数量 " + data.getData()[14]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            for (){
//
//            }
//            mDataField.setText(data);
        }
    }

    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDeviceName = sharedPreferences.getString("name", "");
        mDeviceAddress = sharedPreferences.getString("address", "");
        if (!mDeviceAddress.equals("")) {
            registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
            if (mBluetoothLeService != null) {
                final boolean result = mBluetoothLeService.connect(mDeviceAddress);
                Log.d(TAG, "Connect request result=" + result);
            }
        } else {
            iv_mRightMenu.setImageResource(R.drawable.no_connect);
            Toast.makeText(DrawerActivity.this, "设备未绑定，请点击右侧按钮连接。", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDeviceAddress.equals("")) {
        } else {
            unregisterReceiver(mGattUpdateReceiver);
        }
        closeAllLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDeviceAddress.equals("")) {

        } else {
            unbindService(mServiceConnection);
            mBluetoothLeService = null;
        }
    }

    public static int byteToInt(byte b) {
        return Integer.valueOf(byteToBit(b), 2);
    }

    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) +
                (byte) ((b >> 6) & 0x1) +
                (byte) ((b >> 5) & 0x1) +
                (byte) ((b >> 4) & 0x1) +
                (byte) ((b >> 3) & 0x1) +
                (byte) ((b >> 2) & 0x1) +
                (byte) ((b >> 1) & 0x1) +
                (byte) ((b >> 0) & 0x1);
    }

}
