package com.example.mosquealert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
           //switchButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition(),switchButton);
        }
    }



    public  interface  OnContactClickListener{
        void onContactClick(int position, Switch switchButton);

        ;
    }



}
