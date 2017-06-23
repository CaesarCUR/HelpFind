package com.example.codejam.helpfind;

import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.codejam.helpfind.entity.BroadcastAdapter;
import com.example.codejam.helpfind.entity.CardUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;

/**
 * Created by zhouming on 2017/6/20.
 */

public class BroadcastSquareActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastSquareActivity";

    @BindView(R.id.input_broadcast) EditText _makeBroadcast;
    @BindView(R.id.img_write) ImageView _broadText;
    @BindView(R.id.img_camera) ImageView _broadPhoto;
    @BindView(R.id.btn_open_map) Button _btnOpenMap;
    @BindView(R.id.list_nearby) RecyclerView _listNearBy;

    /**
     * LayoutManager for RecyclerView should be implemented explicitly before it is used by
     * a RecyclerView instant
     */
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastsquare);
        ButterKnife.bind(this);

        setResult(RESULT_OK, null); // tell login activity: you can die

        // initialization for RecyclerView
        initial();

        _broadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement broad text action
            }
        });

        _broadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: implement broad photo distribute action
            }
        });

        _btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open map
            }
        });
    }

    private void initial() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // TODO: initialize mAdapter
        mAdapter = new BroadcastAdapter(getData(), getBaseContext());
        // Set appearance animation for RecyclerView with reload data adapter
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(mAdapter, _listNearBy);
        _listNearBy.setLayoutManager(mLayoutManager);
        // Load wrapped adapter to RecyclerView
        _listNearBy.setAdapter(animatorAdapter);
//        _listNearBy.setItemAnimator();
    }

    private ArrayList<CardUser> getData() {
        ArrayList<CardUser> data = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            String url = "file:///Users/zhouming/Desktop/catb.png";
            data.add(new CardUser("Andrew", "My god! So handsome a genius!", url));
        }

        return data;
    }


}
