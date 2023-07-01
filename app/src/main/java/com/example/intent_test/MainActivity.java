package com.example.intent_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.Explicit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplication(), SecondActivity.class);
                intent.putExtra("Key","MainActivity call this activity");
                Log.d("Explicit", getApplication().toString());
                Log.d("Explicit", getApplication().getClass().toString());
//                Log.d("Explicit", getApplication().toString());
                startActivity(intent);
            }
        });

        findViewById(R.id.Implicit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 일반적인 implicit intent 호출방법
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));

                Log.d("Implicit", Intent.ACTION_VIEW);
                Log.d("Implicit", Uri.parse("http://www.google.com/").toString());

                startActivity(intent);
            }
        });


    }
}