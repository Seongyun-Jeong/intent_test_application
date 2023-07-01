package com.example.intent_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;

public class SecondActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;
    private TelephonyManager telephonyManager;

    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver();
    }

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        registerReceiver();

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_second);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        requestPermission();

        TextView textView = (TextView) findViewById(R.id.textView);
        Intent intent = getIntent();
        textView.setText("Received Data : " + intent.getStringExtra("Key"));

    findViewById(R.id.clickme).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mReceiver == null) {
                textView.setText("Intent filter doesn't set yet");
            } else {
                textView.setText("Intent filter setting completed : " + mReceiver);
            }
        }
    });

    }



    private void registerReceiver() {
        Log.d("Receiver","register Receiver");
        if(mReceiver != null) {
            return;
        }
         final IntentFilter Filter = new IntentFilter();
        Filter.addAction(Intent.ACTION_DOCK_EVENT);

        this.mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Intent.ACTION_DOCK_EVENT)) {
                    Toast.makeText(context, "Dynamic intent received : " + Intent.ACTION_DOCK_EVENT, Toast.LENGTH_SHORT).show();
                    Log.d("Intent", "Dynamic intent received : " + Intent.ACTION_DOCK_EVENT);
                    getPhoneNumber();
                }else {
                    Toast.makeText(context, "Error : Dynamic intent received : " + intent.getAction(), Toast.LENGTH_SHORT).show();
                    Log.d("Intent", "Error : Dynamic intent received : " + intent.getAction());
                }
            }
        };
        this.registerReceiver(this.mReceiver,Filter);
    }

    private void unregisterReceiver() {
        if (mReceiver != null) {
            Log.d("Receiver","Unregister Receiver");
            this.unregisterReceiver(mReceiver);
            mReceiver = null;
        }
    }


    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS}, PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            // Permission already granted
//            getPhoneNumber();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
//                getPhoneNumber();
            }
        }
    }

    private void getPhoneNumber() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String phoneNumber = telephonyManager.getLine1Number();
        Log.d("Phone Number", "Phone Number: " + phoneNumber);
    }
}
