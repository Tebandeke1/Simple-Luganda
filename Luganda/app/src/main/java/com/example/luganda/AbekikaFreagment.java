package com.example.luganda;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AbekikaFreagment extends Fragment {


    //handles media player for all audio files ;
    private MediaPlayer mediaPlayer;

    //Handles audio focus when playing sound file
    private AudioManager audioManager;

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

    public AbekikaFreagment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_words,container,false);

        //create a setup to request audio focus
        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        WordAdapter wordAdapter = new WordAdapter(getActivity(),words,R.color.category_family);

        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);

                //release media if it is currently playing because we want to play a different file
                releaseMediaPlay();



                int result = audioManager.requestAudioFocus(focusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ) {

                    // We have audio focus now.

                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    mediaPlayer = MediaPlayer.create(getActivity(),word.getAudioResource());

                    // Start the audio file
                    mediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    //then here we call the on complete listener object
                    mediaPlayer.setOnCompletionListener(onCompletionListener);

                }
            }
        });
        return rootView;
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
    public void onStop() {
        super.onStop();
        releaseMediaPlay();
    }
}
