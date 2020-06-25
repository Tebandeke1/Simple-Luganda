package com.example.luganda;

public class Word {
    private String lugandaWord;
    private String defaultWord;

    public Word(String lugandaWord, String defaultWord) {
        this.lugandaWord = lugandaWord;
        this.defaultWord = defaultWord;
    }

    public String getLugandaWord() {
        return lugandaWord;
    }

    public void setLugandaWord(String lugandaWord) {
        this.lugandaWord = lugandaWord;
    }

    public String getDefaultWord() {
        return defaultWord;
    }

    public void setDefaultWord(String defaultWord) {
        this.defaultWord = defaultWord;
    }
}
