package com.analizador.Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {


    @SuppressWarnings("unused")
	private String[] separateWords(String code){
        code = code+"\n";
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
        signos.add(".");
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

        String[] separate = code.split("(\\t|\n| )+");
        return separate;
    }

}
