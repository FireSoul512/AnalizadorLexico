package com.analizador.Model;

import java.util.ArrayList;

public class AnalizadorSemantico {

    private int operacion(ArrayList<MostarInfo> codigo, ArrayList<MostarInfo> declarados, int operacion, int j){
        int valor = 0;
        if (codigo.get(j).getDescription().equals("Identificador")){
            int pos = buscarPos(declarados, codigo.get(j));
            valor = declarados.get(pos).getValor();
        } else if (codigo.get(j).getTipo().equals("int")){
            valor = codigo.get(j).getValor();
        }

        if (codigo.get(j-1).getDescription().equals("SimboloOperacion")){
            switch (codigo.get(j-1).getWord()){
                case "+":  operacion = operacion + valor; break;
                case "-": operacion = operacion - valor; break;
                case "*": operacion = operacion * valor; break;
                case "/": operacion = operacion / valor; break;
            }
        } else {
            operacion = valor;
        }
        return operacion;
    }

    private boolean validarInt(ArrayList<MostarInfo> declarados, MostarInfo inf){
        if (inf.getTipo().equals("int")) return true;
        int pos = buscarPos(declarados, inf);
        if (pos == -50) return false;
        if (declarados.get(pos).getTipo().equals("int"))return true;
        else return false;
    }

    public boolean prueba(ArrayList<MostarInfo> codigo){
        ArrayList<MostarInfo> declarados = new ArrayList<>();

        for (int i = 0; i < codigo.size(); i++){
            if (codigo.get(i).getWord().equals("->") && validarInt(declarados, codigo.get(i+1))){
                int operacion = 0;
                int posicion = 0;
                for (int j = i; j<codigo.size() ; j++){
                    if (codigo.get(j).getDescription().equals("Identificador")){
                        int pos = buscarPos(declarados, codigo.get(j));
                        if (pos == -50){
                            System.out.println("Error: La variable '"+codigo.get(j).getWord()+"' no ha sido declarada");
                            return false;
                        } else if (!declarados.get(pos).getTipo().equals("int")){
                            System.out.println("Error: la variable '"+codigo.get(j).getWord()+"' es tipo '"+declarados.get(pos).getTipo()+"'");
                            return false;
                        }
                    }
                    if (codigo.get(j).getDescription().equals("Identificador") || codigo.get(j).getTipo().equals("int"))
                        operacion = operacion(codigo, declarados, operacion, j);
                    posicion = j;
                    if (codigo.get(j+1).getWord().equals(";")) j = codigo.size();
                }
                codigo.get(i-1).setValor(operacion);
                codigo.get(i-1).setTipo("int");
                int num = buscarPos(declarados, codigo.get(i-1));
                if (num == -50){
                    declarados.add(codigo.get(i-1));
                } else {
                    declarados.remove(num);
                    declarados.add(codigo.get(i-1));
                } i = posicion;
            } else if (codigo.get(i).getWord().equals("->")){
                if (codigo.get(i+1).getWord().equals("int")) {
                    codigo.get(i-1).setValor(codigo.get(i+3).getValor());
                    codigo.get(i-1).setTipo("int");
                } else if (codigo.get(i+1).getWord().equals("circulo") || codigo.get(i+1).getWord().equals("cuadrado") || codigo.get(i+1).getWord().equals("triangulo")) {
                    codigo.get(i-1).setTipo(codigo.get(i+1).getWord());
                } else {
                    System.out.println("Error: la varaible '"+codigo.get(i+1).getWord()+"' no se puede utilizar");
                    return false;
                }

                int num = buscarPos(declarados, codigo.get(i-1));
                if (num == -50){
                    declarados.add(codigo.get(i-1));
                } else {
                    System.out.println("Error: La variable '"+codigo.get(i-1).getWord()+"' ya ha sido declarada");
                    return false;
                }
            } else if (codigo.get(i).getWord().equals(".")){
                MostarInfo inf = buscar(declarados, codigo.get(i-1));
                if (inf.getTipo().equals("")){
                    System.out.println("Error: La variable '"+inf.getWord()+"' no ha sido declarada");
                    return false;
                } else if (!inf.getTipo().equals("cuadrado") && !inf.getTipo().equals("triangulo") && !inf.getTipo().equals("circulo")){
                    System.out.println("Error: la variable '"+inf.getWord()+"' es tipo '"+inf.getTipo()+"'");
                    return false;
                }
            }
            if (codigo.get(i).getWord().equals("mover")){
                for (int j = i+2; j<codigo.size() ; j++){
                    i = j;
                    MostarInfo info = buscar(declarados, codigo.get(j));
                    if (codigo.get(j).getWord().equals(")"))
                        j = codigo.size();

                    else if ( !info.getTipo().equals("int") && !info.getWord().equals(",") ){
                        if(info.getTipo().equals(""))
                            System.out.println("Error: La variable '"+info.getWord()+"' no ha sido declarada");
                        else
                            System.out.println("Error: la variable '"+info.getWord()+"' es tipo '"+info.getTipo()+"'");
                        return false;
                    }
                }
            }

        }
        for (MostarInfo x:  declarados){
            System.out.println("-------------");
            System.out.println(x.getWord());
            System.out.println(x.getTipo());
            System.out.println(x.getValor());
            System.out.println(x.getDescription());
        }
        return true;
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
