package com.example.exponetimeworkmanager;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWork extends Worker
{    public MyWork(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {
        String data1 =getInputData().getString("aa");
        //int data2=getInputData().getInt("bb",0);
        createNotification(data1,"Bonjour ceci est une notif");
        //return Result.success();
        return Result.retry();
    }
    void createNotification(String title,String description) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
       NotificationChannel notificationChannel = new NotificationChannel ("101", "channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
     Notification.Builder notificationBuilder =
                new Notification.Builder(
                        getApplicationContext(), "101")
                        .setContentTitle(title)
                        .setContentText(description)
                        .setSmallIcon(R.drawable.ic_launcher_background);
        notificationManager.notify(1, notificationBuilder.build());
    }
}
