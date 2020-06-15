package com.example.alarmmanager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity   {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tvShowTime);

    }
    public void btnTimePicker(View v){

        Calendar c = Calendar.getInstance(); // lấy cái thời gian hiện tại
        c.get(Calendar.HOUR);
        int hourd=c.get(Calendar.HOUR), m=c.get(Calendar.MINUTE);
       TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
           @RequiresApi(api = Build.VERSION_CODES.KITKAT)
           @Override
           public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
               Calendar c = Calendar.getInstance(); // lấy cái thời gian hiện tại
               c.set(Calendar.HOUR, hourOfDay);
               c.set(Calendar.MINUTE,minute);
               c.set(Calendar.SECOND,0);
showTime(c);
               startAlarm(c);
           }
       }, hourd , m , true);
       timePicker.show();

    }
    public void btnCancel(View v){

    }
    private  void showTime(Calendar c){
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");


        tv.setText("Alarm set for: "+   simpleDateFormat.format(c.getTime()));

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);// lấy dịch vụ hệ thống
        Intent intent =new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
//        if (c.before(Calendar.getInstance())) {
//            c.add(Calendar.DATE, 1);
//        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent); //Exact độ chính xác

    }
}
