package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NORMAL = 2;

    private View mHeaderView;
    private View mFooterView;

    public BroadcastAdapter(ArrayList<CardUser> data, Context context) {
        this.mContext = context;
        this.mData = data;
    }

    public void updateData(ArrayList<CardUser> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    /**
     * Create ViewHolder, if current view's type is HeaderView or FooterView, return
     * ViewHolder directly
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return new ViewHolder(mHeaderView);
        }
        if (mFooterView != null && viewType == TYPE_FOOTER) {
            return new ViewHolder(mFooterView);
        }

        // or normal view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nearby_list, parent, false);
        return new ViewHolder(v);
    }

    /**
     * Bind view
     *
     * this method return view type for current value pointed by position, but HeaderView and
     * FooterView need not be bound
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            // bind data from ViewHolder
            holder._userName.setText(mData.get(position).get_userName());
            holder._info.setText(mData.get(position).get_info());

            final ImageView imageView = holder._userPhoto;

            Glide.with(mContext).load(R.drawable.cute)
                    .asBitmap().override(100, 100).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });


            // setting grid style for album
            GridLayoutManager mgr = new GridLayoutManager(mContext, 3);

            BroadcastSubAdapter mAdapter = new BroadcastSubAdapter(mData.get(position).get_imageURLs(),
                    mContext);
            holder._photoGrid.setLayoutManager(mgr);
            holder._photoGrid.setAdapter(mAdapter);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null && mFooterView == null) {
            return TYPE_NORMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public View getmHeaderView() {
        return mHeaderView;
    }

    public void setmHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        Button btnOpenMap = (Button) this.mHeaderView.findViewById(R.id.btn_open_map);
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open map
            }
        });
        notifyItemInserted(0);
    }

    public View getmFooterView() {
        return mFooterView;
    }

    public void setmFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        notifyItemInserted(getItemCount() - 1);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.item_nearby_img) ImageView _userPhoto;
        @Nullable @BindView(R.id.item_nearby_user) TextView _userName;
        @Nullable @BindView(R.id.item_nearby_time) TextView _timeSpan;
        @Nullable @BindView(R.id.item_nearby_info) TextView _info;
        @Nullable @BindView(R.id.item_nearby_grid) RecyclerView _photoGrid;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView || itemView == mFooterView) return;
            ButterKnife.bind(this, itemView);
        }

    }


}
