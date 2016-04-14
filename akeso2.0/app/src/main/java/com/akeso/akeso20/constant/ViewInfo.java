package com.akeso.akeso20.constant;

import com.akeso.akeso20.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/1.
 */
public class ViewInfo {
    String title = "title";
    String content = "您当前处于的光线太暗，在这样的环境下阅读会导致您的眼部负担加重，引起不良疾病。";
    int state = 0;
    String timeInfo = "暂无更新";
    String data = "暂无";
    int background_color = R.color.text_gray;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type = 1;

    public int getBackground_color() {
        return background_color;
    }

    public void setBackground_color(int background_color) {
        this.background_color = background_color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public JSONObject getJsonObject() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", getTitle());
        jsonObject.put("content", getContent());
        jsonObject.put("state", getState());
        jsonObject.put("timeInfo", getTimeInfo());
        jsonObject.put("data", getData());
        jsonObject.put("background_color", getBackground_color());
        jsonObject.put("type", getType());
        return jsonObject;
    }

    public ViewInfo JsontoViewInfo(JSONObject jsonObject) {
        ViewInfo info = new ViewInfo();
        info.title = jsonObject.optString("title","");
        info.content = jsonObject.optString("content","");
        info.state = jsonObject.optInt("state",0);
        info.timeInfo = jsonObject.optString("timeInfo","");
        info.data = jsonObject.optString("data","暂无");
        info.background_color = jsonObject.optInt("background_color", R.color.background_gray);
        info.type = jsonObject.optInt("type", 1);
        return info;
    }
}
