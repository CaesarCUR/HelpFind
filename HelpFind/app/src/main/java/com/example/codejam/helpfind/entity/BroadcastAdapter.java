package com.example.codejam.helpfind.entity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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

    public BroadcastAdapter(ArrayList<CardUser> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<CardUser> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_list, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        holder._userName.setText(mData.get(position).get_userName());
        holder._info.setText(mData.get(position).get_info());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_nearby_img) ImageView _userPhoto;
        @BindView(R.id.item_nearby_user) TextView _userName;
        @BindView(R.id.item_neary_time) TextView _timeSpan;
        @BindView(R.id.item_nearby_info) TextView _info;
        @BindView(R.id.item_nearby_grid) GridView _photoGrid;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
