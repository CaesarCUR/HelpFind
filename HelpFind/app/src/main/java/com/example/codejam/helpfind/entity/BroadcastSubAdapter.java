package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.codejam.helpfind.BrowserActivity;
import com.example.codejam.helpfind.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/21.
 */

public class BroadcastSubAdapter extends RecyclerView.Adapter<BroadcastSubAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<String> mData = new ArrayList<>();

    public BroadcastSubAdapter(ArrayList<String> data, Context context) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_image, parent, false);
        // instant viewholder
        BroadcastSubAdapter.ViewHolder viewHolder = new BroadcastSubAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder._imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: call BrowserActivity
                Intent intent = new Intent(mContext, BrowserActivity.class);
                intent.putExtra(BrowserActivity.INTENT_POS_TAG, position);
                intent.putStringArrayListExtra(BrowserActivity.INTENT_URLS_TAG, mData);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        int imgId = R.drawable.a;
        switch (position) {
            case 0:
                imgId = R.drawable.a;
                break;
            case 1:
                imgId = R.drawable.b;
                break;
            case 2:
                imgId = R.drawable.c;
                break;
            case 3:
                imgId = R.drawable.d;
                break;
            case 4:
                imgId = R.drawable.e;
                break;
        }

        // TODO: load image
        Glide.with(mContext)
                .load(imgId)
                .override(500, 500)
//                .centerCrop()
                .fitCenter()
                .into(holder._imageView);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.img_item) ImageView _imageView;
//        ImageView _imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            _imageView = (ImageView) itemView.findViewById(R.id.img_item);
        }
    }

}
