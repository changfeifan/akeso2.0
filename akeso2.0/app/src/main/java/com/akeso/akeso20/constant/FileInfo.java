package com.akeso.akeso20.constant;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/20.
 */
public class FileInfo {

    String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject toObject(){
        JSONObject object=new JSONObject();
        try {
            object.put("name",getName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;

    }
}
