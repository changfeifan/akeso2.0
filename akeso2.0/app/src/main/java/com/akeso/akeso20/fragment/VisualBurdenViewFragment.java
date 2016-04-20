package com.akeso.akeso20.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
public class VisualBurdenViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    //    private List<Object> mContentItems = new ArrayList<>();
    private JSONArray array = new JSONArray();

    private ViewInfo viewInfo_accomodation = new ViewInfo();
    private ViewInfo viewInfo_eyestrain = new ViewInfo();
    private ViewInfo viewInfo_time = new ViewInfo();
    Handler handler = new Handler();
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
        viewInfo_accomodation.setBackground_color(R.color.blue_light);
        viewInfo_eyestrain.setTitle(getString(R.string.title_eyestrain));
        viewInfo_eyestrain.setBackground_color(R.color.red_light);
        viewInfo_time.setTitle(getString(R.string.title_time));
        viewInfo_time.setBackground_color(R.color.yellow_light);
        try {
            array.put(viewInfo_accomodation.getJsonObject());
//            array.put(viewInfo_eyestrain.getJsonObject());
            array.put(viewInfo_time.getJsonObject());

            array.getJSONObject(2).put("content", "为了更好的保护我们的眼健康，我们需要适当去户外走动，吸收阳光。保证每天" + Html.fromHtml("<font color='#ffc437'>过度湿润</font>") + "的户外时间是良好的开始");

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
//            array.getJSONObject(0).put("data", str2 + "%");
            int angle = Integer.valueOf(str1);
            if (angle >= 0 && angle <= 5) {
                array.getJSONObject(0).put("data", "0.25-0.5D");
                array.getJSONObject(0).put("content", "当前你所使用的调节力良好，状态放松，请继续保持给您的眼镜一个放松的状态。");
                array.getJSONObject(0).put("face",1);
            } else if (angle > 5 && angle <= 15) {
                array.getJSONObject(0).put("data", "0.5-1D");
                array.getJSONObject(0).put("content", "根据您的预估调节力，您当前正在电脑前工作，建议您工作1小时候眺望远方，并起身运动。");
                array.getJSONObject(0).put("face",2);
            } else {
                array.getJSONObject(0).put("data", "2-3D");
                array.getJSONObject(0).put("content", "您当前可能是在低头写字、阅读或使用手机，建议您20分钟后休息20秒并眺望20米以外的物体。");
                array.getJSONObject(0).put("face",2);
            }

            testRecyclerViewAdapter.setArray(array);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
