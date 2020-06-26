package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Langi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Myufu","Red"));
        words.add(new Word("Kiragala","Green"));
        words.add(new Word("Kitaka","Brown"));
        words.add(new Word("Kancungwa","Orange"));
        words.add(new Word("Kidugavu","Black"));
        words.add(new Word("Kyeru","White"));
        words.add(new Word("Bulu","Blue"));
        words.add(new Word("Kaki","Grey"));

        WordAdapter wordAdapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);


    }
}
