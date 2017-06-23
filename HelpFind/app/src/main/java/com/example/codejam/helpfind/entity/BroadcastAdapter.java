package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.codejam.helpfind.R;
import com.example.codejam.helpfind.util.GlideCircleTransform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

        // Load user photo
//        Glide.with(mContext)
//                .load(mData.get(position).get_imgURL())
//                .placeholder(R.drawable.defaultimg)
//                .override(150, 150)
//                .transform(new GlideCircleTransform(mContext))
//                .into(holder._userPhoto);

        bindGridView(holder._photoGrid);

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    /**
     * Bind data for GridView
     * @param gridView
     */
    private void bindGridView(GridView gridView) {
        // TODO: this block implement the initialization for GridView
        List<String> urls = new ArrayList<String>(){
            {
                add("http://pics1.pof.com/dating/221/89/4iozl43dvlhe1xlskxev0f10w369212433.jpg");
                add("http://pics1.pof.com/dating/221/89/4iozl43dvlhe1xlskxev0f10w369212433.jpg");
                add("http://pics1.pof.com/dating/221/89/4iozl43dvlhe1xlskxev0f10w369212433.jpg");
            }};

        if (null == gridView.getAdapter()) {
            BroadcastSubAdapter mAdapter = new BroadcastSubAdapter(mContext, R.layout.item_grid_image, urls);
            gridView.setAdapter(mAdapter);
        } else {
            gridView.getAdapter().notify();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @Nullable @BindView(R.id.item_nearby_img) ImageView _userPhoto;
        @Nullable @BindView(R.id.item_nearby_user) TextView _userName;
        @Nullable @BindView(R.id.item_neary_time) TextView _timeSpan;
        @Nullable @BindView(R.id.item_nearby_info) TextView _info;
        @Nullable @BindView(R.id.item_nearby_grid) GridView _photoGrid;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
