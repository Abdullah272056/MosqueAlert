package com.example.mosquealert;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
EditText waktoNameEditText,minuteEditText;
Button cancelButton ,saveButton;


    Context context;
    private List<Notes> allNotes;
    List<Notes> copyAllNotes;
    private DataBaseHelper databaseHelper;
    int switchButtonStatus;

    CustomAdapter.OnContactClickListener onContactClickListener;
    public CustomAdapter(Context context, List<Notes> allNotes,CustomAdapter.OnContactClickListener onContactClickListener){
        this.context = context;
        this.allNotes = allNotes;
        this.context=context;
        databaseHelper=new DataBaseHelper(context);
        copyAllNotes = new ArrayList<>(allNotes);

        this.onContactClickListener = onContactClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view= layoutInflater.inflate(R.layout.recyclerview_item_design,parent,false);
        return new MyViewHolder(view,onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.startTextView.setText(allNotes.get(position).getStartTime());
        holder.endTextView.setText(allNotes.get(position).getEntTime());
        holder.nameTextView.setText(allNotes.get(position).getWaktoName());
      //  holder.nameTextView.setText(allNotes.get(position).get());
        switchButtonStatus=allNotes.get(position).getSwitchStatus();

        if (switchButtonStatus==1){
            holder.switchButton.setChecked(true);
        }
        else if (switchButtonStatus==0){
            holder.switchButton.setChecked(false);
        }else {

        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


//        holder.switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    currentMilliSecond=System.currentTimeMillis();
//
//                    Toast.makeText(context, "check", Toast.LENGTH_SHORT).show();
//
//                    long status = databaseHelper.updateData(new Notes(allNotes.get(position).getId(),
//                            allNotes.get(position).getStartTime(),allNotes.get(position).getEntTime(),
//                            allNotes.get(position).getStartTimeMilli(),
//                            allNotes.get(position).getEndTimeMilli(),
//                            allNotes.get(position).getAudioMode(),
//                            1));
//
//                    if (status == 1){
//                        allNotes.clear();
//                        allNotes.addAll(databaseHelper.getAllNotes());
//                        //notifyDataSetChanged();
//
//                        if (currentMilliSecond<=Long.parseLong(allNotes.get(position).getStartTimeMilli())){
//                            Intent intent=new Intent(context, NotificationReceiver.class);
//                            intent.putExtra("notificationRequestCode",allNotes.get(position).getId());
//                            intent.putExtra("TargetTimeMilliSecond",allNotes.get(position).getStartTimeMilli());
//                            alarmIntent= PendingIntent.getBroadcast(context,
//                                    allNotes.get(position).getId(),intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                            //get ALARM_SERVICE from SystemService
//                            alarm= (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                            //alarm set
//                            alarm.set(AlarmManager.RTC_WAKEUP,Long.parseLong(allNotes.get(position).getStartTimeMilli()),alarmIntent);
//
//
//                            alarmIntent= PendingIntent.getBroadcast(context,
//                                    allNotes.get(position).getId()+100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                            //get ALARM_SERVICE from SystemService
//                            alarm= (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                            int minuteInt =Integer.parseInt(allNotes.get(position).getEntTime());
//                            //alarm set
//                            alarm.set(AlarmManager.RTC_WAKEUP,(Long.parseLong(allNotes.get(position).getStartTimeMilli())+(minuteInt*60000)),alarmIntent);
//                        }else {
//                            Toast.makeText(context, "Current Time is bigger than target time", Toast.LENGTH_SHORT).show();
//                        }
//                        Toast.makeText(context, String.valueOf(allNotes.get(position).getId()), Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//                    long status = databaseHelper.updateData(new Notes(allNotes.get(position).getId(),
//                            allNotes.get(position).getStartTime(),allNotes.get(position).getEntTime(),
//                            allNotes.get(position).getStartTimeMilli(),
//                            allNotes.get(position).getEndTimeMilli(),
//                            allNotes.get(position).getAudioMode(),
//                            0));
//                    if (status == 1){
//                        allNotes.clear();
//                        allNotes.addAll(databaseHelper.getAllNotes());
//                        //notifyDataSetChanged();
//
//                      Intent intent=new Intent(context, NotificationReceiver.class);
//                        alarmIntent= PendingIntent.getBroadcast(context,
//                                allNotes.get(position).getId(),intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                        alarm= (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                     alarm.cancel(alarmIntent);
//
//                        alarmIntent= PendingIntent.getBroadcast(context,
//                                allNotes.get(position).getId()+100,intent,PendingIntent.FLAG_CANCEL_CURRENT);
//                        //get ALARM_SERVICE from SystemService
//                        alarm= (AlarmManager) context.getSystemService(ALARM_SERVICE);
//                        alarm.cancel(alarmIntent);
//
//                        //  Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
//                    }
//                    Toast.makeText(context, String.valueOf(allNotes.get(position).getId()), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Switch switchButton;
        TextView nameTextView,startTextView,endTextView;
        CustomAdapter.OnContactClickListener onContactClickListener;
        public MyViewHolder(@NonNull View itemView, CustomAdapter.OnContactClickListener onContactClickListener) {
            super(itemView);
            switchButton=itemView.findViewById(R.id.switchId);
            nameTextView=itemView.findViewById(R.id.waktoNameTextViewId);
            startTextView=itemView.findViewById(R.id.startTimeTextViewId);
            endTextView=itemView.findViewById(R.id.endTimeTextViewId);
            this.onContactClickListener=onContactClickListener;
            itemView.setOnClickListener(this);
            switchButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }



    public  interface  OnContactClickListener{
        void onContactClick(int position);
    }


//    private void CustomDialog(){
//
//        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context);
//        bottomSheetDialog.setContentView(R.layout.input_layout);
//        bottomSheetDialog.setCanceledOnTouchOutside(false);
//
//        saveButton=bottomSheetDialog.findViewById(R.id.saveButtonId);
//        cancelButton=bottomSheetDialog.findViewById(R.id.cancelButtonId);
//        minuteEditText=bottomSheetDialog.findViewById(R.id.minuteEditTextId);
//        waktoNameEditText=bottomSheetDialog.findViewById(R.id.waktoNameEditTextId);
//        final TimePicker startTimePicker =bottomSheetDialog.findViewById(R.id.timePickerId);
//
//        saveButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                String waktoName=waktoNameEditText.getText().toString();
//                String endMinute=minuteEditText.getText().toString();
//                if (TextUtils.isEmpty(waktoName)){
//                    waktoNameEditText.setError("Enter wakto name");
//                    waktoNameEditText.requestFocus();
//                    return;
//                }
//                if (TextUtils.isEmpty(endMinute)){
//                    minuteEditText.setError("Enter minute name");
//                    minuteEditText.requestFocus();
//                    return;
//                }
//
//
//                DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
//                int hour=startTimePicker.getCurrentHour();
//                int minute=startTimePicker.getCurrentMinute();
//                Calendar startTimeCalender=Calendar.getInstance();
//                startTimeCalender.set(Calendar.HOUR_OF_DAY,hour);
//                startTimeCalender.set(Calendar.MINUTE,minute);
//                startTimeCalender.set(Calendar.SECOND,0);
//                String startTimeString = dateFormat.format(startTimeCalender.getTime()).toString();
//
//                startTimeMilliSecond=startTimeCalender.getTimeInMillis();
//
//                int addMinute=Integer.parseInt(endMinute);
//                endTimeMilliSecond=startTimeMilliSecond+(addMinute*60000);
//
//                int id=dataBaseHelper.insertData(new Notes(startTimeString,endTime,String.valueOf(startTimeMilliSecond),
//                        String.valueOf(endTimeMilliSecond),"www",1));
//                if (id!=-1){
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
//
//
//
//            }
//        });
//
//        cancelButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                bottomSheetDialog.dismiss();
//            }
//        });
//        bottomSheetDialog.show();
//    }
}
