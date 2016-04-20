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
 * Created by cff on 24/04/15.
 */
public class VisualHabitViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private JSONArray array = new JSONArray();
    private TestRecyclerViewAdapter testRecyclerViewAdapter = new TestRecyclerViewAdapter(getActivity(), array);
    private ViewInfo viewInfo_neck = new ViewInfo();
    private ViewInfo viewInfo_neckstrain = new ViewInfo();
    private ViewInfo viewInfo_null = new ViewInfo();
    Handler handler = new Handler();

    public static VisualHabitViewFragment newInstance() {
        return new VisualHabitViewFragment();
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

        mAdapter = new RecyclerViewMaterialAdapter(testRecyclerViewAdapter);
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);

        update();
    }


    public JSONArray baseDate() {
        viewInfo_neck.setTitle(getString(R.string.title_neck_angle));
        viewInfo_neck.setBackground_color(R.color.purple_light);
        viewInfo_neckstrain.setTitle(getString(R.string.title_neckstrain));
        viewInfo_neckstrain.setBackground_color(R.color.green_light);
        viewInfo_null.setType(2);

        try {
            array.put(viewInfo_neck.getJsonObject());
//            array.put(viewInfo_neckstrain.getJsonObject());
            array.put(viewInfo_null.getJsonObject());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;

    }

    public void update() {
        handler.postDelayed(runnable, 1000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                handler.postDelayed(this, 1000);
               if (getActivity().getIntent().getStringExtra("neck") != null && getActivity().getIntent().getStringExtra("angle") != null)
                    reset(getActivity().getIntent().getStringExtra("neck"), getActivity().getIntent().getStringExtra("angle"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public void reset(String str, String angle) {
        try {
            if (str.equals("") || str == null) {
                return;
            }
            viewInfo_neck.setData(angle + "°");
            array.getJSONObject(0).put("data", angle + '°');
            if (str.equals("96")) {
                array.getJSONObject(0).put("content", "目前状态良好，请继续保持");
                array.getJSONObject(0).put("face",1);
                array.getJSONObject(0).put("timeInfo", "良好");
            } else if (str.equals("97")) {
                array.getJSONObject(0).put("content", "请您在工作和学习时注意坐姿，尽量减少低头的角度和时间，并在工作1h后，活动颈部肌肉。");
                array.getJSONObject(0).put("face",2);
                array.getJSONObject(0).put("timeInfo", "适宜");
            } else if (str.equals("98")) {
                array.getJSONObject(0).put("content", "当前用眼弧度过高，请您调整坐姿，为避免过度使用，建议工作30分钟后，活动颈部肌肉。");
                array.getJSONObject(0).put("face",2);
                array.getJSONObject(0).put("timeInfo", "不良");

            } else {
                viewInfo_neck.setData("暂无");
                array.getJSONObject(0).put("data", "暂无");
            }

            testRecyclerViewAdapter.notifyDataSetChanged();
            mAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.e("json",array.toString());

    }

}
