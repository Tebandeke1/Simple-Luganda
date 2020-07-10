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
import java.util.List;

public class Langi extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    //this is going to handle audio focus when playing audio files

    private  AudioManager audioManager;
    //This player gets triggered when the song is done being played so

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //after playing again we make the media player null for other songs to be played
            releaseMediaPlay();
        }
    };

    //This method helps mostly when we want to pause the audio playing so as to recieve a phone call
    private AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
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

        //getting the audio context
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

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

                int result = audioManager.requestAudioFocus(focusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mediaPlayer = MediaPlayer.create(Langi.this, word.getAudioResource());
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

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
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
