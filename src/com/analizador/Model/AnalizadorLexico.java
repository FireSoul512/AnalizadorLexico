package com.analizador.Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {
    private Pattern pattern;
    private Matcher matcher;

    public MostarInfo detectaError(ArrayList<MostarInfo> lexico){
        for (MostarInfo word: lexico){
            if (word.getDescription().equals("Error de lexico"))
                return word;
        }
        return new MostarInfo("Bien","Bien");
    }

    public ArrayList<MostarInfo> analizar(String codigo){
        ArrayList<MostarInfo> lexico = new ArrayList<>();
        String[] separate =separateWords(codigo);

        for(String word: separate){
            if (validarNumero(word)) {
                MostarInfo numero = new MostarInfo(word, "Digito");
                numero.setTipo("int");
                numero.setValor(Integer.parseInt(word));
                lexico.add(numero);
            } else if (validarPalabraReservada(word)) lexico.add(new MostarInfo(word, "Palabra reservada"));
            else if (validarVariable(word)) lexico.add(new MostarInfo(word, "Identificador"));
            else if (validarOperador(word)) lexico.add(new MostarInfo(word, "SimboloOperacion"));
            else if (validarSimboloAgrupacion(word)) lexico.add(new MostarInfo(word, "Simbolo de agrupacion"));
            else if (validarPunto(word)) lexico.add(new MostarInfo(word, "Punto"));
            else if (validarAsignacion(word)) lexico.add(new MostarInfo(word, "Simbolo de asignacion"));
            else if (validarTerminacio(word)) lexico.add(new MostarInfo(word, "Simbolo de terminacion"));
            else if (validarComa(word)) lexico.add(new MostarInfo(word, "Coma"));
            else if (validarEspacio(word)) ;
            else lexico.add(new MostarInfo(word, "Error de lexico"));
        }
        lexico.add(new MostarInfo("$","Simbolo terminal"));

        return lexico;
    }

    private boolean validarTerminacio(String word){
        if (word.equals(";")) return true;
        return false;
    }

    private boolean validarAsignacion(String word){
        if (word.equals("->")) return true;
        return false;
    }

    private boolean validarComa(String word){
        if (word.equals(",")) return true;
        return false;
    }

    private boolean validarPunto(String word){
        if(word.equals(".")) return true;
        return false;
    }

    private boolean validarEspacio(String word){
        if (word.equals("")) return true;
        return false;
    }

    private boolean validarNumero(String word){
        pattern = Pattern.compile("^(([1-9]{1}[0-9]{0,9})|(0))$");
        matcher = pattern.matcher(word);
        return matcher.find();
    }

    private boolean validarVariable(String word){
        pattern = Pattern.compile("^[A-Za-z]+$");
        matcher = pattern.matcher(word);
        return matcher.find();
    }

    private boolean validarPalabraReservada(String word){
        pattern = Pattern.compile("^(circulo|cuadrado|triangulo|lienzo|int|rotacion|animar|reboteHorizontal|reboteVertical|" +
                "mover|diagonal|izquierda|derecha|arriba|abajo)$");
        matcher = pattern.matcher(word);
        return matcher.find();
    }

    private boolean validarSimboloAgrupacion(String word){
        pattern = Pattern.compile("^(\\(|\\)|\\{|\\})$");
        matcher = pattern.matcher(word);
        return matcher.find();
    }

    private boolean validarOperador(String word){
        pattern = Pattern.compile("^(\\+|\\-|\\*|\\/)$");
        matcher = pattern.matcher(word);
        return matcher.find();
    }

	private String[] separateWords(String code){
        Pattern p;
        Matcher m;

        p=Pattern.compile("->");
        m=p.matcher(code);
        code = m.replaceAll("f5df58oBleIguaLlkwemf");

        ArrayList<String> signos = new ArrayList<>();
        signos.add(";");
        signos.add("\\+");
        signos.add("-");
        signos.add("\\*");
        signos.add("/");
        signos.add("\\,");
        signos.add("\\.");
        signos.add("\\(");
        signos.add("\\)");
        signos.add("\\{");
        signos.add("\\}");

        for (int i = 0; i < signos.size(); i++){
            p = Pattern.compile(signos.get(i));
            m = p.matcher(code);
            code = m.replaceAll(" "+signos.get(i)+" ");
        }

        p=Pattern.compile("f5df58oBleIguaLlkwemf");
        m=p.matcher(code);
        code = m.replaceAll(" -> ");

        String[] separate = code.split("(\n|\\t| )+");
        return separate;
    }

}
