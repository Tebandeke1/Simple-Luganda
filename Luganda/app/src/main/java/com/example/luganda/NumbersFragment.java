package com.example.luganda;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
public class NumbersFragment extends Fragment {


    private MediaPlayer mediaPlayer;
    //This player gets triggered when the song is done being played so

    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            //after playing again we make the media player null for other songs to be played
            releaseMediaPlay();
        }
    };

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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_words,container,false);

        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

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


        WordAdapter wordAdapter = new WordAdapter(getActivity(),numbers,R.color.category_numbers);

        ListView view = rootView.findViewById(R.id.list);

        view.setAdapter(wordAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlay();
                Word word = numbers.get(i);

                int result = audioManager.requestAudioFocus(focusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResource());
                    mediaPlayer.start();

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

            audioManager.abandonAudioFocus(focusChangeListener);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
        //when the activity is stopped ,release media playing because we won't be playing
        //media any more
        releaseMediaPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //the same when the activity is destroyed release media
        releaseMediaPlay();
    }
}
