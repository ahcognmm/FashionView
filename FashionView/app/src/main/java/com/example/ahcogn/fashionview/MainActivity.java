package com.example.ahcogn.fashionview;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_CODE = 6969;

    Fashion fs;
    ArrayList<Fashion> fashionArray = new ArrayList<>();
    FashionAdapter fashionAdapter = null;
    ListView lvFashion;
    TextView tvlol;
    FashionSQLite db = null;
    String[] arrStr = {"Edit", "Delete"};
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFashion = (ListView) findViewById(R.id.lvFashion);

        bt = (Button) findViewById(R.id.bt);

        fs = new Fashion("hell",200, null);
        //db
        db = new FashionSQLite(MainActivity.this);

        db.addFashion(fs);

        fs = db.getFashionById(0);

        showListView();

        lvFashion.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = DialogMenu(fashionArray.get(i));
                builder.create();
                builder.show();
                return false;
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void showListView() {
        //Array & Adapter
        fashionArray = db.getAllFashion();
        fashionAdapter = new FashionAdapter(this, R.layout.fashio_cus, fashionArray);
        // run :)
        lvFashion.setAdapter(fashionAdapter);
    }

    public AlertDialog.Builder DialogMenu(final Fashion fs) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, arrStr);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        intent.putExtra("itemFashion", fs);
                        startActivityForResult(intent, REQUEST_CODE);
                        break;
                    case 1:
                        db.deleteFashion(fs);
                        showListView();
                        break;
                }
            }

        });
        return builder;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            switch (resultCode) {
                case 9696:
                    Fashion fs = (Fashion) data.getSerializableExtra("itemFashion");
                    db.editFashion(fs);
                    showListView();
                    break;
                case 1507:
                    Fashion fs1 = (Fashion) data.getSerializableExtra("itemFashion");
                    db.addFashion(fs1);
                    showListView();
                    break;

            }
        }if(requestCode==0715&& resultCode== RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView imvTest = (ImageView) findViewById(R.id.imvTest);
            imvTest.setImageBitmap(bitmap);
        }
    }

        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.option_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.addNew:
                    Intent intent = new Intent(MainActivity.this, AddNew.class);
                    int rq = fashionArray.get(fashionArray.size()-1).getId();
                    intent.putExtra("id",rq);
                    startActivityForResult(intent, REQUEST_CODE);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }

        }
    }
