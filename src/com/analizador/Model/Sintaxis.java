package com.analizador.Model;

import javafx.scene.control.Alert;

import java.util.ArrayList;

public class Sintaxis {


    public String[][] crearTabla(){
        String digito = "<diferenciador> <operacion>";
        String identificador1 = "<identificador> <restoCuerpo> ; <cuerpo>";
        String identificador2 = "<diferenciador> <operacion>";
        String id3 = "<identificador>";
        String SO = "<signos> <diferenciador> <operacion>";
        String arriba = "<vertical>";
        String abajo  = "<vertical>";
        String mover = "mover ( <diferenciador> , <diferenciador> )";
        String animar = "animar ( <cruz> )";
        String diagonal = "diagonal ( <horizontal> , <vertical> )";
        String rotacion = "rotacion ( <diferenciador> )";
        String reboteH = "reboteHorizontal ( <horizontal> )";
        String reboteV = "reboteVertical ( <vertical> )";
        String Circulo = "circulo ( <diferenciador> , <diferenciador> , <diferenciador> )";
        String triangulo = "triangulo ( <diferenciador> , <diferenciador> , <diferenciador> , <diferenciador> )";
        String cuadrado = "cuadrado ( <diferenciador> , <diferenciador> , <diferenciador> )";
        String entero = "int ( <digito> )";
        String asig = "-> <tipo>";
        String punto = ". <accion>";
        String lienzo = "lienzo ( <digito> , <digito> ) { <cuerpo> }";
        String digito3 = "Digito";
        String id4 = "Identificador";
        String SO2 =  "simboloOperacion";
        String arriba2 = "arriba";
        String abajo2 = "abajo";
        String izquierda2 = "izquierda";
        String der2 = "derecha";
        String v = "<VACIO>";

        String [][] tabla = {
                {"-",               "Digito",     "Identificador",     "SimboloOperacion",     "arriba",    "abajo",       "izquierda",     "derecha",      "mover",    "animar",   "diagonal",     "rotacion",     "reboteHorizontal",     "reboteVertical",      "circulo",   "triangulo",    "cuadrado",     "int",      "->",   ".",    "lienzo",   ";",    "}"},
                {"<lienzo>",        "-",            "-",                        "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    lienzo,     "-",    "-"},
                {"<cuerpo>",        "-",           identificador1,              "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    v  },
                {"<restoCuerpo>",   "-",            "-",                        "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        asig,   punto,  "-",        "-",    "-"},
                {"<tipo>",          digito,       identificador2,               "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   Circulo,     triangulo,      cuadrado,       entero,     "-",    "-",    "-",        "-",    "-"},
                {"<operacion>",     "-",            "-",                        SO,             "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        v,      "-"},
                {"<diferenciador>", "<digito>",     id3,                        "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<accion>",        "-",            "-",                        "-",            "-",        "-",            "-",            "-",            mover,      animar,     diagonal,       rotacion,       reboteH,                reboteV,               "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<cruz>",          "-",            "-",                        "-",            arriba,     abajo,          "<horizontal>", "<horizontal>", "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<horizontal>",    "-",            "-",                        "-",            "-",        "-",            izquierda2,     der2,           "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<vertical>",      "-",            "-",                        "-",            arriba2,    abajo2,         "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<signos>",        "-",            "-",                        SO2,            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<identificador>", "-",            id4,                        "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"},
                {"<digito>",        digito3,        "-",                        "-",            "-",        "-",            "-",            "-",            "-",        "-",        "-",            "-",            "-",                    "-",                   "-",         "-",            "-",            "-",        "-",    "-",    "-",        "-",    "-"}
        };

        return tabla;
    }

    public boolean analizar(ArrayList<MostarInfo> codigo){
        String [][] tabla = crearTabla();
        Pila pila = new Pila("<lienzo>");
        String letra;
        for(int l = 0; l<codigo.size(); l++){
            MostarInfo word = codigo.get(l);
            letra = pila.observarUltimo();
            if (letra.equals(word.getWord()) || letra.equals(word.getDescription())) {
                pila.imprimir();
                System.out.print("  --->  ");
                imprimirLista(codigo, l);
                pila.quitar();
            } else{
                int posY = -10;
                for( int i = 1; i<14; i++){
                    if (tabla[i][0].equals(letra)){
                        posY = i;
                        break;
                    }
                }
                if (posY != -10){
                    int posX = -10;
                    for (int i = 1; i<23; i++)
                        if (tabla[0][i].equals(word.getWord()) || tabla[0][i].equals(word.getDescription())){
                            posX = i;
                            break;
                        }
                    if (posX != -10 && !tabla[posY][posX].equals("-") && !tabla[posY][posX].equals("<VACIO>")){
                        pila.imprimir();
                        System.out.print("  --->  ");
                        imprimirLista(codigo, l);
                        pila.quitar();
                        pila.agregar(tabla[posY][posX]);
                        l--;
                    } else if (posX != -10 && tabla[posY][posX].equals("<VACIO>")) {
                        pila.imprimir();
                        System.out.print("  --->  ");
                        imprimirLista(codigo, l);
                        l--;
                        pila.quitar();
                    } else {
                        pila.imprimir();
                        System.out.print("  --->  ");
                        imprimirLista(codigo, l);
                        error(word.getWord(), pila);
                        return false;
                    }
                } else {
                    pila.imprimir();
                    System.out.print("  --->  ");
                    imprimirLista(codigo, l);
                    error(word.getWord(), pila);
                    return false;
                }
            }
            System.out.println();
        }
        return true;
    }

    private void imprimirLista(ArrayList<MostarInfo> lista, int pos){
        System.out.print("[");
        for (int i = pos; i<lista.size(); i++){
            System.out.print(lista.get(i).getWord());
            if (i != lista.size()-1) System.out.print(", ");
        }
        System.out.println("]");
    }

    private void error(String error, Pila pila){
        String er = pila.observarUltimo();
        switch (er){
            case "<lienzo>":er = "lienzo y sus tamaños"; break;
            case "<cuerpo>": er = "}"; break;
            case "<restoCuerpo>": er = "resto del cuerpo"; break;
            case "<tipo>":er = "tipo de dato"; break;
            case "<operacion>":er = "signo de operacion"; break;
            case "<diferenciador>":er = "un identificador o numero"; break;
            case "<accion>":er = "una accion a realizar"; break;
            case "<cruz>":er = "una direccion"; break;
            case "<horizontal>":er = "una direccion horizontal"; break;
            case "<vertical>":er = "una direccion vertical"; break;
            case "<signos>":er = "signos de operacion"; break;
            case "<identificador>":er = "un identificador"; break;
            case "<digito>":er = "un digito"; break;
            default: er = pila.observarUltimo(); break;
        }
        System.out.println("Error sintactico en "+error+" se esperaba "+er);
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Ejecucion finalizada");
        if (error.equals("$")) {
            alerta.setContentText("Error sintactico se esperaba "+er);
        } else {
            alerta.setContentText("Error sintactico en "+error+" se esperaba "+er);
        }
        alerta.showAndWait();
    }
}
