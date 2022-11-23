package com.group3.smarthome.sigin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group3.smarthome.MainActivity;
import com.group3.smarthome.R;
import com.group3.smarthome.Register;
import com.group3.smarthome.db.DbHelper;

//import android.app.AlertDialog;
public class LogIn extends AppCompatActivity {
    private EditText user, psw;
    private CheckBox rem_psw;
    DbHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button switchBtn = findViewById(R.id.switchBtn);
        switchBtn.setOnClickListener(new com.group3.smarthome.LogIn.mClick());
        Button btnLogin=findViewById(R.id.loginBtn);
        btnLogin.setOnClickListener(new com.group3.smarthome.LogIn.mClick());
        user=findViewById(R.id.userName);

//        //You can create a new SharedPreference to operate on stored files
//        SharedPreferences setting=getSharedPreferences("Users", Context.MODE_PRIVATE);
//        //Writing data like SharedPreference needs to use Editor
//        SharedPreferences. Editor editor = setting. edit();
//        //similar key-value pair
//        editor. putString("name", user. getText(). toString());
//        //editor.apply();
//        editor.commit();







        psw = findViewById(R.id.password);
        rem_psw = findViewById(R.id.check);
        //Nhận đối tượng kết nối cơ sở dữ liệu 1: Khởi tạo đối tượng DBHelper và tạo cơ sở dữ liệu 2: Nhận kết nối cơ sở dữ liệu;
        helper = new DbHelper(com.group3.smarthome.LogIn.this);
        db=helper.getWritableDatabase();
        //Lấy đối tượng kết nối cơ sở dữ liệu Context.MODE_PRIVATE: (chỉ ứng dụng hiện tại mới có thể truy cập) chế độ tạo cơ sở dữ liệu, tham số thứ ba: nhà máy con trỏ, null có nghĩa là sử dụng con trỏ mặc định
        db = openOrCreateDatabase("SmartHomeManagementSystem.db", Context.MODE_PRIVATE,null);
        // Nhận kết quả truy vấn
        cursor = db.query("Users",null,null,null,null,null,null);
        // nhớ mật khẩu
        sp = getSharedPreferences("user_mes", MODE_PRIVATE);

        editor = sp.edit();

        if (sp.getBoolean("flag", false)) {
            String user_read = sp.getString("user", "");
            String psw_read = sp.getString("psw", "");
            user.setText(user_read);
            psw.setText(psw_read);
        }

    }
    class mClick implements View.OnClickListener
    {
        @SuppressLint("NonConstantResourceId")
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.switchBtn:
                    Intent intent = new Intent(com.group3.smarthome.LogIn.this, Register.class);
                    startActivity(intent);
                    break;
                case R.id.loginBtn:
                    //
                    editor.putString("name", user.getText().toString());

                    String user_str = user.getText().toString();
                    String psw_str = psw.getText().toString();
                    if (user_str.equals("") || psw_str.equals("")) {
                        Toast.makeText(com.group3.smarthome.LogIn.this, "Không được để trống tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }else {
                        @SuppressLint("Recycle") Cursor cursor = db.query("Users", null, "account=?", new String[]{user_str}, null, null, null);
                        if(cursor.moveToNext()){

                            @SuppressLint("Range") String psw_query=cursor.getString(cursor.getColumnIndex("password"));
                            @SuppressLint("Range") String telephone = cursor.getString(cursor.getColumnIndex("telephone"));
                            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
                            editor.putString("email", email);
                            editor.putString("telephone", telephone);
                            if(psw_str.equals(psw_query)){
                                // nhớ mật khẩu
                                if (rem_psw.isChecked()) {
                                    editor.putBoolean("flag", true);
                                    editor.putString("user", user_str);
                                    editor.putString("psw", psw_str);
                                    editor.commit();
                                    Toast.makeText(com.group3.smarthome.LogIn.this, "Đã nhớ mật khẩu thành công", Toast.LENGTH_LONG).show();
                                } else {
                                    editor.remove("name");
                                    editor.remove("password");
                                    editor.commit();
                                }
                                Intent intent1 = new Intent(com.group3.smarthome.LogIn.this, MainActivity.class);
                                startActivity(intent1);
                                Toast.makeText(com.group3.smarthome.LogIn.this,"Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(com.group3.smarthome.LogIn.this, "Tên đăng nhập hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(com.group3.smarthome.LogIn.this, "Tài khoản không tồn tại, vui lòng đăng ký trước！", Toast.LENGTH_SHORT).show();
                        }
                    }

            }

        }
    }
}

