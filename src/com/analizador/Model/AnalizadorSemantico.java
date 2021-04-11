package com.analizador.Model;

import com.analizador.Model.Compilacion.Circulo;
import com.analizador.Model.Compilacion.Cuadrado;
import com.analizador.Model.Compilacion.Lienzo;
import com.analizador.Model.Compilacion.Triangulo;
import javafx.scene.Node;
import javafx.scene.control.Alert;

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

    private void alertaError(String error){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error semantico");
        alerta.setContentText(error);
        alerta.showAndWait();
    }

    public boolean prueba(ArrayList<MostarInfo> codigo){
        int lienzoX = codigo.get(2).getValor();
        int lienzoY = codigo.get(4).getValor();
        ArrayList<MostarInfo> declarados = new ArrayList<>();
        ArrayList<Object> figuras = new ArrayList<>();
        Lienzo lienzo = new Lienzo(lienzoX, lienzoY);

        for (int i = 0; i < codigo.size(); i++){
            if (codigo.get(i).getWord().equals("->") && validarInt(declarados, codigo.get(i+1))){
                int operacion = 0;
                int posicion = 0;
                for (int j = i; j<codigo.size() ; j++){
                    if (codigo.get(j).getDescription().equals("Identificador")){
                        int pos = buscarPos(declarados, codigo.get(j));
                        if (pos == -50){
                            alertaError("Error: La variable '"+codigo.get(j).getWord()+"' no ha sido declarada");
                            return false;
                        } else if (!declarados.get(pos).getTipo().equals("int")){
                            alertaError("Error: la variable '"+codigo.get(j).getWord()+"' es tipo '"+declarados.get(pos).getTipo()+"'");
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
            }
            else if (codigo.get(i).getWord().equals("->")){
                if (codigo.get(i+1).getWord().equals("int")) {
                    codigo.get(i-1).setValor(codigo.get(i+3).getValor());
                    codigo.get(i-1).setTipo("int");
                } else if (codigo.get(i+1).getWord().equals("circulo") || codigo.get(i+1).getWord().equals("cuadrado") || codigo.get(i+1).getWord().equals("triangulo")) {
                    codigo.get(i-1).setTipo(codigo.get(i+1).getWord());
                } else {
                    alertaError("Error: la varaible '"+codigo.get(i+1).getWord()+"' no se puede utilizar");
                    return false;
                }

                int num = buscarPos(declarados, codigo.get(i-1));
                if (num == -50){
                    declarados.add(codigo.get(i-1));
                    switch (codigo.get(i+1).getWord()){
                        case "circulo":
                            Circulo circulo = new Circulo(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)), valor(declarados, codigo.get(i+7)), lienzoX, lienzoY);
                            circulo.setVariable(codigo.get(i-1).getWord());
                            figuras.add(circulo);
                            break;
                        case "cuadrado":
                            Cuadrado cuadrado = new Cuadrado(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)), valor(declarados, codigo.get(i+7)), lienzoX, lienzoY);
                            cuadrado.setVariable(codigo.get(i-1).getWord());
                            figuras.add(cuadrado);
                            break;
                        case "triangulo":
                            Triangulo triangulo = new Triangulo(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)), valor(declarados, codigo.get(i+7)), valor(declarados, codigo.get(i+9)), lienzoX, lienzoY);
                            triangulo.setVariable(codigo.get(i-1).getWord());
                            figuras.add(triangulo);
                            break;
                    }
                } else {
                    alertaError("Error: La variable '"+codigo.get(i-1).getWord()+"' ya ha sido declarada");
                    return false;
                }
            } else if (codigo.get(i).getWord().equals(".")){
                MostarInfo inf = buscar(declarados, codigo.get(i-1));
                if (inf.getTipo().equals("")){
                    alertaError("Error: La variable '"+inf.getWord()+"' no ha sido declarada");
                    return false;
                } else if (!inf.getTipo().equals("cuadrado") && !inf.getTipo().equals("triangulo") && !inf.getTipo().equals("circulo")){
                    alertaError("Error: la variable '"+inf.getWord()+"' es tipo '"+inf.getTipo()+"'");
                    return false;
                } else if (inf.getTipo().equals("cuadrado") || inf.getTipo().equals("triangulo") || inf.getTipo().equals("circulo")){
                    int pos = buscarPosFigura(figuras, codigo.get(i-1).getWord());
                    if (pos == -50){
                        alertaError("Ilegal error");
                        return false;
                    } else {
                        switch (codigo.get(i+1).getWord()){
                            case "mover":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).mover(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).mover(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)));
                                else
                                    ((Triangulo)figuras.get(pos)).mover(valor(declarados, codigo.get(i+3)), valor(declarados, codigo.get(i+5)));
                                break;
                            case "animar":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).animar(decoficicarAccionCruz(codigo.get(i+3).getWord()));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).animar(decoficicarAccionCruz(codigo.get(i+3).getWord()));
                                else
                                    ((Triangulo)figuras.get(pos)).animar(decoficicarAccionCruz(codigo.get(i+3).getWord()));
                                break;
                            case "diagonal":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).diagonal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()), decoficicarAccionVertical(codigo.get(i+5).getWord()));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).diagonal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()), decoficicarAccionVertical(codigo.get(i+5).getWord()));
                                else
                                    ((Triangulo)figuras.get(pos)).diagonal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()), decoficicarAccionVertical(codigo.get(i+5).getWord()));
                                break;
                            case "rotacion":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).rotacion(valor(declarados, codigo.get(i+3)));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).rotacion(valor(declarados, codigo.get(i+3)));
                                else
                                    ((Triangulo)figuras.get(pos)).rotacion(valor(declarados, codigo.get(i+3)));
                                break;
                            case "reboteHorizontal":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).reboteHorizontal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).reboteHorizontal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()));
                                else
                                    ((Triangulo)figuras.get(pos)).reboteHorizontal(decoficicarAccionHorizontal(codigo.get(i+3).getWord()));
                                break;
                            case "reboteVertical":
                                if (figuras.get(pos).getClass() == Circulo.class)
                                    ((Circulo)figuras.get(pos)).reboteVertical(decoficicarAccionHorizontal(codigo.get(i+3).getWord()));
                                else if (figuras.get(pos).getClass() == Cuadrado.class)
                                    ((Cuadrado)figuras.get(pos)).reboteVertical(decoficicarAccionVertical(codigo.get(i+3).getWord()));
                                else
                                    ((Triangulo)figuras.get(pos)).reboteVertical(decoficicarAccionVertical(codigo.get(i+3).getWord()));
                                break;
                        }
                    }
                }
            }
            if (codigo.get(i).getWord().equals("(")){
                for (int j = i+1; j<codigo.size() ; j++){
                    i = j;
                    MostarInfo info = buscar(declarados, codigo.get(j));
                    if (codigo.get(j).getWord().equals(")"))
                        j = codigo.size();

                    else if ( !info.getTipo().equals("int") && !info.getWord().equals(",") && !info.getDescription().equals("Palabra reservada")){
                        if(info.getTipo().equals(""))
                            alertaError("Error: La variable '"+info.getWord()+"' no ha sido declarada");
                        else
                            alertaError("Error: la variable '"+info.getWord()+"' es tipo '"+info.getTipo()+"'");
                        return false;
                    }
                }
            }

        }
        for (Object temp: figuras){
            lienzo.agregarFigura((Node)temp);
            if (temp.getClass() == Cuadrado.class){
                ((Cuadrado) temp).animacion();
            } else if (temp.getClass() == Circulo.class){
                ((Circulo) temp).animacion();
            } else if (temp.getClass() == Triangulo.class){
                ((Triangulo) temp).animacion();
            }
        }
        lienzo.run();
        return true;
    }

    private int buscarPosFigura(ArrayList<Object> figuras, String variable){
        for(int j = 0; j<figuras.size(); j++){
            if (figuras.get(j).getClass() == Cuadrado.class){
                if (((Cuadrado)figuras.get(j)).getVariable().equals(variable)) return j;
            } else if (figuras.get(j).getClass() == Circulo.class){
                if (((Circulo)figuras.get(j)).getVariable().equals(variable)) return j;
            } else {
                if (((Triangulo)figuras.get(j)).getVariable().equals(variable)) return j;
            }
        }
        return -50;
    }

    private int decoficicarAccionVertical(String accion){
        switch (accion){
            case "arriba": return 1;
            case "abajo":  return 2;
        }
        return -50;
    }

    private int decoficicarAccionHorizontal(String accion){
        switch (accion){
            case "izquierda": return 1;
            case "derecha": return 2;
        }
        return -50;
    }

    private int decoficicarAccionCruz(String accion){
        switch (accion){
            case "arriba": return 4;
            case "abajo":  return 3;
            case "izquierda": return 2;
            case "derecha": return 1;
        }
        return -50;
    }

    private MostarInfo buscar(ArrayList<MostarInfo> declarados, MostarInfo word){
        for (MostarInfo declarado: declarados){
            if(declarado.getWord().equals(word.getWord())){
                return declarado;
            }
        }
        return word;
    }

    private int valor(ArrayList<MostarInfo> declarados, MostarInfo word){
        for (int i= 0; i<declarados.size(); i++){
            if (declarados.get(i).getWord().equals(word.getWord())){
                return declarados.get(i).getValor();
            }
        }
        return word.getValor();
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
