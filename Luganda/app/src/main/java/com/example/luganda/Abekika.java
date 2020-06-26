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

        words.add(new Word("Taata","Father"));
        words.add(new Word("Maama","Mother"));
        words.add(new Word("Mutabani","Son"));
        words.add(new Word("Muwala","Daughter"));
        words.add(new Word("Mukulu wange Omulenzi","Older brother"));
        words.add(new Word("Muto wange omulenzi","Younger brother"));
        words.add(new Word("Mukuku wange omuwala ","Older sister"));
        words.add(new Word("Muto wange omuwala","Younger sister"));
        words.add(new Word("Jaaja omukyala","Grandmother"));
        words.add(new Word("Jaaja omusajja ","Grandfather"));

        WordAdapter wordAdapter = new WordAdapter(this,words);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }


}
