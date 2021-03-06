package com.example.ahcogn.fashionview;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

/**
 * Created by ahcogn on 18/09/2017.
 */

public class Fashion implements Serializable {
    private int id;
    private String content;
    private int cost;
    private byte[] image;

    public Fashion(String content, int cost, byte[] image) {
        this.content = content;
        this.cost = cost;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Fashion(int id, String content, int cost, byte[] image) {
        this.id = id;
        this.content = content;
        this.cost = cost;
        this.image = image;
    }

    public Fashion() {

    }

}
