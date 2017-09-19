package com.example.ahcogn.fashionview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by ahcogn on 19/09/2017.
 */

public class HandleImage {
    public byte[] ImageView_To_Byte(Bitmap imgv){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imgv.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap Byte_To_Bitmap(byte[] imgv){
        return BitmapFactory.decodeByteArray(imgv,0,imgv.length);
    }
}
