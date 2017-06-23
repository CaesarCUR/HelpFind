package com.example.codejam.helpfind.entity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.codejam.helpfind.R;

import java.util.ArrayList;

/**
 * Created by zhouming on 2017/6/23.
 */

public class ImageAdapter extends PagerAdapter {
    private ArrayList<String> urls;
    private Context mContext;

    public ImageAdapter(Context context, ArrayList<String> urls) {
        this.mContext = context;
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Clean memory for forbidding overflow
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_image,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.big_img);

        Glide.with(mContext).load(urls.get(position))
                .fitCenter()
                .centerCrop()
                .into(imageView);

        container.addView(view);

        return view;
    }

}
