package com.akeso.akeso20.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akeso.akeso20.R;
import com.akeso.akeso20.constant.ViewInfo;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by cff on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.TestRecyclerViewHolder> {

    private JSONArray array;
    private Context context;
    static final int TYPE_CELL = 999;
    ViewInfo info;

    public TestRecyclerViewAdapter(Context context, JSONArray array) {
        this.context = context;
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
    public TestRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewInfo info = new ViewInfo().JsontoViewInfo(array.optJSONObject(viewType));
        switch (info.getType()) {
            case 1: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                RelativeLayout rl_background_top = (RelativeLayout) view.findViewById(R.id.rl_background_top);
                rl_background_top.setBackgroundColor(parent.getResources().getColor(info.getBackground_color()));
                return new TestRecyclerViewHolder(view) {
                };
            }
            case 0:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.blue_light));
                return new TestRecyclerViewHolder(view) {
                };
            case 2://占位页面
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                CardView card_view = (CardView) view.findViewById(R.id.card_view);
                card_view.setVisibility(View.INVISIBLE);
                return new TestRecyclerViewHolder(view) {
                };
            case 3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.yellow_light));
                return new TestRecyclerViewHolder(view) {
                };
            case 4:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.purple_light));
                return new TestRecyclerViewHolder(view) {
                };
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);

                return new TestRecyclerViewHolder(view) {
                };
            }
            default:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new TestRecyclerViewHolder(view) {
                };
        }
//        return null;
    }

    public void setArray(JSONArray array) {
        this.array = array;
//        Log.e("array",array.toString());
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(TestRecyclerViewHolder holder, int position) {
        holder.setData(array.optJSONObject(position));
        switch (getItemViewType(position)) {
            case TYPE_CELL:

                break;
        }
    }

    class TestRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_time;
        TextView tv_data;
        TextView tv_content;


        public TestRecyclerViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_data = (TextView) itemView.findViewById(R.id.tv_data);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }

        public void setData(JSONObject object) {
            ViewInfo info = new ViewInfo().JsontoViewInfo(object);
            tv_title.setText(info.getTitle());
            tv_time.setText(info.getTimeInfo());
            tv_data.setText(info.getData());
            tv_content.setText(info.getContent());
        }
    }
}