package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Abekika extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Taata","Father",R.drawable.family_father));
        words.add(new Word("Maama","Mother",R.drawable.family_mother));
        words.add(new Word("Mutabani","Son",R.drawable.family_son));
        words.add(new Word("Muwala","Daughter",R.drawable.family_daughter));
        words.add(new Word("Mukulu wange Omulenzi","Older brother",R.drawable.family_older_brother));
        words.add(new Word("Muto wange omulenzi","Younger brother",R.drawable.family_younger_brother));
        words.add(new Word("Mukuku wange omuwala ","Older sister",R.drawable.family_older_sister));
        words.add(new Word("Muto wange omuwala","Younger sister",R.drawable.family_younger_sister));
        words.add(new Word("Jaaja omukyala","Grandmother",R.drawable.family_grandmother));
        words.add(new Word("Jaaja omusajja ","Grandfather",R.drawable.family_grandfather));

        WordAdapter wordAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }


}
