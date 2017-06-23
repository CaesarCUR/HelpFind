package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.codejam.helpfind.BrowserActivity;
import com.example.codejam.helpfind.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/21.
 */

public class BroadcastSubAdapter extends RecyclerView.Adapter<BroadcastSubAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();

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
//        Glide.with(mContext).load(mData.get(position))
//                .centerCrop()
//                .fitCenter()
//                .into(holder._imageView);
        holder._imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Test", Toast.LENGTH_SHORT).show();
                // TODO: call BrowserActivity
                Intent intent = new Intent(mContext, BrowserActivity.class);
                intent.putExtra(BrowserActivity.INTENT_POS_TAG, position);
                intent.putExtra(BrowserActivity.INTENT_URL_TAG, mData.get(position));
                mContext.startActivity(intent);
            }
        });

        // TODO: load image
        Glide.with(mContext)
                .load(mData.get(position))
                .thumbnail(0.2f)
                .centerCrop()
                .fitCenter()
                .into(holder._imageView);
//        holder._imageView.setImageResource(R.drawable.cata);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable @BindView(R.id.img_item) ImageView _imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
