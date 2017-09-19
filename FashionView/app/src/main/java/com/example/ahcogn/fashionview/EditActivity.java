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

public class EditActivity extends AppCompatActivity {

    HandleImage handle = new HandleImage();
    EditText edContent, edCost;
    Button btOK;
    Intent intent;
    ImageView imvEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        intent = this.getIntent();
        Fashion fs = (Fashion) intent.getSerializableExtra("itemFashion");

        final int id = fs.getId();
        edContent = (EditText) findViewById(R.id.edContent);
        edCost = (EditText) findViewById(R.id.edCost);
        btOK = (Button) findViewById(R.id.btOK);
        imvEdit = (ImageView) findViewById(R.id.imvEdit);

        edContent.setText(fs.getContent());
        edCost.setText(String.valueOf(fs.getCost()));

        imvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camIntent,0715);
            }
        });

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fashion newfs = new Fashion();
                newfs.setId(id);
                newfs.setContent(edContent.getText().toString());
                newfs.setCost(Integer.parseInt(edCost.getText().toString()));
                BitmapDrawable drawable = (BitmapDrawable) imvEdit.getDrawable();
                Bitmap bmp = drawable.getBitmap();
                newfs.setImage(handle.ImageView_To_Byte(bmp));
                intent.putExtra("itemFashion",newfs);
                setResult(9696,intent);
                finish();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0715&& resultCode==RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imvEdit.setImageBitmap(bitmap);
        }
    }
}
