package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Ebigambo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Olaga wa?","Where are you going?"));
        words.add(new Word("Erinya lyo gwe ani?","What is your name?"));
        words.add(new Word("Eriny alyange nze...","My name is...."));
        words.add(new Word("Owulira otya?","How are you feeling?"));
        words.add(new Word("Ogenda kujja?","Are you coming?"));
        words.add(new Word("Ye ngenda kujja.","Yes am coming."));
        words.add(new Word("Ojja sawa meka?","What time are you coming?"));
        words.add(new Word("Tugende","Let's go."));
        words.add(new Word("Jangu wano","Come here"));
        words.add(new Word("Wasiibye otya?","How was your day?"));
        words.add(new Word("Obera wa?","Where do you live?"));

        WordAdapter wordAdapter = new WordAdapter(this,words,R.color.category_words);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
    }
}
