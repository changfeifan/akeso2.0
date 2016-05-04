package com.akeso.akeso20.thread;

import android.os.AsyncTask;

import com.akeso.akeso20.constant.Configurations;
import com.akeso.akeso20.constant.Util;

import org.json.JSONObject;

/**
 * 获取动态详情使用的方法
 */
public class RequestPost extends AsyncTask<String, Integer, JSONObject> {
    private String mobile = "";


    public RequestPost(String mobile) {
        this.mobile = mobile;
    }

    protected JSONObject doInBackground(String... params) {
        try {

            JSONObject mobilejson = new JSONObject();
            mobilejson.put("mobile", mobile);
            String url = Configurations.POSTCHECKPHONE;
            JSONObject apiResult = Util.makePostRequest(mobilejson.toString(),
                    url, "");
//            JSONObject data = new JSONObject(Util.decode(apiResult.getString("user")));
//            FileTool.writeToSDCard(apiResult.toString(), Configurations.FILENAME_USER);
            if (apiResult!=null){
                return apiResult;
            }
            return new JSONObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
