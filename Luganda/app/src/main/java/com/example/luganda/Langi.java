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
        words.add(new Word("Myufu","Red",R.drawable.color_red));
        words.add(new Word("Kiragala","Green",R.drawable.color_green));
        words.add(new Word("Kitaka","Brown",R.drawable.color_brown));
        words.add(new Word("Kancungwa","Orange",R.drawable.color_dusty_yellow));
        words.add(new Word("Kidugavu","Black",R.drawable.color_black));
        words.add(new Word("Kyeru","White",R.drawable.color_white));
        words.add(new Word("Kyenvu","Yellow",R.drawable.color_mustard_yellow));
        words.add(new Word("Kaki","Grey",R.drawable.color_gray));

        WordAdapter wordAdapter = new WordAdapter(this, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);


    }
}
