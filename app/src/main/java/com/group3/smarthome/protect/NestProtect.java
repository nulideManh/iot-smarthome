package com.group3.smarthome;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.smarthome.db.DbHelper;

import java.util.Calendar;

public class NestProtect extends AppCompatActivity {

    Button searchBtn;
    private DbHelper helper;
    SQLiteDatabase db7;
    SQLiteDatabase db8;
    Cursor cursor;
    Cursor cursor1;
    Cursor cursor2;
    Cursor cursor3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nest_protect);

        searchBtn = (Button)findViewById(R.id.searchBtn);
        TextView co =findViewById(R.id.co3);
        TextView smoke =findViewById(R.id.smoke3);
        TextView roomname =findViewById(R.id.roomname);
        //Nhận đối tượng kết nối cơ sở dữ liệu 1: Khởi tạo đối tượng DBHelper và tạo cơ sở dữ liệu 2: Nhận kết nối cơ sở dữ liệu;
        helper =new DbHelper(NestProtect.this);
        db7 = helper.getWritableDatabase();
        //Lấy đối tượng kết nối cơ sở dữ liệu Context.MODE_PRIVATE: (chỉ ứng dụng hiện tại mới có thể truy cập) chế độ tạo cơ sở dữ liệu, tham số thứ ba: nhà máy con trỏ, null có nghĩa là sử dụng con trỏ mặc định
        db7 = openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
        //Nhận kết quả truy vấn
        cursor1=db7.query("SmokeSensor",null,"roomname=?",new String[]{"1"},null,null,null);
        if (cursor1.moveToLast()) {
            @SuppressLint("Range") String co1=cursor1.getString(cursor1.getColumnIndex("CO"));
            co.setText(co1);
            @SuppressLint("Range") String smoke1=cursor1.getString(cursor1.getColumnIndex("smoke"));
            smoke.setText(smoke1);
            @SuppressLint("Range") String rom1=cursor1.getString(cursor1.getColumnIndex("roomname"));
            roomname.setText(rom1);
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NestProtect.this,NestProtectHistory.class);
                startActivity(intent);
            }
        });
    }

    /**
     * @description Ngày bật
     * @param listener Giao diện sẽ được thực thi sau ngày lựa chọn được xác nhận
     * @param curDate Ngày hiện đang hiển thị
     */
    public void showDatePickDialog(DatePickerDialog.OnDateSetListener listener, String curDate) {
        Calendar calendar = Calendar.getInstance();
        int year = 0, month = 0, day = 0;
        try {
            year =Integer.parseInt(curDate.substring(0,curDate.indexOf("-"))) ;
            month =Integer.parseInt(curDate.substring(curDate.indexOf("-")+1,curDate.lastIndexOf("-")))-1 ;
            day =Integer.parseInt(curDate.substring(curDate.lastIndexOf("-")+1,curDate.length())) ;
        } catch (Exception e) {
            e.printStackTrace();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day=calendar.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,DatePickerDialog.THEME_HOLO_LIGHT,listener, year,month , day);
        datePickerDialog.show();
    }
}
