package com.example.xinagmu1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.Touch;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pro;
    EditText et_name, et_psw;
    View pages1,pages2,pages3;
    SQLiteDatabase db;
    ListView list_user;

//    ArrayList<View> pageList;
//    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Button btn_register = (Button) findViewById(R.id.btn_register); //注册
        Button btn_denglu = (Button) findViewById(R.id.btn_degnlu);     //登录
        et_name = (EditText) findViewById(R.id.et_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        list_user = (ListView) findViewById(R.id.list_user);

        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/zmSQL.db3", null);


//        设置viewPager
//        pageList = new ArrayList<>();
//
//        pages1 = LayoutInflater.from(this).inflate(R.layout.viewone,null);
//        pageList.add(pages1);
//        pages2 = LayoutInflater.from(this).inflate(R.layout.viewtwo,null);
//        pageList.add(pages2);
//        pages3 = LayoutInflater.from(this).inflate(R.layout.viewthree,null);
//        pageList.add(pages3);
//
//        MyAdapter adapter = new MyAdapter(pageList);
//        viewPager.setAdapter(adapter);
//
//        viewPager.setCurrentItem(2);   //设置当前页为第一页
//
//        可以像viewPager中加很多个Listener
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            当前滚动的页面，以及偏移量
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
////            显示在屏幕上，并告诉你显示的是哪页
//            @Override
//            public void onPageSelected(int position) {
//                Toast.makeText(MainActivity.this, "这是第"+(position+1)+"页", Toast.LENGTH_SHORT).show();
//            }
//
////            发生滚动
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.example.xinagmu1.registerActivity");
                startActivityForResult(intent,1);
            }
        });
    }

//    拿到结果
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String user = data.getStringExtra("user");
        String psw = data.getStringExtra("psw");
        et_name.setText(user);
        et_psw.setText(psw);

    }
}
