package com.example.luganda;

public class Word {
    private String lugandaWord;
    private String defaultWord;
    private int imageResource = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED =-1;

    //this constructor is for activities which require images
    public Word(String lugandaWord, String defaultWord,int imageResource) {
        this.lugandaWord = lugandaWord;
        this.defaultWord = defaultWord;
        this.imageResource = imageResource;
    }

    //this constructor is for activities which do not require images..like Ebigambo activity
    public Word(String lugandaWord,String defaultWord){
        this.lugandaWord = lugandaWord;
        this.defaultWord = defaultWord;
    }

    public String getLugandaWord() {
        return lugandaWord;
    }


    public String getDefaultWord() {
        return defaultWord;
    }

    public int getImageResource(){
        return imageResource;
    }

    //this method is used to check whether or not the activity is having images
    public boolean hasImage(){
        return imageResource != NO_IMAGE_PROVIDED;
    }

}
