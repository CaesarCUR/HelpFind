package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.codejam.helpfind.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/21.
 */

public class BroadcastSubAdapter extends ArrayAdapter<BroadcastSubAdapter.ViewHolder> {
    private Context mContext;
    private List<String> data = new ArrayList<>();
    private int mResource;
    private LayoutInflater mInflater;

    public BroadcastSubAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.data = objects;
        mInflater = LayoutInflater.from(context);
    }

    public static class ViewHolder {

        @Nullable @BindView(R.id.img_item) ImageView _imageView;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;
        if (row == null) {
            row = mInflater.inflate(mResource, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag(); // Easy to recycle view
        }

        Glide.with(mContext)
                .load(data.get(position))
                .placeholder(R.drawable.defaultimg)
                .override(500, 500)
                .centerCrop()
                .into(holder._imageView);

        return row;
    }


}
