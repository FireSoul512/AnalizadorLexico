package com.analizador.Model;

public class MostarInfo {
    private String word;
    private String description;

    public MostarInfo(String word, String description){
        this.word = word;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "LexicalDescription{" +
                "word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
