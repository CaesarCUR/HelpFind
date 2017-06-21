package com.example.codejam.helpfind;

import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhouming on 2017/6/20.
 */

public class BroadcastSquareActivity extends AppCompatActivity {
    private static final String TAG = "BroadcastSquareActivity";

    @BindView(R.id.input_broadcast) EditText _makeBroadcast;
    @BindView(R.id.img_write) ImageView _broadText;
    @BindView(R.id.img_camera) ImageView _broadPhoto;
    @BindView(R.id.btn_open_map) Button _btnOpenMap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastsquare);
        ButterKnife.bind(this);

        setResult(RESULT_OK, null); // tell login activity: you can die

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


}
