package com.example.Game_of_throne;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.media.MediaPlayer;

import com.example.ex02.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.ex02.R.layout.lanister;
import static com.example.ex02.R.layout.stark;

public class Lannister extends AppCompatActivity {

    MediaPlayer mySongL;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(lanister);
        mySongL = MediaPlayer.create(this, R.raw.lannister);
        mySongL.start();


        List<Data> data = new ArrayList<>();

        data.add(new Data( R.drawable.twyinl, "Twyinl Lannister"));
        data.add(new Data( R.drawable.cerseil, "Cersei Lannister"));
        data.add(new Data( R.drawable.jaimel, "Jaime Lannister"));
        data.add(new Data( R.drawable.tyrionl, "Tyrion Lannister"));
        data.add(new Data( R.drawable.joffreyl, "Joffrey Baratheon"));
        data.add(new Data( R.drawable.myrcellal, "Myrcella Baratheon"));
        data.add(new Data( R.drawable.tommenl, "Tomen Baratheon"));
        data.add(new Data( R.drawable.kevanl, "Kevan Lannister"));
        data.add(new Data( R.drawable.lancell, "Lancel Lannister"));
        data.add(new Data( R.drawable.martynl, "Martyn Lannister"));
        data.add(new Data( R.drawable.willeml, "Willem Lannister"));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView_lannister);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter1(Lannister.this, data));

    }
}

