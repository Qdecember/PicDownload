package com.example.picdownload;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class LocalDownload extends AppCompatActivity {

    //private static final String TAG = LocalDownload.class.getSimpleName();
    private RecyclerView recyclerView;
    private ArrayList<File> files = new ArrayList<>();
    //得到存储的绝对路径
    private String storagePath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_download);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final LocalAdapter LocalAdapter = new LocalAdapter(LocalDownload.this, files);
        recyclerView.setAdapter(LocalAdapter);
        recyclerView.setHasFixedSize(true);


        //Log.d(TAG, "onCreate: "+storagePath);
        //打印文件路径
        /*for (File file : files) {
            Log.d(TAG, "onCreate: "+file.getAbsolutePath());
        }*/

    }

    public String paths[]={

            storagePath+"sina/weibo/weibo/1549745853715_177a38f3-dd5f-4e53-91e0-208458eea87d_by_weibo_editor.jpg",
            storagePath+"sina/weibo/weibo/1550584141534_ba201fb7-f8ea-471e-80b1-7ed60852f45f_by_weibo_editor.jpg",
            storagePath+"sina/weibo/weibo/2017学术动态模板4-3 - 副本.jpg",
            storagePath+"sina/weibo/weibo/img-01a3b4f1168c1edd802956a27d5612e6.jpg",
            storagePath+"sina/weibo/weibo/img-0225f0ebb1a63eb7ddc8fe1c12dbafc1.jpg",
            storagePath+"sina/weibo/weibo/img-04b6df7757b4dbfd774b5680e8cce366.jpg",
            storagePath+"sina/weibo/weibo/img-084b917a5d0b9fe78bfacc42694d4e81.jpg",
            storagePath+"sina/weibo/weibo/img-08e22fc2c19cd7e159c4e0061296ae67.jpg",
            storagePath+"sina/weibo/weibo/img-09affe2e812fb53745df1f752cffad7a.jpg",
            storagePath+"sina/weibo/weibo/img-0b0554d65fd58e41c845a538850b77a2.jpg",
            storagePath+"sina/weibo/weibo/img-0ca6fc016dff4c4c35c6c87e47eab4f1.jpg",


    };


    private void initData(){
        //File类直接传入图片存储路径
        File Apple = new File(paths[0]);
        files.add(Apple);
        File coconut = new File(paths[1]);
        files.add(coconut);
        File Orange = new File(paths[2]);
        files.add(Orange);
        File Watermelon = new File(paths[3]);
        files.add(Watermelon);
        File Grape = new File(paths[4]);
        files.add(Grape);
        File potato = new File(paths[5]);
        files.add(potato);
        File Pineapple = new File(paths[6]);
        files.add(Pineapple);
        File Strawberry = new File(paths[7]);
        files.add(Strawberry);
        File Mango = new File(paths[8]);
        files.add(Mango);
        File Peach = new File(paths[9]);
        files.add(Peach);

    }


}




