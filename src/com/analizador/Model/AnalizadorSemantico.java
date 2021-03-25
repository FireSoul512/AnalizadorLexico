package com.analizador.Model;

import java.util.ArrayList;

public class AnalizadorSemantico {

    public void prueba(ArrayList<MostarInfo> codigo){
        ArrayList<MostarInfo> declarados = new ArrayList<>();
        for (int i = 0; i < codigo.size(); i++){
            if (codigo.get(i).getWord().equals("->")){
                if (codigo.get(i+1).getWord().equals("int")) {
                    codigo.get(i-1).setTipo(codigo.get(i+1).getWord());
                    codigo.get(i-1).setValor(codigo.get(i+3).getValor());
                } else if (codigo.get(i+1).getTipo().equals("int")){
                    codigo.get(i-1).setTipo("int");
                } else  codigo.get(i-1).setTipo(codigo.get(i+1).getWord());

                int num = buscarPos(declarados, codigo.get(i-1));
                if (num == -50){
                    declarados.add(codigo.get(i-1));
                } else {
                    declarados.remove(num);
                    declarados.add(codigo.get(i-1));
                }
            } else if (codigo.get(i).getWord().equals(".")){
                MostarInfo inf = buscar(declarados, codigo.get(i-1));
                if (inf.getTipo().equals("")){
                    System.out.println("Error: La variable '"+inf.getWord()+"' no ha sido declarada");
                } else if (!inf.getTipo().equals("cuadrado") && !inf.getTipo().equals("triangulo") && !inf.getTipo().equals("circulo")){
                    System.out.println("Error: la variable '"+inf.getWord()+"' es tipo '"+inf.getTipo()+"'");
                }
            }

            if (codigo.get(i).getWord().equals("(")){
                for (int j = i+1; j<codigo.size() ; j++){
                    i = j;
                    MostarInfo info = buscar(declarados, codigo.get(j));
                    if (codigo.get(j).getWord().equals(")"))
                        j = codigo.size();

                    else if ( !info.getTipo().equals("int") && !info.getWord().equals(",") )
                        System.out.println("Error: la variable '"+info.getWord()+"' es tipo '"+info.getTipo()+"'");
                }
            }

        }
    }

    private MostarInfo buscar(ArrayList<MostarInfo> declarados, MostarInfo word){
        for (MostarInfo declarado: declarados){
            if(declarado.getWord().equals(word.getWord())){
                return declarado;
            }
        }
        return word;
    }

    private int buscarPos(ArrayList<MostarInfo> declarados, MostarInfo word){
        for (int i= 0; i<declarados.size(); i++){
            if (declarados.get(i).getWord().equals(word.getWord())){
                return i;
            }
        }
        return -50;
    }
}
