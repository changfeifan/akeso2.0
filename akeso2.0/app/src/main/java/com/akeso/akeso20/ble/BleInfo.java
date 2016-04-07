package com.akeso.akeso20.ble;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by changfeifan on 16/4/7.
 */
public class BleInfo implements Parcelable {
    public String name;
    public String address;

    public BleInfo() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public BleInfo(Parcel in) {
        name = in.readString();
        address = in.readString();
    }

    public static final Creator<BleInfo> CREATOR = new Creator<BleInfo>() {
        @Override
        public BleInfo createFromParcel(Parcel in) {
            return new BleInfo(in);
        }

        @Override
        public BleInfo[] newArray(int size) {
            return new BleInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
