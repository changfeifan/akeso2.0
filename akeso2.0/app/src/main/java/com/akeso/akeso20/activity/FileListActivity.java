package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.constant.FileInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/20.
 */
public class FileListActivity extends Activity {

    ListView lv_list;
    ListAdapter listAdapter;
    JSONArray array = new JSONArray();

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, FileListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filelist);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setDemo();

        lv_list = (ListView) findViewById(R.id.lv_list);
        listAdapter = new ListAdapter(array, this);
        lv_list.setAdapter(listAdapter);
    }

    private void setDemo() {
        for (int i = 0; i < 5; i++) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName("黄鱼");
            array.put(fileInfo.toObject());
            fileInfo.setName("陈大紫");
            array.put(fileInfo.toObject());
        }

    }

    class ListAdapter extends BaseAdapter {

        JSONArray array;
        LayoutInflater mInflater;
        TextView tv_name;
        TextView tv_click;
        Context context;
        LinearLayout ll_filelist;

        public ListAdapter(JSONArray array, Context context) {
            this.array = array;
            this.mInflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return array.length();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_filelist, null);
            }
            try {
                final JSONObject object = array.getJSONObject(position);
                ll_filelist=(LinearLayout)convertView.findViewById(R.id.ll_filelist);
                tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                tv_click = (TextView) convertView.findViewById(R.id.tv_click);
                tv_name.setText(object.getString("name") + "的验光数据");
//                tv_click.setText(object.getString("click"));

                ll_filelist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FileActivity.show(FileListActivity.this,object.optString("name",""));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }
}
