package com.example.nbaquotes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import static android.content.Context.NOTIFICATION_SERVICE;


public class MyReceiver extends BroadcastReceiver {
    public static final int REQUEST_CODE = 111;
    private int mNotificationId = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        createNotification(context);

        Intent intentNew = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intentNew, 0);

        String currentQuote = new BIBIQuote().mQuote;
        //here

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources() ,R.drawable.bibi);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "NotifyChannel")
                .setSmallIcon(R.drawable.bibi)
                .setLargeIcon(largeIcon)
                .setContentIntent(pendingIntent)
                .setContentText(currentQuote)
                .setContentTitle("רק ביבי?")
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(largeIcon))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(currentQuote))
               .setPriority(NotificationCompat.PRIORITY_DEFAULT)
               .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationId++, builder.build());
    }

    private void createNotification(Context context) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationManager notificationManager = (NotificationManager)context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel("NotifyChannel","NotifyChannel",NotificationManager.IMPORTANCE_DEFAULT));
        }
    }
}
