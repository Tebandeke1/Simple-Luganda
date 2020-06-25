package com.example.luganda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textNumbers;
    private TextView textColors;
    private TextView textFamily;
    private TextView textWords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textColors = findViewById(R.id.colors);
        textFamily = findViewById(R.id.family);
        textNumbers = findViewById(R.id.numbers);
        textWords = findViewById(R.id.phrases);

        colorsListener();
        familyListener();
        numbersListener();
        wordsListener();
    }

    private void wordsListener() {
        textWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Ebigambo.class);
                startActivity(intent);
            }
        });
    }

    private void numbersListener() {
        textNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Namba.class);
                startActivity(intent);
            }
        });
    }

    private void familyListener() {
        textFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Abekika.class);
                startActivity(intent);
            }
        });
    }

    private void colorsListener() {
        textColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Langi.class);
                startActivity(intent);
            }
        });
    }
}
