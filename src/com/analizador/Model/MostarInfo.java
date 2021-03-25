package com.analizador.Model;

public class MostarInfo {
    private String word;
    private String description;
    private String tipo;
    private int valor;

    public MostarInfo(String word, String description){
        this.word = word;
        this.description = description;
        this.tipo = "";
        this.valor = 0;
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

    public String getTipo() { return tipo; }

    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getValor() { return valor; }

    public void setValor(int valor) {  this.valor = valor; }

    @Override
    public String toString() {
        return "LexicalDescription{" +
                "word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
