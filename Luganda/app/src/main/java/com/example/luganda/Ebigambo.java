package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Ebigambo extends AppCompatActivity {

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
        words.add(new Word("Olaga wa?","Where are you going?",R.raw.word1));
        words.add(new Word("Erinya lyo gwe ani?","What is your name?",R.raw.word2));
        words.add(new Word("Eriny alyange nze...","My name is....",R.raw.word3));
        words.add(new Word("Owulira otya?","How are you feeling?",R.raw.word4));
        words.add(new Word("Ogenda kujja?","Are you coming?",R.raw.word5));
        words.add(new Word("Ye ngenda kujja.","Yes am coming.",R.raw.word6));
        words.add(new Word("Ojja sawa meka?","What time are you coming?",R.raw.word7));
        words.add(new Word("Tugende","Let's go.",R.raw.word8));
        words.add(new Word("Jangu wano","Come here.",R.raw.word9));
        words.add(new Word("Wasiibye otya?","How was your day?",R.raw.word10));
        words.add(new Word("Obera wa?","Where do you live?",R.raw.word11));

        WordAdapter wordAdapter = new WordAdapter(this,words,R.color.category_words);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlay();
                Word word = words.get(i);

                mediaPlayer = MediaPlayer.create(Ebigambo.this,word.getAudioResource());
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
