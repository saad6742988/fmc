package com.example.firebasemessagingnotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    //hello
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d("token", "onNewToken: "+token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        String myCustomData = message.getData().get("activity_name");
        Log.d("notification", "onMessageReceived: ");
        NotificationChannel channel= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("channel check", message.getNotification().getChannelId());
            channel = new NotificationChannel(message.getNotification().getChannelId(),message.getNotification().getChannelId(), NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager manager =getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
//        String message="Hello Programming Digest";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplication(),message.getNotification().getChannelId());
        builder.setContentTitle(message.getNotification().getTitle());
        builder.setContentText(message.getNotification().getBody());
        builder.setSmallIcon(R.drawable.logo);
        builder.setAutoCancel(true);
        Intent intent = null;
        try {
            Log.d("tryinggggggggggg", "onMessageReceived: ");
            intent = new Intent(getApplication(), Class.forName(getPackageName()+"."+message.getData().get("activity_name")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",message);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplication(),0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);


        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(getApplication());
        managerCompat.notify(1,builder.build());

    }
}