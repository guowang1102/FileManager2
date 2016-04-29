package com.wells.filemanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wells.filemanager.R;

/**
 * Created by wells on 16/4/25.
 */
public class LoadingActivity extends TActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initData();
    }

    private void initData() {
        //补丁检测
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
