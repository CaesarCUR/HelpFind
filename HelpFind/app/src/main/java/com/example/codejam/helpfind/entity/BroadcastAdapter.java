package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codejam.helpfind.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/21.
 */

public class BroadcastAdapter extends RecyclerView.Adapter<BroadcastAdapter.ViewHolder> {

    private ArrayList<CardUser> mData;
    private Context mContext;

    public BroadcastAdapter(ArrayList<CardUser> data, Context context) {
        this.mContext = context;
        this.mData = data;
    }

    public void updateData(ArrayList<CardUser> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // instant view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_list, parent, false);
        // instant viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // bind data from ViewHolder
        holder._userName.setText(mData.get(position).get_userName());
        holder._info.setText(mData.get(position).get_info());

        GridLayoutManager mgr = new GridLayoutManager(mContext, 3);

        BroadcastSubAdapter mAdapter = new BroadcastSubAdapter(mData.get(position).get_imageURLs(),
                mContext);
        holder._photoGrid.setLayoutManager(mgr);
        holder._photoGrid.setAdapter(mAdapter);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.item_nearby_img) ImageView _userPhoto;
        @Nullable @BindView(R.id.item_nearby_user) TextView _userName;
        @Nullable @BindView(R.id.item_neary_time) TextView _timeSpan;
        @Nullable @BindView(R.id.item_nearby_info) TextView _info;
        @Nullable @BindView(R.id.item_nearby_grid) RecyclerView _photoGrid;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
