package com.example.ahcogn.fashionview;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahcogn on 18/09/2017.
 */

public class FashionAdapter extends ArrayAdapter<Fashion> {

    HandleImage handle = new HandleImage();
    Activity context = null;
    ArrayList<Fashion> fashionArray = null;
    int layoutId;

    public FashionAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Fashion> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
        this.layoutId = resource;
        this.fashionArray = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(layoutId, null);
        }

        TextView tvID = convertView.findViewById(R.id.tvID);
        TextView tvContent = convertView.findViewById(R.id.tvContent);
        TextView tvCost = convertView.findViewById(R.id.tvCost);
        ImageView imvMain = convertView.findViewById(R.id.imvMain);

        Fashion fs = fashionArray.get(position);
        if (fs.getImage() != null) {
            imvMain.setImageBitmap(handle.Byte_To_Bitmap(fs.getImage()));
        }
        tvID.setText(String.valueOf(fs.getId()));
        tvContent.setText(fs.getContent());
        tvCost.setText(String.valueOf(fs.getCost()));
        return convertView;
    }
}
