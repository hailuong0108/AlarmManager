package com.example.alarmmanager;

import android.app.Notification;
import android.content.Context;
import android.content.ContextWrapper;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {// bao bọc bối cảnh
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {// nếu mà phiên bản đt mà lớn hơn hoặc bằng phiên bản code
            // ví dụ đt phiên bản là android 8 mà code thì có 4
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
        channel.enableLights(true);// bật đèn
        channel.enableVibration(true);// rung
        channel.setLightColor(R.color.colorPrimaryDark);// màu tối
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);// chế màn hình khóa
        //  getSystemService(Context.NOTIFICATION_SERVICE).createNotificationChannel(channel);
        // tao kenh thong bao
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification() {// trình tạo thông báo
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Alarm!")
                .setContentText("Your AlarmManager is working.")
                .setSmallIcon(R.drawable.notifi);
    }
}