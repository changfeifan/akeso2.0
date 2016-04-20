package com.akeso.akeso20.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.constant.ApartInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by changfeifan on 16/4/15.
 */
public class ApartmentActivity extends Activity {

    ListView lv_list;
    ListAdapter listAdapter;

    JSONArray array = new JSONArray();

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, ApartmentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

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
            ApartInfo apartInfo = new ApartInfo();
            apartInfo.setPosition("海淀黄庄分院");
            apartInfo.setName("北京同仁眼科医院");
            apartInfo.setImageId(R.drawable.iv_tongren);
            array.put(apartInfo.toObject());
            apartInfo.setPosition("海淀黄庄分院");
            apartInfo.setName("LOHO眼镜");
            apartInfo.setImageId(R.drawable.iv_loho);
            array.put(apartInfo.toObject());
        }

    }

    class ListAdapter extends BaseAdapter {

        JSONArray array;
        LayoutInflater mInflater;

        ImageView iv_logo;
        TextView tv_name;
        TextView tv_position;
        TextView tv_apart;
        Context context;

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
                convertView = mInflater.inflate(R.layout.list_item_apartment, null);
            }
            try {
                JSONObject object = array.getJSONObject(position);
                iv_logo = (ImageView) convertView.findViewById(R.id.iv_logo);
                tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                tv_position = (TextView) convertView.findViewById(R.id.tv_position);
                tv_apart = (TextView) convertView.findViewById(R.id.tv_apart);

                tv_name.setText(object.getString("name"));
                tv_position.setText(object.getString("position"));
                iv_logo.setImageResource(object.getInt("imageId"));


                tv_apart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final AlertDialog alertDialog = new AlertDialog.Builder(ApartmentActivity.this).create();
                        alertDialog.show();
                        Window window = alertDialog.getWindow();
                        window.setContentView(R.layout.dialog_info);
                        TextView tv_content = (TextView) window.findViewById(R.id.tv_content);
                        tv_content.setText(array.optJSONObject(position).optString("name") + array.optJSONObject(position).optString("position"));
                        TextView tv_confirm = (TextView) window.findViewById(R.id.tv_confirm);
                        tv_confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alertDialog.dismiss();
                            }
                        });

                        TextView tv_phone = (TextView) window.findViewById(R.id.tv_phone);
                        tv_phone.setText("我们会把详细的预约信息发送给您的" + Html.fromHtml("<font color='blue_deep'>手机</font>" + "."));
//                        Toast.makeText(context, "已预约" + , Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }


}
