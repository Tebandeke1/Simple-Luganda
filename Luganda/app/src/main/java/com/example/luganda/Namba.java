package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Namba extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namba);

        final ArrayList<Word> numbers = new ArrayList<>();

        numbers.add(new Word("Emu","One"));
        numbers.add(new Word("Biri","Two"));
        numbers.add(new Word("Satu","Three"));
        numbers.add(new Word("Inya","Four"));
        numbers.add(new Word("Taano","Five"));
        numbers.add(new Word("Mukaaga","Six"));
        numbers.add(new Word("Musaanvu","Seven"));
        numbers.add(new Word("Munaana","Eight"));
        numbers.add(new Word("Mwenda","Nine"));
        numbers.add(new Word("Kuumi","Ten"));


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
