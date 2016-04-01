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

        mAdapter = new RecyclerViewMaterialAdapter(new TestRecyclerViewAdapter(array));
        mRecyclerView.setAdapter(mAdapter);



        MaterialViewPagerHelper.registerRecyclerView(getActivity(), mRecyclerView, null);
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
            array.put(viewInfo_eyestrain.getJsonObject());
            array.put(viewInfo_time.getJsonObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
}
