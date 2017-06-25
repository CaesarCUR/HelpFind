package com.example.codejam.helpfind;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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

    @Nullable @BindView(R.id.input_broadcast) EditText _makeBroadcast;
    @Nullable @BindView(R.id.img_write) ImageView _broadText;
    @Nullable @BindView(R.id.img_camera) ImageView _broadPhoto;
    @Nullable @BindView(R.id.list_nearby) RecyclerView _listNearBy;
    @Nullable @BindView(R.id.img_me) ImageView _me;

    /**
     * LayoutManager for RecyclerView should be implemented explicitly before it is used by
     * a RecyclerView instant
     */
    private RecyclerView.LayoutManager mLayoutManager;
    private BroadcastAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastsquare);
        ButterKnife.bind(this);

        setResult(RESULT_OK, null); // tell login activity: you can die

        Glide.with(this).load(R.drawable.zhihu).override(500, 500).into(_me);

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
    }

    /**
     * Initial RecyclerView
     */
    private void initial() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // TODO: initialize mAdapter
        mAdapter = new BroadcastAdapter(getData(), getBaseContext());
        // Set appearance animation for RecyclerView with reload data adapter
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(mAdapter, _listNearBy);
        _listNearBy.setLayoutManager(mLayoutManager);
        // Load wrapped adapter to RecyclerView
        _listNearBy.setAdapter(animatorAdapter);

        // setting header and footer
        setHeaderView(_listNearBy);
        setFooterView(_listNearBy);
    }

    private void setHeaderView(RecyclerView recyclerView) {
        View header = LayoutInflater.from(this).inflate(R.layout.broadcast_list_header, recyclerView, false);
        mAdapter.setmHeaderView(header);
    }

    private void setFooterView(RecyclerView recyclerView) {
        View footer = LayoutInflater.from(this).inflate(R.layout.broadcast_list_footer, recyclerView, false);
        mAdapter.setmFooterView(footer);
    }

    private ArrayList<CardUser> getData() {
        ArrayList<CardUser> data = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<String>(){
            {
                add("file:///C:/Users/Andrew/Desktop/a.jpg");
                add("file:///C:/Users/Andrew/Desktop/b.jpg");
                add("file:///C:/Users/Andrew/Desktop/c.jpg");
                add("file:///C:/Users/Andrew/Desktop/d.jpg");
                add("file:///C:/Users/Andrew/Desktop/e.jpg");
            }};

        for (int i = 0; i < 13; i++) {
            String url = "file:///Users/zhouming/Desktop/catb.png";
            data.add(new CardUser("Andrew", "#云音乐专栏#古典和金属乐的结合会是什么效果？Diablo Swing Orchestral，" +
                    "这个成立于2003年的瑞典先锋金属乐队，将摇摆乐（Swing）、爵士乐、古典乐、前卫交响金属等元素融合在一起，" +
                    "在传统摇滚乐基础上，加入众多爵士乐器，创造出独树一帜的风格", url, urls));
        }

        return data;
    }
}
