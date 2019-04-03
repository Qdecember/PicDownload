package com.example.picdownload;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {

    public Context mcontext;
    public ArrayList<File> mfiles;

    public LocalAdapter(Context context, ArrayList<File> files){
        mcontext = context;
        mfiles = files;
    }



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);
            textView = itemView.findViewById(R.id.net_text);
            imageView = itemView.findViewById(R.id.net_image);
        }

    }





    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int position){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(mfiles.get(position).getName());
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//            }
//        });
        Glide.with(mcontext)
                .load(mfiles.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mfiles.size();
    }

}
