package com.example.intent_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_TIMEZONE_CHANGED.equals(intent.getAction())) {
            Toast.makeText(context, "ACTION_TIMEZONE_CHANGED intent received", Toast.LENGTH_SHORT).show();
            Log.d("BroadcastReceiver", "Intend received : " + intent.getAction());
        } else if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Toast.makeText(context, "ACTION_BOOT_COMPLETED intent received", Toast.LENGTH_SHORT).show();
            Log.d("BroadcastReceiver", "Intend received : " + intent.getAction());
        } else {
            Toast.makeText(context, "Strange intent received. Have to check again", Toast.LENGTH_SHORT).show();
            Log.d("BroadcastReceiver", "Intend received : " + intent.getAction());
        }
        Intent intents = new Intent(context, SecondActivity.class);
        intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intents);
    }
}