package com.example.picdownload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static android.widget.Toast.*;

public class PicDownload extends AppCompatActivity implements PictureSlideFragment.OnFragmentInteractionListener {

    private ActionBar actionBar;
    private TextView image_num;
    private ArrayList<String> urlList;
    private ViewPager viewPager;
    private int position;
    private Button  btn_DownLoad;
    private String imageUrls[]={
            "https://b-ssl.duitang.com/uploads/item/201510/04/20151004001302_QiYvP.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201509/03/20150903001242_MmWPh.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201510/04/20151004001351_2Gmdv.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201507/25/20150725183924_fX2Fa.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201805/18/20180518225131_bcxrs.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201601/18/20160118230945_dLHsV.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201610/07/20161007155042_dLyF8.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201509/11/20150911144235_Q8nEx.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201508/25/20150825175650_THWGK.thumb.700_0.jpeg",
            "https://b-ssl.duitang.com/uploads/item/201508/25/20150825175258_n8PxC.thumb.700_0.jpeg"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_layout);


        btn_DownLoad = findViewById(R.id.download);
        btn_DownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   int currentPos= viewPager.getCurrentItem();
                   DownLoadImageService downLoadImage = new DownLoadImageService(imageUrls[currentPos],PicDownload.this);
                   new Thread(downLoadImage).start();
                  Toast.makeText(PicDownload.this,"下载完成",Toast.LENGTH_SHORT).show();

            }
        });


        //隐藏系统的标题栏
        actionBar = getSupportActionBar();
        actionBar.hide();
        //获取当前项position
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);

        urlList = new ArrayList<>();
        Collections.addAll(urlList,imageUrls);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        image_num = (TextView)findViewById(R.id.tv_num);

        viewPager.setAdapter(new PictureSlidePagerAdapter(getSupportFragmentManager()));
        //设置当前项
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
         @Override
         public void onPageScrolled(int position, float v, int i1) {
             image_num.setText(String.valueOf(position+1)+"/"+urlList.size());
         }

         @Override
         public void onPageSelected(int i) {

         }

         @Override
         public void onPageScrollStateChanged(int i) {

         }
     });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    private class PictureSlidePagerAdapter extends FragmentPagerAdapter{
        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return PictureSlideFragment.newInstance(urlList.get(i));
        }

        @Override
        public int getCount(){
             return urlList.size();
        }

    }


//    class PicTask extends AsyncTask<String,Void,Bitmap> {
//        private String url;
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(PicDownload.this,"开始下载"
//                    ,Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            FutureTarget<Bitmap> target = null;
//            try {
//                target = Glide.with(PicDownload.this)
//                        .load(url)
//                        .asBitmap()
//                        .into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
//                        .get();
//            }
//
//            return null;
//        }
//
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//
//        }
//    }
}
