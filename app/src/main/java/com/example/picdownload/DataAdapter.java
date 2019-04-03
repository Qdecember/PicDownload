package com.example.picdownload;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    public Context mcontext;
    public ArrayList<NetPicture> picList;

    public DataAdapter(Context context, ArrayList<NetPicture> netPictures){
        mcontext = context;
        picList = netPictures;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        //添加picView保存子项最外层布局的实例
        View picView;

        public ViewHolder(View itemView){
            super(itemView);
            picView = itemView;
            textView = itemView.findViewById(R.id.net_text);
            imageView = itemView.findViewById(R.id.net_image);
        }
    }



    @Override
        public ViewHolder onCreateViewHolder( ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);
        //设计点击事件
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                NetPicture picture = picList.get(position);
                String url = picture.getImageUrl();
                Intent intent = new Intent(mcontext,PicDownload.class);
                intent.putExtra("imageUrl",url);
                mcontext.startActivity(intent);
            }
        });
//
//        //设置点击事件
//        viewHolder.picView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.textView.setText(picList.get(position).getName());
        //Handler处理AsyncTask返回结果
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:
                        Bitmap bitmap = (Bitmap)msg.obj;
                        viewHolder.imageView.setImageBitmap(bitmap);
                        break;
                    default:
                        break;
                }
            }
        };
        DownTask downTask = new DownTask(picList.get(position).getImageUrl(),handler);
        downTask.execute();

    }

    @Override
    public int getItemCount() {
        return picList.size();
    }



    //异步执行Glide加载bitmap
    class DownTask extends AsyncTask<String,Void,Bitmap>{
        private String url;
        Handler mHandler;
        public  DownTask(String url,Handler handler){
            this.url = url;
            this.mHandler = handler;
        }


        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            try {
                bitmap = Glide.with(mcontext)
                         .load(url)
                         .asBitmap()
                         .into(500,500)
                        .get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            Message msg = mHandler.obtainMessage();
            if (result!=null){
                msg.what = 1;
                msg.obj = result;
            }else{
                msg.what = 2;
            }
            mHandler.sendMessage(msg);
        }
    }


}
