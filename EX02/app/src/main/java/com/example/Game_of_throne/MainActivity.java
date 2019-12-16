package com.example.Game_of_throne;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.content.Intent;

import com.example.ex02.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ImageButton starkButton;
    ImageButton lannisterButton;

    MediaPlayer mySong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySong = MediaPlayer.create(this, R.raw.got);
        mySong.start();

        ImageButton starkButton = (ImageButton)findViewById(R.id.stark);
        starkButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), Stark1.class);
                mySong.stop();
                startActivity(intent);
            }
        });

        ImageButton lannisterButton = (ImageButton)findViewById(R.id.lannister);
        lannisterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(v.getContext(), Lannister.class);
                mySong.stop();
                startActivity(intent1);
            }
    });
}


}
