package com.group3.smarthome;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.smarthome.db.DbHelper;

public class Update extends AppCompatActivity {
    //Lưu trữ kết quả truy vấn；
    Cursor cursor;
    //For the operation of the database, the core object;
    SQLiteDatabase db4;
    SQLiteDatabase db5;
    DbHelper helper;
    public  int id_this;
    static EditText udedit1,udedit2,udedit3,udedit4,udedit5;
    Button submit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_update);
        udedit1=findViewById(R.id.udedit1);
        udedit2=findViewById(R.id.udedit2);
        udedit3=findViewById(R.id.udedit3);
        udedit4=findViewById(R.id.udedit4);
        udedit5=findViewById(R.id.udedit5);
        Bundle bundle = new Bundle();
        bundle =this.getIntent().getExtras();
        String name=bundle.getString("account");
        udedit1.setText(name);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the database connection object; 1: Instantiate the DBHelper object and create the database; 2: Get the database connection;
                helper =new DbHelper(Update.this);
                db4=helper.getWritableDatabase();
                //Get the database connection object; Context.MODE_PRIVATE: (only the current application can access) the mode of creating the database, the third parameter: cursor factory, null means use the default cursor
                db4=openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
                //Get query results
                cursor=db4.query("Users",null,null,null,null,null,null);
                if( udedit2.getText().toString().equals(udedit3.getText().toString())) {
                    update();
                    Toast.makeText(Update.this,"Sửa đổi thành công",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Update.this, LogIn.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Update.this,"Hai mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


    private void update(){
        ContentValues values=new ContentValues();

        values.put("password",Update.udedit2.getText().toString());
        values.put("telephone",Update.udedit4.getText().toString());
        values.put("email",Update.udedit5.getText().toString());
        String where = "account" + " = '"+ Update.udedit1.getText().toString()+"'" ;
        //Lấy đối tượng kết nối cơ sở dữ liệu；
        helper=new DbHelper(Update.this);
        db5=helper.getWritableDatabase();
        //2. Dữ liệu đã sửa đổi, 4. Mảng giá trị dữ liệu đã sửa đổi
        db5.update("Users",values,where,null);
        Toast.makeText(Update.this,"Dữ liệu được sửa đổi thành công",Toast.LENGTH_SHORT).show();
    }


}

