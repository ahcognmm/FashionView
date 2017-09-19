package com.example.ahcogn.fashionview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahcogn on 18/09/2017.
 */

public class FashionSQLite extends SQLiteOpenHelper {


    public FashionSQLite(Context context) {
        super(context, "Test", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE fashion ( id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, cost INTEGER, image BLOB )";
        // Execute script.
        db.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST fashion");
        onCreate(db);
    }

    public void addFashion(Fashion fs) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", fs.getId());
        values.put("content", fs.getContent());
        values.put("cost", fs.getCost());
        values.put("image",fs.getImage());

        database.insert("fashion", null, values);
    }

    public ArrayList<Fashion> getAllFashion() {
        ArrayList<Fashion> fashionList = new ArrayList<>();

        String query = "SELECT * FROM fashion";

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Fashion fs = new Fashion();
            fs.setId(cursor.getInt(0));
            fs.setContent(cursor.getString(1));
            fs.setCost(cursor.getInt(2));
            if (cursor.getBlob(3) != null) {
                fs.setImage(cursor.getBlob(3));
            }
            fashionList.add(fs);
        }
        return fashionList;

    }

    public Fashion getFashionById(int id) {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.query("fashion", new String[]{
                "id", "content", "cost", "image"}, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Fashion fs = new Fashion(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), null);
        return fs;
    }

    public int editFashion(Fashion fs) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("content", fs.getContent());
        values.put("cost", fs.getCost());
        values.put("image",fs.getImage());

        // updating row
        return database.update("fashion", values, "id = ?",
                new String[]{String.valueOf(fs.getId())});
    }

    public void deleteFashion(Fashion fs){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        database.delete("fashion","id=?",new String[] {String.valueOf(fs.getId())});

    }

}
