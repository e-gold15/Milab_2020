package com.example.Game_of_throne;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import com.example.ex02.R;

import static com.example.ex02.R.layout.lanister;
import static com.example.ex02.R.layout.stark;

public class Stark1 extends AppCompatActivity {

    MediaPlayer mySongS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(stark);
        mySongS = MediaPlayer.create(this, R.raw.stark);
        mySongS.start();

        List<Data> data = new ArrayList<>();

        data.add(new Data( R.drawable.rickards, "Rickard Stark"));
        data.add(new Data( R.drawable.brandons, "Brandon Stark"));
        data.add(new Data( R.drawable.ned, "Eddard Stark"));
        data.add(new Data( R.drawable.benjens, "Benjen Stark"));
        data.add(new Data( R.drawable.lyannas, "Lyanna Stark"));
        data.add(new Data( R.drawable.catylns, "Catelyn Stark"));
        data.add(new Data( R.drawable.robs, "Robb Stark"));
        data.add(new Data( R.drawable.sansas, "Sansa Stark"));
        data.add(new Data( R.drawable.aray, "Aray Stark"));
        data.add(new Data( R.drawable.bran, "Bran Stark"));
        data.add(new Data( R.drawable.rickon, "Rickon Stark"));
        data.add(new Data( R.drawable.jon, "Jon Snow"));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView_stark);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(Stark1.this, data));

    }
}
