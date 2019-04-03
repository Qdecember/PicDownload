package com.example.picdownload;

public class NetPicture {
    private String name;
    private String imageUrl;

    public NetPicture(){

    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
