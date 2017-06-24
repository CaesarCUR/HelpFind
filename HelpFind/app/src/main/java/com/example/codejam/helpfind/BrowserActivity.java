package com.example.codejam.helpfind;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/23.
 */

public class BrowserActivity extends FragmentActivity {
    public final static String INTENT_URLS_TAG = "IMAGE_URL";
    public final static String INTENT_POS_TAG = "POS_ID";
    public final static String CURRENT_POS = "CurrentPosition";

    private int pagerPosition;

    @BindView(R.id.vp_image_browser) ViewPager _viewPager;
    @BindView(R.id.tv_image_index) TextView _indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        initialViewPager();

        pagerPosition = getIntent().getIntExtra(INTENT_POS_TAG, 0);

        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(CURRENT_POS);
        }

        _viewPager.setCurrentItem(pagerPosition);
    }

    /**
     * Initialize ViewPager
     */
    private void initialViewPager() {
        ArrayList<String> imgUrls = getIntent().getStringArrayListExtra(INTENT_URLS_TAG);

        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), imgUrls);
        _viewPager.setAdapter(mAdapter);

        CharSequence text = getString(R.string.string_img_index, 1, _viewPager.getAdapter().getCount());
        _indicator.setText(text);

        // update title
        _viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                // update title
                CharSequence text = getString(R.string.string_img_index, position + 1, _viewPager.getAdapter().getCount());
                _indicator.setText(text);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_POS, _viewPager.getCurrentItem());
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);
        }

    }
}
