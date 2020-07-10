package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Ebigambo extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    //This player gets triggered when the song is done being played so

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //after playing again we make the media player null for other songs to be played
            releaseMediaPlay();
        }
    };


    private  AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focus) {
            if (focus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focus == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                //AUDIOFOCUS_LOSS_TRANSIENT means we have lost the  audio focus for a short period of time
                //AUDIO_LOSS_TRANSIENT_CAN_DUCK means we can reduce the audio volume for a short period of time
                //we can treat these states in the same way because almost they are the same

                //pause play back and then after play the song from the begining because these are are short audio files
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }else if (focus == AudioManager.AUDIOFOCUS_GAIN){
                //this AudioManager.AUDIOFOCUS_GAIN means we can gain the audio manager hence playing the audio again
                mediaPlayer.start();
            }else if (focus == AudioManager.AUDIOFOCUS_LOSS){
                //  this AUDIOFOCUS_LOSS means we have lost the song completely so we just release the media
                //first stop play and release the resources
                releaseMediaPlay();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

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



                int result = audioManager.requestAudioFocus(focusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ) {

                    mediaPlayer = MediaPlayer.create(Ebigambo.this, word.getAudioResource());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(onCompletionListener);

                }

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

            audioManager.abandonAudioFocus(focusChangeListener);
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
}
