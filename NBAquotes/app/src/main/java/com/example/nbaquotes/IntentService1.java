package com.example.nbaquotes;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class IntentService1 extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_Notification = "com.example.nbaquotes.action.FOO";


        public IntentService1() {
        super("IntentService1");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, IntentService1.class);
        intent.setAction(ACTION_Notification);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.d("Intent Service", "intent service");
            if (ACTION_Notification.equals(action)) {
                handleActionFoo();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo() {
        // TODO: Handle action Foo

        Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),MyReceiver.REQUEST_CODE,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(), (AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3), pendingIntent);
    }

}
