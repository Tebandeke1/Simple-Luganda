package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Langi extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    //This player gets triggered when the song is done being played so

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //after playing again we make the media player null for other songs to be played
            releaseMediaPlay();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Myufu","Red",R.drawable.color_red,R.raw.red));
        words.add(new Word("Kiragala","Green",R.drawable.color_green,R.raw.green));
        words.add(new Word("Kitaka","Brown",R.drawable.color_brown,R.raw.brown));
        words.add(new Word("Kancungwa","Orange",R.drawable.color_dusty_yellow,R.raw.orange));
        words.add(new Word("Kidugavu","Black",R.drawable.color_black,R.raw.black));
        words.add(new Word("Kyeru","White",R.drawable.color_white,R.raw.white));
        words.add(new Word("Kyenvu","Yellow",R.drawable.color_mustard_yellow,R.raw.kyenvu));
        words.add(new Word("Kaki","Grey",R.drawable.color_gray,R.raw.grey));

        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_colors);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlay();
                Word word = words.get(i);

                mediaPlayer = MediaPlayer.create(Langi.this,word.getAudioResource());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }

    //this method is to help and release a song when another song is being played at the same time as to save resources
    public void releaseMediaPlay(){
        if (mediaPlayer != null){
            //release the song so that another player gets a chance to play
            //this is done because we nolonger need  resources of the current song
            mediaPlayer.release();


            // we after set the media player back to null telling the song to be played that the media is null
            mediaPlayer = null;
        }
    }
}
