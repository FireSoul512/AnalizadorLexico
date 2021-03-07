package com.analizador.Model;

import java.util.ArrayList;

public class Pila {
    ArrayList<String> pila;

    public Pila(String word){
        pila = new ArrayList<>();
        pila.add(word);
        pila.add("$");
    }

    public void agregar(String words){
        String[] word = words.split("( )+");
        for (int i = word.length-1; i>=0; i--){
            pila.add(0,word[i]);
        }
    }

    public boolean quitar(){
        if (pila.size() > 0){
            pila.remove(0);
            return true;
        }
        return false;
    }

    public String observarUltimo(){
        if (pila.size() > 0){
            return pila.get(0);
        }
        return "Error";
    }

    public void imprimir(){
        if (pila.size() > 0){
            System.out.print(pila);
        } else {
            System.out.println("Pila vacia");
        }
    }
}
