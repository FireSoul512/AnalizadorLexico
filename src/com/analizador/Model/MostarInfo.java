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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "LexicalDescription{" +
                "word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
