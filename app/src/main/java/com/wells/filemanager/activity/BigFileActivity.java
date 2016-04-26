package com.wells.filemanager.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.wells.filemanager.R;
import com.wells.filemanager.adapter.FileListAdapter;
import com.wells.filemanager.util.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wells on 16/4/24.
 */
public class BigFileActivity extends TActivity {
    private ListView fileListView;
    private CheckBox allCheckBox;
    private Button confirmBtn;
    private List<File> files = new ArrayList<File>();

    //默认设置超过100M为大文件
    private int defaultBigFileSize = 2;
    private int defaultSizeType = FileUtils.TYPE_MB;

    private FileListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bigfile);
        setTitle(R.string.bigFile, true);
        initViews();
        initData();
    }

    private void initData() {
        FileUtils.getGreaterSizeFiles(files, new File(Environment.getExternalStorageDirectory().getAbsolutePath()), defaultBigFileSize, defaultSizeType);
        adapter = new FileListAdapter(this, files, R.layout.item_list_file);
        fileListView.setAdapter(adapter);

//        allCheckBox.performClick();

        allCheckBox.setChecked(true);
        adapter.setAllCheck(true);
//        adapter.notifyDataSetChanged();
    }

    private void initViews() {
        fileListView = (ListView) findViewById(R.id.bigfile_listview);
        allCheckBox = (CheckBox) findViewById(R.id.bigfile_all_check);
        confirmBtn = (Button) findViewById(R.id.bigfile_confirm_delete);

        allCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Log.v("info","checked status " +checked);
                if (checked) {
                    adapter.setAllCheck(true);
                } else {
                    adapter.setAllCheck(false);
                }
//                adapter.notifyDataSetChanged();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (File file : files) {
                    if(file.exists()){
                        file.delete();
                    }
                }
                toast("删除成功!");
                adapter.setDatas(new ArrayList<File>());
            }
        });

    }
}
