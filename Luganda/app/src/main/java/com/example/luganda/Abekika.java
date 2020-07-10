package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Abekika extends AppCompatActivity {

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

        words.add(new Word("Taata","Father",R.drawable.family_father,R.raw.taata));
        words.add(new Word("Maama","Mother",R.drawable.family_mother,R.raw.mother));
        words.add(new Word("Mutabani","Son",R.drawable.family_son,R.raw.mutabani));
        words.add(new Word("Muwala","Daughter",R.drawable.family_daughter,R.raw.muwala));
        words.add(new Word("Mukulu wange Omulenzi","Older brother",R.drawable.family_older_brother,R.raw.olderbrother));
        words.add(new Word("Muto wange omulenzi","Younger brother",R.drawable.family_younger_brother,R.raw.youngbrother));
        words.add(new Word("Mukuku wange omuwala ","Older sister",R.drawable.family_older_sister,R.raw.oldersister));
        words.add(new Word("Muto wange omuwala","Younger sister",R.drawable.family_younger_sister,R.raw.youngsister));
        words.add(new Word("Jaaja omukyala","Grandmother",R.drawable.family_grandmother,R.raw.grandmother));
        words.add(new Word("Jaaja omusajja ","Grandfather",R.drawable.family_grandfather,R.raw.grandfather));
        WordAdapter wordAdapter = new WordAdapter(this,words,R.color.category_family);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);

                releaseMediaPlay();

                mediaPlayer = MediaPlayer.create(getApplicationContext(),word.getAudioResource());
                mediaPlayer.start();

                //then here we call the on complete listener object
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
    protected void onRestart() {
        super.onRestart();
        releaseMediaPlay();
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
