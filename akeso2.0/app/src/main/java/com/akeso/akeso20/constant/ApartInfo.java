package com.akeso.akeso20.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/15.
 */
public class ApartInfo {
    String name;
    String position;
    int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public JSONObject toObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("name", getName());
            object.put("imageId", getImageId());
            object.put("position", getPosition());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public ApartInfo toApartInfo(JSONObject object) {
        ApartInfo apartInfo = new ApartInfo();
        apartInfo.setImageId(object.optInt("imageId", 0));
        apartInfo.setName(object.optString("name", ""));
        apartInfo.setPosition(object.optString("position,"));
        return apartInfo;
    }
}
