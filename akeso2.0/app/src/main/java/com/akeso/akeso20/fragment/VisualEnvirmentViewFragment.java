package com.akeso.akeso20.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akeso.akeso20.R;
import com.akeso.akeso20.adapter.TestRecyclerViewAdapter;
import com.akeso.akeso20.constant.ViewInfo;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.florent37.materialviewpager.adapter.RecyclerViewMaterialAdapter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class VisualEnvirmentViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private JSONArray array = new JSONArray();

    private ViewInfo viewInfo_illumination = new ViewInfo();
    private ViewInfo viewInfo_humidity = new ViewInfo();
    private ViewInfo viewInfo_null = new ViewInfo();
    Handler handler = new Handler();
    private TestRecyclerViewAdapter testRecyclerViewAdapter;


    public static VisualEnvirmentViewFragment newInstance() {
        return new VisualEnvirmentViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        baseDate();

        testRecyclerViewAdapter = new TestRecyclerViewAdapter(getActivity(), array);
        mAdapter = new RecyclerViewMaterialAdapter(testRecyclerViewAdapter);
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        update();
    }

    public JSONArray baseDate() {
        viewInfo_humidity.setTitle(getString(R.string.title_humidity));
        viewInfo_humidity.setBackground_color(R.color.blue_light);
        viewInfo_illumination.setTitle(getString(R.string.title_illumination));
        viewInfo_illumination.setBackground_color(R.color.red_light);

        viewInfo_null.setType(2);
        try {
            array.put(viewInfo_humidity.getJsonObject());
            array.put(viewInfo_illumination.getJsonObject());
//            array.put(viewInfo_null.getJsonObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void update() {
        handler.postDelayed(runnable, 3000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                reset(getActivity().getIntent().getStringExtra("light"), getActivity().getIntent().getStringExtra("humidity"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(runnable, 1000);

        }
    };

    public void reset(String str1, String str2) {
        try {

            if (str1 != null && !str1.equals("null")) {
                int light = Integer.valueOf(str1.split("/")[0]);
                if (light < 100) {
                    array.getJSONObject(1).put("data", str1);
                    array.getJSONObject(1).put("face", 2);
                    array.getJSONObject(1).put("timeInfo", "过弱");
                    array.getJSONObject(1).put("content", "您目前所处环境光强过弱，如您在这样的环境中长时间用眼，尤其是使用电子设备或者近距离阅读文字，会造成眼部不适和视疲劳，请您加设光线柔和的台灯或者改善室内照明亮度。");
                } else if (light >= 100 && light < 500) {
                    array.getJSONObject(1).put("data", str1);
                    array.getJSONObject(1).put("face", 1);
                    array.getJSONObject(1).put("timeInfo", "适宜");
                    array.getJSONObject(1).put("content", "您目前所处环境光强适当，可以安心在此环境中工作生活。");
                } else {
                    array.getJSONObject(1).put("data", str1);
                    array.getJSONObject(1).put("face", 2);
                    array.getJSONObject(1).put("timeInfo", "过强");
                    array.getJSONObject(1).put("content", "您目前所处环境光强过强，如在此环境中长时间近距离用眼可能会造成眼睛眩光和视疲劳，如为室内请您改善照明环境，为灯具加设灯罩，如为室外请您佩戴墨镜保护眼睛");
                }
            }
            if (str2 != null && !str2.equals("null")) {
                int light = Integer.valueOf(str2);
                if (light > 60) {
                    array.getJSONObject(0).put("data", str2 + "%");
                    array.getJSONObject(0).put("timeInfo", "过度湿润");
                    array.getJSONObject(0).put("content", "您目前的眼周湿度过湿，请您保持室内空气流通，并打开空调除湿。");
                    array.getJSONObject(0).put("face", 2);
                } else if (light < 40) {
                    array.getJSONObject(0).put("data", str2 + "%");
                    array.getJSONObject(0).put("content", "您目前的眼周湿度过干，处理不当会严重威胁眼部健康。请您打开加湿器改善室内空气湿度，用1分钟的时间做20次完全瞬目，并用温毛巾热敷眼部10分钟。如您感觉眼睛干涩症状严重，可适当补充人工泪液滴眼液缓解症状，如长期使用，请您选用无防腐剂的人工泪液制剂。");
                    array.getJSONObject(0).put("face", 2);
                    array.getJSONObject(0).put("timeInfo", "干涩");
                } else {
                    array.getJSONObject(0).put("data", str2 + "%");
                    array.getJSONObject(0).put("content", "您目前的眼周湿度适宜，请您继续保持，长时间使用电子设备时不要忘记眨眼哦。");
                    array.getJSONObject(0).put("face", 1);
                    array.getJSONObject(0).put("timeInfo", "适宜");

                }
            }
            testRecyclerViewAdapter.setArray(array);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
