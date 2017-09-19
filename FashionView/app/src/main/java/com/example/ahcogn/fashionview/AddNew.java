package com.example.ahcogn.fashionview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.R.attr.id;
import static com.example.ahcogn.fashionview.R.id.btOK;
import static com.example.ahcogn.fashionview.R.id.edContent;
import static com.example.ahcogn.fashionview.R.id.edCost;

public class AddNew extends AppCompatActivity {

    HandleImage handle = new HandleImage();
    EditText addContent, addCost;
    Button btAddOK;
    ImageView imvAddNew;
    Intent intent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        intent = this.getIntent();

        id = intent.getIntExtra("id",-1);

        addContent = (EditText) findViewById(R.id.addContent);
        addCost = (EditText) findViewById(R.id.addCost);
        btAddOK = (Button) findViewById(R.id.btAddOk);
        imvAddNew = (ImageView) findViewById(R.id.imvAddNew);

        imvAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent,0715);
            }
        });

        btAddOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fashion newfs = new Fashion();
                newfs.setId(id+1);
                newfs.setContent(addContent.getText().toString());
                newfs.setCost(Integer.parseInt(addCost.getText().toString()));
                BitmapDrawable drawable = (BitmapDrawable) imvAddNew.getDrawable();
                Bitmap bmp = drawable.getBitmap();
                newfs.setImage(handle.ImageView_To_Byte(bmp));
                intent.putExtra("itemFashion",newfs);
                setResult(1507,intent);
                finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0715&& resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imvAddNew.setImageBitmap(bitmap);
        }
    }
}
