package com.example.bai76;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<RSSItem> data = new ArrayList<>();
    ListView danhsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        danhsach = findViewById(R.id.lvDanhSach);


        XMLParser parser = new XMLParser(MainActivity.this, data,danhsach);
        parser.execute("https://vnexpress.net/rss/tin-moi-nhat.rss");

    }
}