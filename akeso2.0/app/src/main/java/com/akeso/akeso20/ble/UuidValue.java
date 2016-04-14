package com.akeso.akeso20.ble;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by changfeifan on 16/4/7.
 */
public class UuidValue implements Parcelable {

    public String Uuid;
    public byte[] data;

    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String uuid) {
        Uuid = uuid;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public UuidValue() {

    }

    protected UuidValue(Parcel in) {
        Uuid = in.readString();
        data = in.createByteArray();
    }

    public static final Creator<UuidValue> CREATOR = new Creator<UuidValue>() {
        @Override
        public UuidValue createFromParcel(Parcel in) {
            return new UuidValue(in);
        }

        @Override
        public UuidValue[] newArray(int size) {
            return new UuidValue[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Uuid);
        dest.writeByteArray(data);
    }
}
