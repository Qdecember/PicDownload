package com.example.picdownload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import java.net.URI;
import java.util.concurrent.ExecutionException;


public class DownLoadImageService implements Runnable {

    private File currentFile;
    private String url;
    private Context mcontext;

    public DownLoadImageService(String url, Context context) {
        this.url = url;
        this.mcontext = context;
    }


    @Override
    public void run() {
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mcontext)
                    .load(url)
                    .asBitmap()
                    .into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL)
                    .get();
            if (bitmap!=null){
                 saveImage(mcontext,bitmap);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    private void saveImage(Context context,Bitmap bitmap){

        String Filepath = Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES).getAbsolutePath();
        Log.d("FilePth:",Filepath);
        File appDir = new File(Filepath+"/PicDownLoad");
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = System.currentTimeMillis()+".jpg";
        currentFile = new File(appDir,fileName);
        Log.d("fileName: ",fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (fos!=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentFile.getPath());
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);


    }




}
