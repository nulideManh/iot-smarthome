package com.group3.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;

import com.group3.smarthome.db.DbHelper;

public class NestProtectHistory extends AppCompatActivity {
    private DbHelper helper;
    SQLiteDatabase db7;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nest_protect_history);



        // set background table title
        ViewGroup tableTitle = (ViewGroup) findViewById(R.id.table_title);
        tableTitle.setBackgroundColor(Color.rgb(177, 173, 172));

        //Nhận đối tượng kết nối cơ sở dữ liệu 1: Khởi tạo đối tượng DBHelper và tạo cơ sở dữ liệu 2: Nhận kết nối cơ sở dữ liệu;
        helper = new DbHelper(NestProtectHistory.this);
        db7 = helper.getWritableDatabase();
        db7 = openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
        //Lấy đối tượng kết nối cơ sở dữ liệu Context.MODE_PRIVATE: (chỉ ứng dụng hiện tại mới có thể truy cập) chế độ tạo cơ sở dữ liệu, tham số thứ ba: nhà máy con trỏ, null có nghĩa là sử dụng con trỏ mặc định
        //Nhận kết quả truy vấn
        cursor=db7.query("SmokeSensor",null,"roomname=?",new String[]{"1"},null,null,null);

        // !!!!!!!!!!!add sau nho
//        if (!cursor.isFirst())
//        {
//            cursor.moveToFirst();
//            List<SmokeSensorEntity> list = new ArrayList<SmokeSensorEntity>();
//            ListView listView=findViewById(R.id.list);
//            while(!cursor.isLast()) {
//                cursor.moveToNext();
//                int id_this = Integer.parseInt(cursor.getString(0));
//                String room = cursor.getString(1);
//                String smoke = cursor.getString(2);
//                String CO = cursor.getString(3);
//                String date = cursor.getString(4);
//
//                list.add(new SmokeSensorEntity(id_this, date, room, smoke, CO ));
//
//
//                // break;
//            }
//            NestProctectAdapter  adapter = new NestProctectAdapter (this, list);
//            listView.setAdapter(adapter);
//
//        }


    }
}

