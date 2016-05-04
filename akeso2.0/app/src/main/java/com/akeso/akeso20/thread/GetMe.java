package com.akeso.akeso20.thread;

import android.os.AsyncTask;

import com.akeso.akeso20.constant.Configurations;
import com.akeso.akeso20.constant.Util;

import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/22.
 */
public class GetMe extends AsyncTask<String, Integer, JSONObject> {

    String token;

    public GetMe(String token) {
        this.token = token;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        String url = Configurations.GETME;
        JSONObject apiResult = Util.makeGetRequest(
                url, token);
        if (apiResult != null) {
            return apiResult;
        }
        return new JSONObject();
    }
}
