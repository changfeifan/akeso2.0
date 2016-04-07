/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akeso.akeso20.ble;

import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class SampleGattAttributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static String HEART_RATE_MEASUREMENT = "00002a37-0000-1000-8000-00805f9b34fb";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "00002902-0000-1000-8000-00805f9b34fb";
    // 链接状态服务 UUID=0x1803
    public static final String Service_Link_state = "00001803-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐0x2A06
    public static final String Characteristics_Link_state = "00002a06-0000-1000-8000-00805f9b34fb";

//    // 丢失寻找服务 UUID= 0xFFE0
//    public static final String Service_Lost_search = "0000ffe0-0000-1000-8000-00805f9b34fb";
//    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐0xFFE1
//    public static final String Characteristics_Lost_search = "0000ffe1-0000-1000-8000-00805f9b34fb";

    // 发射功率调整服务 UUID=0x1804
    public static final String Service_Transmit_power_adjustment = "00001804-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐0x2A07
    public static final String Characteristics_Transmit_power_adjustment = "00002a07-0000-1000-8000-00805f9b34fb";

    // 加速度传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC0
    public static final String Service_Acceleration_sensor_data = "0000ffc0-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC1（只读）
    public static final String Characteristics_Acceleration_sensor_data = "0000ffc1-0000-1000-8000-00805f9b34fb";
    // 计步： 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC2（只读）
    public static final String Characteristics_Acceleration_sensor_data_steps = "0000ffc2-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC3（只写）
    public static final String Characteristics_Acceleration_sensor_data_off_on = "0000ffc3-0000-1000-8000-00805f9b34fb";

    // 紫外线环境光传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFD0
    public static final String Service_Ultraviolet_light_sensor_data = "0000ffd0-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFD1
    public static final String Characteristics_Ultraviolet_light_sensor_data = "0000ffd1-0000-1000-8000-00805f9b34fb";

    // 温湿度传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFF0
    public static final String Service_Temperature_and_humidity_sensor_data = "0000fff0-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFF1
    public static final String Characteristics_Temperature_and_humidity_sensor_data = "0000fff1-0000-1000-8000-00805f9b34fb";

    // 状态信息数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFB0
    public static final String Service_Status_information_data = "0000ffb0-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFB1
    public static final String Characteristics_Status_information_data = "0000ffb1-0000-1000-8000-00805f9b34fb";

    // 历史数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFE0
    public static final String Service_History_data = "0000ffe0-0000-1000-8000-00805f9b34fb";
    // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFE1
    public static final String Characteristics_History_data = "0000ffe1-0000-1000-8000-00805f9b34fb";

    static {
        // Sample Services.
        attributes.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate Service");
        attributes.put("0000180a-0000-1000-8000-00805f9b34fb", "BLE设备信息");
        // Sample Characteristics.
        attributes.put(HEART_RATE_MEASUREMENT, "Heart Rate Measurement");
        attributes.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String");

        // 加速度传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC0
        attributes.put(Service_Acceleration_sensor_data, "加速度传感器数据");
        // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC1（只读）
        attributes.put(Characteristics_Acceleration_sensor_data, "实时数据");
        // 计步： 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC2（只读）
        attributes.put(Characteristics_Acceleration_sensor_data_steps, "计步数据");
        // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFC3（只写）
        attributes.put(Characteristics_Acceleration_sensor_data_off_on, "计步开关");

        // 紫外线环境光传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFD0
        attributes.put(Service_Ultraviolet_light_sensor_data, "光线传感器");
        // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFD1
        attributes.put(Characteristics_Ultraviolet_light_sensor_data, "光线特征");

        // 温湿度传感器数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFF0
        attributes.put(Service_Temperature_and_humidity_sensor_data, "温湿度传感器服务");
        // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFF1
        attributes.put(Characteristics_Temperature_and_humidity_sensor_data, "温湿度特征");

        // 状态信息数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFB0
        attributes.put(Service_Status_information_data, "设备状态服务");
        // 特征UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFB1
        attributes.put(Characteristics_Status_information_data, "设备状态特征");

        // 历史数据服务 UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFE0
        attributes.put(Service_History_data, "历史数据服务");
        // 历史数据UUID‐‐‐‐‐‐‐‐‐‐‐‐ 0xFFE1
        attributes.put(Characteristics_History_data, "历史数据开关");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}
