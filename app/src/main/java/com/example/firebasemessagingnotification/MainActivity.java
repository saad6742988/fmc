package com.example.firebasemessagingnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isSuccessful())
                {
                    Log.d("device tokken", "onCreate: "+task.getResult());
                }
            }
        });





        NotificationChannel channel= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("My Notification1","My Notification1", NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager manager =getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }
//        String message="Hello Programming Digest";
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"My Notification");
//        builder.setContentTitle("NotificationTitle");
//        builder.setContentText(message);
//        builder.setSmallIcon(R.drawable.ic_outline_adb_24);
//        builder.setAutoCancel(true);
//        Intent intent = new Intent(MainActivity.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("message",message);
//        PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_IMMUTABLE);
//        builder.setContentIntent(pendingIntent);
//
//
//        NotificationManagerCompat managerCompat=NotificationManagerCompat.from(MainActivity.this);
//        managerCompat.notify(1,builder.build());
    }
}