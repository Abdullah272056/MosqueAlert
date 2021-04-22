package com.example.mosquealert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnContactClickListener{
RecyclerView recyclerView;

    DataBaseHelper dataBaseHelper;
    CustomAdapter customAdapter;
    private List<Notes> dataList;

    CustomAdapter.OnContactClickListener onContactClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onContactClickListener=this;
        recyclerView=findViewById(R.id.recyclerViewId);
        dataBaseHelper=new DataBaseHelper(MainActivity.this);
        dataBaseHelper.getWritableDatabase();
        loadData(onContactClickListener);
    }
    private void loadData(CustomAdapter.OnContactClickListener onContactClickListener){
        dataList  = new ArrayList<>();
        dataList = dataBaseHelper.getAllNotes();


        if (dataList.size() > 0){
            customAdapter = new CustomAdapter(MainActivity.this,dataList,onContactClickListener);
            recyclerView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        }else {
            for (int i = 0; i <=5; i++) {
                int id=dataBaseHelper.insertData(new Notes("Wakto Name","0:00 AM","0 Minute","00",
                        "00","www",0));
                if (id!=0){
                    Toast.makeText(this, "success "+String.valueOf(i), Toast.LENGTH_SHORT).show();
                }
            }
            dataList  = new ArrayList<>();
            dataList = dataBaseHelper.getAllNotes();
            customAdapter = new CustomAdapter(MainActivity.this,dataList,onContactClickListener);
            recyclerView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }

    @Override
    public void onContactClick(int position) {
        Toast.makeText(this, "position", Toast.LENGTH_SHORT).show();
    }
}