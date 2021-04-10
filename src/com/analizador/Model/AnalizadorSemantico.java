package com.analizador.Model;

import java.util.ArrayList;

public class AnalizadorSemantico {

    public void prueba(ArrayList<MostarInfo> codigo){
        ArrayList<MostarInfo> declarados = new ArrayList<>();

        for (int i = 0; i < codigo.size(); i++){
            // Parahacer la suma
            if (codigo.get(i).getWord().equals("->") && codigo.get(i+1).getTipo().equals("int")){
                for (int j = i+1; j<codigo.size() ; j++){
                    if (codigo.get(j).getTipo().equals("int"))
                        System.out.println(codigo.get(j).getValor());
                    else if (codigo.get(j).getDescription().equals("Identificador")){
                        int pos = buscarPos(declarados, codigo.get(j));
                        if (pos == -50){
                            System.out.println("Error: La variable '"+codigo.get(j).getWord()+"' no ha sido declarada");
                        } else if (!declarados.get(pos).getTipo().equals("int")){
                            System.out.println("Error: la variable '"+codigo.get(j).getWord()+"' es tipo '"+declarados.get(pos).getTipo()+"'");
                        } else {
                            System.out.print(declarados.get(pos).getWord()+": ");
                            System.out.println(declarados.get(pos).getValor());
                        }
                    }
                    

                    if (codigo.get(j+1).getWord().equals(";")) j = codigo.size();
                }
            } else if (codigo.get(i).getWord().equals("->")){
                if (codigo.get(i+1).getWord().equals("int")) {
                    codigo.get(i-1).setTipo(codigo.get(i+1).getWord());
                    codigo.get(i-1).setValor(codigo.get(i+3).getValor());
                } else  codigo.get(i-1).setTipo(codigo.get(i+1).getWord());

                int num = buscarPos(declarados, codigo.get(i-1));
                if (num == -50){
                    declarados.add(codigo.get(i-1));
                } else {
                    System.out.println("Error: La variable '"+codigo.get(i-1).getWord()+"' ya ha sido declarada");
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

                    else if ( !info.getTipo().equals("int") && !info.getWord().equals(",") ){
                        if(info.getTipo().equals(""))
                            System.out.println("Error: La variable '"+info.getWord()+"' no ha sido declarada");
                        else
                            System.out.println("Error: la variable '"+info.getWord()+"' es tipo '"+info.getTipo()+"'");
                    }
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
