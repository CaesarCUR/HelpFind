package com.example.codejam.helpfind;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.codejam.helpfind.entity.BroadcastSubAdapter;
import com.example.codejam.helpfind.entity.ImageAdapter;
import com.example.codejam.helpfind.util.DepthPageTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/23.
 */

public class BrowserActivity extends AppCompatActivity {
    public final static String INTENT_URL_TAG = "IMAGE_URL";
    public final static String INTENT_POS_TAG = "POS_ID";

    @BindView(R.id.viewpager_img) ViewPager _viewPager;

    private ImageAdapter imgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

        initialViewPager();
    }

    private void initialViewPager() {
        final ArrayList<String> imgUrls = getIntent().getStringArrayListExtra(INTENT_URL_TAG);
        final int pos = getIntent().getIntExtra(INTENT_POS_TAG, 0);

        imgAdapter = new ImageAdapter(this, imgUrls);
        _viewPager.setAdapter(imgAdapter);

        _viewPager.setCurrentItem(pos); // 设置起始位置
        _viewPager.setPageTransformer(true, new DepthPageTransformer()); // 修改动画效果

        _viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO: text below ImageView will change
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
