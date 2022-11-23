package com.group3.smarthome;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bedroom extends Fragment {
    private BedroomInfoViewModel BedroomInfoViewModel;
    private DbHelper helper;
    SQLiteDatabase db7;
    SQLiteDatabase db8;
    Cursor cursor;
    Cursor cursor1;
    Cursor cursor2;
    Cursor cursor3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BedroomInfoViewModel =
                ViewModelProviders.of(this).get(BedroomInfoViewModel.class);
        final View root = inflater.inflate(R.layout.room_bedroom, container, false);
        final TextView textView = root.findViewById(R.id.bedroom);
        BedroomInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        TextView tem1 =root.findViewById(R.id.tem1);
        TextView day1 =root.findViewById(R.id.day1);
        TextView window1 =root.findViewById(R.id.window1);
        TextView smoke1 =root.findViewById(R.id.smoke1);
        //Nhận đối tượng kết nối cơ sở dữ liệu 1: Khởi tạo đối tượng DBHelper và tạo cơ sở dữ liệu 2: Nhận kết nối cơ sở dữ liệu;
        helper =new DbHelper(getActivity());
        db7=helper.getWritableDatabase();
        //Lấy đối tượng kết nối cơ sở dữ liệu Context.MODE_PRIVATE: (chỉ ứng dụng hiện tại mới có thể truy cập) chế độ tạo cơ sở dữ liệu, tham số thứ ba: nhà máy con trỏ, null có nghĩa là sử dụng con trỏ mặc định
        db7 = getActivity().openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
        //获取查询结果
        cursor=db7.query("BedroomInfo",null,null,null,null,null,null);
        cursor1=db7.query("SmokeSensor",null,null,null,null,null,null);
        cursor2=db7.query("WindowsSensor",null,null,null,null,null,null);
        cursor3=db7.query("TemSensor",null,null,null,null,null,null);
        ContentValues values5 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values3 = new ContentValues();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//
        Date date = new Date(System.currentTimeMillis());
        values4.put("roomname", "1");
        values4.put("smoke", "Bình thường");
        values4.put("state", "Đang mở");
        values4.put("date", simpleDateFormat.format(date));
        db7.insert("WindowsSensor", null, values4);

        if (cursor1.moveToLast()) {
            @SuppressLint("Range") String co1=cursor1.getString(cursor1.getColumnIndex("CO"));
            @SuppressLint("Range") String s1=cursor1.getString(cursor1.getColumnIndex("smoke"));
            String smokestates ="CO:"+co1+"S:"+s1;
            values5.put("smokesatate", smokestates);
        }
        if (cursor2.moveToLast()) {

            @SuppressLint("Range") String state1=cursor2.getString(cursor2.getColumnIndex("state"));

            values5.put("windowsstate",state1);
        }

        if (cursor3.moveToLast()) {
            @SuppressLint("Range") String tem2=cursor3.getString(cursor3.getColumnIndex("temperature"));
            values5.put("temperature", tem2);
        }
//        db7.insert("BedroomInfo", null, values5);
        //setText("Hiện tại "+simpleDateFormat.format(date));

        /*values5.put("smokesatate", "Bình thường");
        values5.put("windowsstate", "Đang mở");
        values5.put("temperature", "25℃");
        values5.put("humidity", "55%");
        //values5.put("date", simpleDateFormat.format(date));
        values4.put("smokesatate", "Bình thường");
        values4.put("windowsstate", "Đang đóng");
        values4.put("temperature", "24.6℃");
        //values4.put("date", simpleDateFormat.format(date));
        values3.put("smokesatate", "Bình thường");
        values3.put("windowsstate", "Đang mở");
        values3.put("temperature", "24.6℃");
        values3.put("humidity", "52%");
        //values3.put("date", simpleDateFormat.format(date));
        db7.insert("BedroomInfo", null, values5);
        db7.insert("BedroomInfo", null, values4);
        db7.insert("BedroomInfo", null, values3);*/
        if (cursor.moveToLast()) {
            @SuppressLint("Range") String s=cursor.getString(cursor.getColumnIndex("smokesatate"));
            @SuppressLint("Range") String w=cursor.getString(cursor.getColumnIndex("windowsstate"));
            @SuppressLint("Range") String t=cursor.getString(cursor.getColumnIndex("temperature"));

            smoke1.setText(s);
            window1.setText(w);
            tem1.setText(t);
            day1.setText(simpleDateFormat.format(date));
            db7.close();
        }
        final Button bedroomdetail1 = root.findViewById(R.id.bedroomdetail1);
        final Button bedroomdetail2 = root.findViewById(R.id.bedroomdetail2);
        final Button bedroomdetail3 = root.findViewById(R.id.bedroomdetail3);
        bedroomdetail1.setOnClickListener(new mClick1());
        // De add sau `````````````
//        bedroomdetail2.setOnClickListener(new mClick3());
//        bedroomdetail3.setOnClickListener(new mClick2());
        return root;
    }

    class mClick1 implements View.OnClickListener {
        public void onClick(View v) {
            Intent intent1=new Intent(getActivity(),NestProtect.class);
            startActivity(intent1);
        }
    }

    // De add sau !!!!!!!!!!!!!!!!!!!
//    class mClick2 implements View.OnClickListener {
//        public void onClick(View v) {
//            Intent intent1=new Intent(getActivity(),DoorSensor.class);
//            startActivity(intent1);
//        }
//    }
//    class mClick3 implements View.OnClickListener {
//        public void onClick(View v) {
//            Intent intent1=new Intent(getActivity(),TemperatureSensor.class);
//            startActivity(intent1);
//        }
//    }
}
