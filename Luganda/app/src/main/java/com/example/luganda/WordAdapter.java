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
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorChange;
    public WordAdapter(Activity activity, ArrayList<Word> words,int colorChange){
        super(activity,0,words);
        this.colorChange = colorChange;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view =  convertView;

        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word word = getItem(position);

        //displaying images in the adapter
        ImageView imageView = view.findViewById(R.id.image);

        if (word.hasImage()){
            // we have images to display .... we use this
            imageView.setImageResource(word.getImageResource());
            //we even set the image view visible
            imageView.setVisibility(View.VISIBLE);
        }else{
            //in case we don't have images to display to the user through the activity
            // we just set the image view to gone
            imageView.setVisibility(View.GONE);
        }


        TextView lugandaText = view.findViewById(R.id.luganda_text_view);

        lugandaText.setText(word.getLugandaWord());

        TextView  defaultView = view.findViewById(R.id.default_text_view);
        defaultView.setText(word.getDefaultWord());

        View tetxContainer = view.findViewById(R.id.textContainer);
        int color = ContextCompat.getColor(getContext(),colorChange);
        tetxContainer.setBackgroundColor(color);

        return view;
    }
}
