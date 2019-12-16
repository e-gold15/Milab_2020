package com.example.nbaquotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final long INTERVAL_FIVE_MINUTES = (AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // IntentService1.startActionFoo(MainActivity.this);

       findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                IntentService1.startActionFoo(MainActivity.this);


            }
       });

    }
}
