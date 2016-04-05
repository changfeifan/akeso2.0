package com.akeso.akeso20.ble;

import android.util.Log;

/**
 * Created by changfeifan on 16/4/5.
 */
public class CheckBleInstanse {

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
    private static CheckBleInstanse instance = null;

    /* 私有构造方法，防止被实例化 */
    private CheckBleInstanse() {
    }

    /* 懒汉式，静态工程方法，创建实例 */
    public static synchronized CheckBleInstanse getInstance() {
        if (instance == null) {
            instance = new CheckBleInstanse();
        }
        return instance;
    }

    public boolean CheckBle(){
        Log.e(getClass().getName(),"start check ble.");
        return true;
    }
}
