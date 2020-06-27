package com.example.luganda;

public class Word {
    private String lugandaWord;
    private String defaultWord;
    private int imageResource;

    public Word(String lugandaWord, String defaultWord,int imageResource) {
        this.lugandaWord = lugandaWord;
        this.defaultWord = defaultWord;
        this.imageResource = imageResource;
    }

    public Word(String lugandaWord,String defaultWord){
        this.lugandaWord = lugandaWord;
        this.defaultWord = defaultWord;
        this.imageResource = 0;
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

}
