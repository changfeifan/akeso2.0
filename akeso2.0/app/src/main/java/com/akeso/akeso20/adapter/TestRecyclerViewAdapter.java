package com.akeso.akeso20.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.constant.ViewInfo;

import org.json.JSONArray;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    JSONArray array;

    static final int TYPE_CELL = 999;

    public TestRecyclerViewAdapter(JSONArray array) {
        this.array = array;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            default:
                return position;
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewInfo info = new ViewInfo().JsontoViewInfo(array.optJSONObject(viewType));
        switch (info.getType()) {
            case 1: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                RelativeLayout rl_background_top=(RelativeLayout)view.findViewById(R.id.rl_background_top);
                rl_background_top.setBackgroundColor(parent.getResources().getColor(info.getBackground_color()));

                TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
                TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
                TextView tv_data = (TextView) view.findViewById(R.id.tv_data);
                TextView tv_content=(TextView)view.findViewById(R.id.tv_content);

                tv_title.setText(info.getTitle());
                tv_time.setText(info.getTimeInfo());
                tv_data.setText(info.getData());
                tv_content.setText(info.getContent());

                return new RecyclerView.ViewHolder(view) {
                };
            }
            case 0:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.blue_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.green_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.yellow_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 4:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.purple_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);

                return new RecyclerView.ViewHolder(view) {
                };
            }
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
        }
//        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_CELL:
                break;
        }
    }
}