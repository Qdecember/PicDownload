package com.example.picdownload;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button local_btn = (Button)findViewById(R.id.local_image);
        Button net_btn = (Button)findViewById(R.id.net_image);


        local_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(MainActivity.this,LocalDownload.class);
                startActivity(localIntent);
            }
        });

        net_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(MainActivity.this,WebDownload.class);
                startActivity(webIntent);
            }
        });

    }
}
