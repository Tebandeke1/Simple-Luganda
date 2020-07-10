package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Namba extends AppCompatActivity {

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

        final ArrayList<Word> numbers = new ArrayList<>();

        numbers.add(new Word("Emu","One",R.drawable.number_one,R.raw.emu));
        numbers.add(new Word("Biri","Two",R.drawable.number_two,R.raw.biiri));
        numbers.add(new Word("Satu","Three",R.drawable.number_three,R.raw.saatu));
        numbers.add(new Word("Inya","Four",R.drawable.number_four,R.raw.inya));
        numbers.add(new Word("Taano","Five",R.drawable.number_five,R.raw.taano));
        numbers.add(new Word("Mukaaga","Six",R.drawable.number_six,R.raw.mulaaga));
        numbers.add(new Word("Musaanvu","Seven",R.drawable.number_seven,R.raw.musanvu));
        numbers.add(new Word("Munaana","Eight",R.drawable.number_eight,R.raw.munaana));
        numbers.add(new Word("Mwenda","Nine",R.drawable.number_nine,R.raw.mwenda));
        numbers.add(new Word("Kuumi","Ten",R.drawable.number_ten,R.raw.kuumi));


       WordAdapter wordAdapter = new WordAdapter(this,numbers,R.color.category_numbers);

        ListView view = findViewById(R.id.list);

        view.setAdapter(wordAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlay();
                Word word = numbers.get(i);

                mediaPlayer = MediaPlayer.create(Namba.this,word.getAudioResource());
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

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlay();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        releaseMediaPlay();
    }
}
