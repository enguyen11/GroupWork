package com.example.groupwork;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;


public class App extends Application {
    public static final String CHANNEL_ID = "chat_channel";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // checks if api is running at least oreo which is when channels were introduced
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Chat Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Channel for chat notifications");


            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }
}
