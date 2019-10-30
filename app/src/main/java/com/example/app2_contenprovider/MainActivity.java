package com.example.app2_contenprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String AUTHORITY="com.example.windows10timt.myapplication";
    static  final String CONTENT_PATH="bookdata";
    static  final  String URL="content://"+ AUTHORITY+"/"+ CONTENT_PATH;
    static  final Uri CONTENT_URI=Uri.parse(URL);
    EditText et_id,et_title,et_author;
    Button bt_luu,bt_select,bt_exit,bt_update;
    GridView gr_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("id_book", Integer.parseInt(et_id.getText().toString()));
                values.put("title", et_title.getText().toString());
                values.put("id_author", Integer.parseInt(et_author.getText().toString()));
                Uri insert_uri = getContentResolver().insert(CONTENT_URI, values);
                Toast.makeText(getApplicationContext(),"Luu thanh cong",  Toast.LENGTH_SHORT).show();
            }
        });
        bt_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                Cursor cursor = getContentResolver().query(CONTENT_URI, null, null,null, "title");
                if(cursor !=null)
                {
                    cursor.moveToNext();
                    do{
                        list.add(cursor.getInt(0) + "");
                        list.add(cursor.getString(1));
                        list.add(cursor.getInt(2) + "");
                    }while (cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,list);
                    gr_display.setAdapter(adapter);
                }else
                    Toast.makeText(getApplicationContext(),"Khong thanh cong",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Anhxa() {
        et_id= findViewById(R.id.edtMs);
        et_title= findViewById(R.id.edtTd);
        et_author=findViewById(R.id.edtTg);
        gr_display =findViewById(R.id.grid);
        bt_luu = findViewById(R.id.btnSae);
        bt_select=findViewById(R.id.btnSelect);
        bt_exit=findViewById(R.id.btnexit);
        bt_update= findViewById(R.id.btnUp);

    }
}
