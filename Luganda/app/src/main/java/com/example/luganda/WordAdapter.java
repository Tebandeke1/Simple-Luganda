package com.example.luganda;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity activity, ArrayList<Word> words){
        super(activity,0,words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view =  convertView;

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word word = getItem(position);

        ImageView imageView = view.findViewById(R.id.image);

        imageView.setImageResource(word.getImageResource());

        TextView lugandaText = view.findViewById(R.id.luganda_text_view);

        lugandaText.setText(word.getLugandaWord());

        TextView  defaultView = view.findViewById(R.id.default_text_view);
        defaultView.setText(word.getDefaultWord());

        return view;
    }
}
