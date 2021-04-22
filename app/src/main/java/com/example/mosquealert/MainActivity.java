package com.example.mosquealert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

    // CustomDialog
    EditText waktoNameEditText,minuteEditText;
    Button cancelButton ,saveButton;
    AlarmManager alarm;
    PendingIntent alarmIntent;
    String endTime;

    CustomAdapter.OnContactClickListener onContactClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onContactClickListener=this;
        recyclerView=findViewById(R.id.recyclerViewId);
        dataBaseHelper=new DataBaseHelper(MainActivity.this);
        dataBaseHelper.getWritableDatabase();
        loadData();
    }
    private void loadData(){
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
    public void onContactClick(int position, Switch switchButton) {
        Toast.makeText(this, "position", Toast.LENGTH_SHORT).show();
        switchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "swwww", Toast.LENGTH_SHORT).show();
            }
        });

        CustomDialog(position);
    }

        private void CustomDialog(final int position){


        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this);
        bottomSheetDialog.setContentView(R.layout.input_layout);
        bottomSheetDialog.setCanceledOnTouchOutside(false);

        saveButton=bottomSheetDialog.findViewById(R.id.saveButtonId);
        cancelButton=bottomSheetDialog.findViewById(R.id.cancelButtonId);
        minuteEditText=bottomSheetDialog.findViewById(R.id.minuteEditTextId);
        waktoNameEditText=bottomSheetDialog.findViewById(R.id.waktoNameEditTextId);
        final TimePicker startTimePicker =bottomSheetDialog.findViewById(R.id.timePickerId);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String waktoName=waktoNameEditText.getText().toString();
                String endMinute=minuteEditText.getText().toString();
                Long startTimeMilliSecond,endTimeMilliSecond;
                if (TextUtils.isEmpty(waktoName)){
                    waktoNameEditText.setError("Enter wakto name");
                    waktoNameEditText.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(endMinute)){
                    minuteEditText.setError("Enter minute name");
                    minuteEditText.requestFocus();
                    return;
                }


                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
                int hour=startTimePicker.getCurrentHour();
                int minute=startTimePicker.getCurrentMinute();
                Calendar startTimeCalender=Calendar.getInstance();
                startTimeCalender.set(Calendar.HOUR_OF_DAY,hour);
                startTimeCalender.set(Calendar.MINUTE,minute);
                startTimeCalender.set(Calendar.SECOND,0);
                String startTimeString = dateFormat.format(startTimeCalender.getTime()).toString();

                startTimeMilliSecond=startTimeCalender.getTimeInMillis();

                int addMinute=Integer.parseInt(endMinute);
                endTimeMilliSecond=startTimeMilliSecond+(addMinute*60000);



                long id=dataBaseHelper.updateData(new Notes(dataList.get(position).getId(),waktoName,startTimeString,endMinute,String.valueOf(startTimeMilliSecond),
                        String.valueOf(endTimeMilliSecond),"www",1));
                if (id==1){
                    bottomSheetDialog.dismiss();
                    Toast.makeText(MainActivity.this, "update success", Toast.LENGTH_SHORT).show();
                    loadData();
                }

//                if (id==1){
//
//                    Intent intent=new Intent(MainActivity.this, NotificationReceiver.class);
//                    intent.putExtra("notificationRequestCode",id);
//                    intent.putExtra("TargetTimeMilliSecond",startTimeMilliSecond);
//                    alarmIntent= PendingIntent.getBroadcast(MainActivity.this,
//                            (int) id,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                    //get ALARM_SERVICE from SystemService
//                    alarm= (AlarmManager) getSystemService(ALARM_SERVICE);
//                    //alarm set
//                    alarm.set(AlarmManager.RTC_WAKEUP,startTimeMilliSecond,alarmIntent);
//
//                    alarmIntent= PendingIntent.getBroadcast(MainActivity.this,
//                            (int) id+100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                    //get ALARM_SERVICE from SystemService
//                    alarm= (AlarmManager) getSystemService(ALARM_SERVICE);
//                    int minuteInt =Integer.parseInt(minuteEditText.getText().toString());
//                    //alarm set
//                    alarm.set(AlarmManager.RTC_WAKEUP,(startTimeMilliSecond+(minuteInt*60000)),alarmIntent);
//
//
//
//                    Toast.makeText(MainActivity.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
//                    loadData();
//                    bottomSheetDialog.dismiss();
//                }else {
//                    Toast.makeText(MainActivity.this, "insert fail", Toast.LENGTH_SHORT).show();
//                    bottomSheetDialog.dismiss();
//                }



            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
}