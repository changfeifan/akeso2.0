package com.akeso.akeso20.thread;

import android.os.AsyncTask;

import com.akeso.akeso20.constant.Configurations;
import com.akeso.akeso20.constant.Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/22.
 */
public class PostResetPassword extends AsyncTask<String, Integer, JSONObject> {

    String mobile;
    String code;
    String new_password;

    public PostResetPassword(String mobile, String code, String new_password) {
        this.mobile = mobile;
        this.code = code;
        this.new_password = new_password;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject json = new JSONObject();
        try {
            json.put("mobile", mobile);
            json.put("code",code);
            json.put("new_password",new_password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Configurations.POSTRESETPASSWORD;
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
