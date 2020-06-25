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

        final ArrayList<String> numbers = new ArrayList<>();

        numbers.add("One");
        numbers.add("Two");
        numbers.add("Three");
        numbers.add("Four");
        numbers.add("Five");
        numbers.add("Six");
        numbers.add("Seven");
        numbers.add("Eight");
        numbers.add("Nine");
        numbers.add("Ten");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item,numbers);

        ListView view = findViewById(R.id.list);

        view.setAdapter(adapter);

        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Namba.this, numbers.get(i)+" Selected..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
