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

        testRecyclerViewAdapter = new TestRecyclerViewAdapter(getActivity(),array);
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
            array.put(viewInfo_null.getJsonObject());
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
            handler.postDelayed(runnable, 3000);

        }
    };

    public void reset(String str1, String str2) {
        try {
            array.getJSONObject(0).put("data", str2 + "%");
            array.getJSONObject(1).put("data", str1 + "");
            testRecyclerViewAdapter.setArray(array);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
