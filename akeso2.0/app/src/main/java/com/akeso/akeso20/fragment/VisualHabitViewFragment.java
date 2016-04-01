package com.akeso.akeso20.fragment;

import android.os.Bundle;
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

    private ViewInfo viewInfo_neck = new ViewInfo();
    private ViewInfo viewInfo_neckstrain = new ViewInfo();
    private ViewInfo viewInfo_null = new ViewInfo();

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

        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(array));
        mRecyclerView.setAdapter(mAdapter);

        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
    }

    public JSONArray baseDate() {
        viewInfo_neck.setTitle(getString(R.string.title_neck_angle));
        viewInfo_neck.setBackground_color(R.color.purple_light);
        viewInfo_neckstrain.setTitle(getString(R.string.title_neckstrain));
        viewInfo_neckstrain.setBackground_color(R.color.green_light);
        viewInfo_null.setType(2);

        try {
            array.put(viewInfo_neck.getJsonObject());
            array.put(viewInfo_neckstrain.getJsonObject());
            array.put(viewInfo_null.getJsonObject());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}
