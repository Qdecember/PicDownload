package com.example.picdownload;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WebDownload extends AppCompatActivity {

    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_download);

        Glide.get(this).clearMemory();

        //设置图片recyclerview显示
        recyclerView =(RecyclerView)findViewById(R.id.recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final DataAdapter dataAdapter = new DataAdapter(WebDownload.this,initData());
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setHasFixedSize(true);

    }



    private final String names[]={
            "Apple",
            "coconut",
            "Orange",
            "Watermelon",
            "Grape",
            "potato",
            "Pineapple",
            "Strawberry",
            "Mango",
            "Peach",
    };

    private final String imageUrls[]={
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



    private ArrayList<NetPicture> initData() {
        ArrayList<NetPicture> netPictures = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            NetPicture netPicture = new NetPicture();
            netPicture.setImageUrl(imageUrls[i]);
            netPicture.setName(names[i]);
            netPictures.add(netPicture);
        }
        return netPictures;
    }


}
