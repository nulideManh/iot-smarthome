package com.group3.smarthome.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.smarthome.LogIn;
import com.group3.smarthome.R;
import com.group3.smarthome.db.DbHelper;

public class Register extends AppCompatActivity {
    EditText name_deit,paswd_deit,vpaswd_deit,tel_deit,email_deit;
    DbHelper helper;
    SQLiteDatabase db2;
    SQLiteDatabase db3;
    Cursor cursor;
    // Xác định tên và cấu trúc cơ sở dữ liệu
    static String Id="_id";
    static String account="account";
    static String password="password";
    static String usertype="usertype";
    static String telephone="telephone";
    static String email="email";
    static String TABLE_NAME="Users";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name_deit = findViewById(R.id.userName);
        paswd_deit = findViewById(R.id.password);
        vpaswd_deit = findViewById(R.id.ver_password);
        tel_deit = findViewById(R.id.tel);
        email_deit = findViewById(R.id.email);
        final Button registerBtn = findViewById(R.id.regBtn);
        final Button switchBtn = findViewById(R.id.switchBtn);
        registerBtn.setOnClickListener(new ClickEvent());
        switchBtn.setOnClickListener(new ClickEvent());

    }
    class ClickEvent implements OnClickListener {
        @SuppressLint("NonConstantResourceId")
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.regBtn:
                    new AlertDialog.Builder(Register.this).setTitle("Gợi ý hệ thống")
                            .setMessage("Bạn chắc chắn？")
                            .setPositiveButton("Chắc chắn", (arg0, arg1) -> regist()).setNegativeButton("Trở về", (arg0, arg1) -> {
                            }).show();
                    break;
                case R.id.switchBtn:
                    Intent intent = new Intent( Register.this,
                            LogIn.class);
                    startActivity(intent);
                    break;
            }
        }
    }
    void add()
    {
        ContentValues values1 = new ContentValues();
        values1.put(account, name_deit.getText().toString());
        values1.put(password, paswd_deit.getText().toString());
        values1.put(usertype, "user");
        values1.put(telephone, tel_deit.getText().toString());
        values1.put(email, email_deit.getText().toString());
        db3=openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
        db3.insert(TABLE_NAME, null, values1);
        db3.close();
    }
    void regist(){
        String name_str = name_deit.getText().toString();
        String psd_str = paswd_deit.getText().toString();
        String vpsd_str = vpaswd_deit.getText().toString();

        if (name_str.equals("") || psd_str.equals("")) {
            Toast.makeText(Register.this, "Không được để trống tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
        }
        else if (!psd_str.equals(vpsd_str)) {
            Toast.makeText(Register.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
        } else {
            // Nhận đối tượng kết nối cơ sở dữ liệu 1: Khởi tạo đối tượng DBHelper và tạo cơ sở dữ liệu 2: Nhận kết nối cơ sở dữ liệu;
            helper = new DbHelper(Register.this);
            db2 = helper.getWritableDatabase();
            //Lấy đối tượng kết nối cơ sở dữ liệu Context.MODE_PRIVATE: (chỉ ứng dụng hiện tại mới có thể truy cập) chế độ tạo cơ sở dữ liệu, tham số thứ ba: nhà máy con trỏ, null có nghĩa là sử dụng con trỏ mặc định
            db2 = openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE, null);
            cursor = db2.rawQuery("select * from Users where account=?", new String[]{name_str});
            // = db.query("Users",null,"account=?",new String[]{name_str},null,null,null);
            if (cursor.getCount() != 0) {
                Toast.makeText(Register.this, "Người dùng đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                add();
                Toast.makeText(Register.this, "Đăng ký thành công, có thể đăng nhập!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}

