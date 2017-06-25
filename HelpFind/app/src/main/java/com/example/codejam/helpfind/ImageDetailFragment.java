package com.example.codejam.helpfind;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Andrew on 2017/6/24.
 */

public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    @BindView(R.id.iv_detail) ImageView mImageView;
//    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;

    private ProgressDialog progressDialog;

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = (getArguments() != null ? getArguments().getString("url") : null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.image_detail_fragment, container, false);
        ButterKnife.bind(this, v);
        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                getActivity().finish();
            }

            @Override
            public void onOutsidePhotoTap() {

            }
        });

        progressDialog = new ProgressDialog(getContext(), R.style.ImageLoading);

//        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        progressDialog.setIndeterminate(true);

        return v;
    }

    private SimpleTarget target = new SimpleTarget() {
        @Override
        public void onResourceReady(Object resource, GlideAnimation glideAnimation) {
            progressDialog.dismiss();
            mImageView.setImageBitmap((Bitmap)resource);
            mAttacher.update();
        }

        @Override
        public void onStart() {
            super.onStart();
            progressDialog.show();
        }
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Glide.with(getContext())
//                .load(mImageUrl)
//                .asBitmap()
//                .into(target);
        Glide.with(getContext()).load(R.drawable.a).asBitmap().into(target);
    }
}
