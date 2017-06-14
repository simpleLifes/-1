package com.example.xinagmu1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText et_userName;
    EditText password;
    Button btn_zhucePage;
    Button btn_gain;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_userName = (EditText) findViewById(R.id.et_uerName);
        password = (EditText) findViewById(R.id.password);
        btn_zhucePage = (Button) findViewById(R.id.btn_zhucePage);
        btn_gain = (Button) findViewById(R.id.btn_gain);
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/my.db3", null);

        btn_zhucePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et_userName.getText().toString();
                String psw = password.getText().toString();
                Intent result = new Intent();
                result.putExtra("user", user);
                result.putExtra("psw", psw);

                setResult(1, result);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String users = et_userName.getText().toString();
                String psws = password.getText().toString();
                try{
                   inserData(db, users, psws);
                    Cursor cursor = db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);

                }catch (SQLiteAbortException se){
                    db.execSQL("create table news_inf(name VARCHAR(20) "
                            +" primary key autoincrement,"
                            +" psw intager  )");

                    inserData(db,users, psws);
                    Cursor cursor = db.rawQuery("select * from news_inf",null);
                    inflateList(cursor);
                }
                Toast.makeText(RegisterActivity.this, "添加数据", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private  void inserData (SQLiteDatabase db, String name, String psw){
//        执行插入语句
        db.execSQL("insert into news_inf values(null, ?, ?)" , new String[]{name,psw});
    }
    private void inflateList(Cursor cursor){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(RegisterActivity.this,
                R.layout.viewone, cursor,
                        new String[]{"name", "psw"},
                        new int[]{R.id.et_uerName, R.id.password},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

//            显示数据

    }
}
