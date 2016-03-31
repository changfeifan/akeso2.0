package com.akeso.akeso20.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akeso.akeso20.R;

import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class PersonViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Object> contents;

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 999;

    public PersonViewAdapter(List<Object> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return position;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_person, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case 1:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_person, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.blue_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 2:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_person, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.green_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 3:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_person, parent, false);
                view.findViewById(R.id.rl_background_top).setBackgroundColor(parent.getResources().getColor(R.color.yellow_light));
                return new RecyclerView.ViewHolder(view) {
                };
            case 4:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_person, parent, false);
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
            case TYPE_HEADER:
                break;
            case TYPE_CELL:
                break;
        }
    }
}