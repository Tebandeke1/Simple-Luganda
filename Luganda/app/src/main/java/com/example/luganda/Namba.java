package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Namba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);

        final ArrayList<Word> numbers = new ArrayList<>();

        numbers.add(new Word("Emu","One",R.drawable.number_one));
        numbers.add(new Word("Biri","Two",R.drawable.number_two));
        numbers.add(new Word("Satu","Three",R.drawable.number_three));
        numbers.add(new Word("Inya","Four",R.drawable.number_four));
        numbers.add(new Word("Taano","Five",R.drawable.number_five));
        numbers.add(new Word("Mukaaga","Six",R.drawable.number_six));
        numbers.add(new Word("Musaanvu","Seven",R.drawable.number_seven));
        numbers.add(new Word("Munaana","Eight",R.drawable.number_eight));
        numbers.add(new Word("Mwenda","Nine",R.drawable.number_nine));
        numbers.add(new Word("Kuumi","Ten",R.drawable.number_ten));


       WordAdapter wordAdapter = new WordAdapter(this,numbers);

        ListView view = findViewById(R.id.list);

        view.setAdapter(wordAdapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Namba.this, numbers.get(i).getLugandaWord()+" Selected..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
