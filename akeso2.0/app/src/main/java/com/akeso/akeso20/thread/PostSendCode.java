package com.akeso.akeso20.thread;

import android.os.AsyncTask;

import com.akeso.akeso20.constant.Configurations;
import com.akeso.akeso20.constant.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/22.
 */
public class PostSendCode extends AsyncTask<String, Integer, JSONObject> {

    String mobile;

    public PostSendCode(String mobile) {
        this.mobile = mobile;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject json = new JSONObject();
        try {
            json.put("mobile", mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Configurations.POSTSENDCODE;
        JSONObject apiResult = Util.makePostRequest(json.toString(),
                url, "");
//            JSONObject data = new JSONObject(Util.decode(apiResult.getString("user")));
//            FileTool.writeToSDCard(apiResult.toString(), Configurations.FILENAME_USER);
        if (apiResult != null) {
            return apiResult;
        }
        return new JSONObject();
    }
}
