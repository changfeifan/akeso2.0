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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class VisualBurdenViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    //    private List<Object> mContentItems = new ArrayList<>();
    private JSONArray array = new JSONArray();

    private ViewInfo viewInfo_accomodation = new ViewInfo();
    private ViewInfo viewInfo_eyestrain = new ViewInfo();
    private ViewInfo viewInfo_time = new ViewInfo();
    Handler handler = new Handler();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private TestRecyclerViewAdapter testRecyclerViewAdapter;

    public static VisualBurdenViewFragment newInstance() {
        return new VisualBurdenViewFragment();
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
        viewInfo_accomodation.setTitle(getString(R.string.title_accomodation));
        viewInfo_accomodation.setBackground_color(R.color.green_light);
        viewInfo_eyestrain.setTitle(getString(R.string.title_eyestrain));
        viewInfo_eyestrain.setBackground_color(R.color.red_light);
        viewInfo_time.setTitle(getString(R.string.title_time));
        viewInfo_time.setBackground_color(R.color.yellow_light);
        viewInfo_time.setType(3);
        try {
            array.put(viewInfo_accomodation.getJsonObject());
            array.put(viewInfo_time.getJsonObject());
            array.put(viewInfo_eyestrain.getJsonObject());

            array.getJSONObject(1).put("content", "为了更好地保护眼睛，每天需要保证2小时的户外活动时间。");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void update() {
        handler.postDelayed(runnable, 2000);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                reset(getActivity().getIntent().getStringExtra("angle"), getActivity().getIntent().getStringExtra("humidity"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.postDelayed(runnable, 1000);

        }
    };

    public void reset(String str1, String str2) {
        try {
            long l = System.currentTimeMillis() - 5965000;
            Date date = new Date(l);
            int hour = date.getHours();
            int min = date.getMinutes();
            int sec = date.getSeconds();

            array.getJSONObject(1).put("time_in", hour + "小时" + min + "分" + sec + "秒");
            array.getJSONObject(1).put("content", "为了更好的保护我们的眼健康，我们需要适当去户外走动，吸收阳光。保证每天2小时的户外时间是良好的开始。");
            testRecyclerViewAdapter.setArray(array);
            mAdapter.notifyDataSetChanged();


//            array.getJSONObject(0).put("data", str2 + "%");
            if (str1 != null && !str1.equals("暂无") ) {
                int angle = Integer.valueOf(str1);
                if (angle >= 0 && angle <= 5) {
                    array.getJSONObject(0).put("data", "0.25-0.5D");
                    array.getJSONObject(0).put("content", "当前眼睛状态放松，请继续保持。");
                    array.getJSONObject(0).put("face", 1);
                    array.getJSONObject(0).put("timeInfo", "0.25-0.5D");

                } else if (angle > 5 && angle <= 15) {
                    array.getJSONObject(0).put("data", "0.5-1D");
                    array.getJSONObject(0).put("content", "当前处于近距离工作状态，建议工作1小时后闭眼休息5分钟，同时做眼保健操，帮助眼周血液循环.");
                    array.getJSONObject(0).put("face", 2);
                    array.getJSONObject(0).put("timeInfo", "0.5-1D");

                } else {
                    array.getJSONObject(0).put("data", "2-3D");
                    array.getJSONObject(0).put("content", "当前眼睛负担较高，建议工作30分钟后闭眼休息5分钟，同时做眼保健操，帮助眼周血液循环，并远眺10分钟帮助眼睛放松。");
                    array.getJSONObject(0).put("face", 2);
                    array.getJSONObject(0).put("timeInfo", "2-3D");

                }
            }


            testRecyclerViewAdapter.setArray(array);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
