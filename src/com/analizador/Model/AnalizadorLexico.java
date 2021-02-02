package com.analizador.Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorLexico {

    private String[] separateWords(String code){
        code = code+"\n";
        Pattern p = Pattern.compile("(//)[^\\n]*\\n");
        Matcher m = p.matcher(code);
        code = m.replaceAll("//");
        p = Pattern.compile("(#)[^\\n]*\\n");
        m = p.matcher(code);
        code = m.replaceAll("#");

        p = Pattern.compile("(\")[^\"]*\"");
        m = p.matcher(code);
        code = m.replaceAll("\"\"");

        p=Pattern.compile("==");
        m=p.matcher(code);
        code = m.replaceAll("f5df58oBleIguaLlkwemf");
        p=Pattern.compile("<=");
        m=p.matcher(code);
        code = m.replaceAll("lkwrmfMenorIgualkfL");
        p=Pattern.compile(">=");
        m=p.matcher(code);
        code = m.replaceAll("lkwmfmayorIguaLlwkmf");
        p=Pattern.compile("!=");
        m=p.matcher(code);
        code = m.replaceAll("wbksmd8iferentEdnfusn");
        p=Pattern.compile("//");
        m=p.matcher(code);
        code = m.replaceAll("?comentario?%");
        p=Pattern.compile("\n");
        m=p.matcher(code);
        code = m.replaceAll("?saltolinea?%");

        ArrayList<String> signos = new ArrayList<>();
        signos.add(":");
        signos.add("\\+");
        signos.add("-");
        signos.add("\\*");
        signos.add("/");
        signos.add("#");
        signos.add("\\^");
        signos.add("<");
        signos.add(">");
        signos.add("=");
        signos.add("\"");
        signos.add("\\(");
        signos.add("\\)");
        signos.add("\\[");
        signos.add("\\]");

        for (int i = 0; i < signos.size(); i++){
            p = Pattern.compile(signos.get(i));
            m = p.matcher(code);
            code = m.replaceAll(" "+signos.get(i)+" ");
        }

        p=Pattern.compile("f5df58oBleIguaLlkwemf");
        m=p.matcher(code);
        code = m.replaceAll(" == ");
        p=Pattern.compile("lkwrmfMenorIgualkfL");
        m=p.matcher(code);
        code = m.replaceAll(" <= ");
        p=Pattern.compile("lkwmfmayorIguaLlwkmf");
        m=p.matcher(code);
        code = m.replaceAll(" >= ");
        p=Pattern.compile("wbksmd8iferentEdnfusn");
        m=p.matcher(code);
        code = m.replaceAll(" != ");
        p=Pattern.compile("\\?comentario\\?%");
        m=p.matcher(code);
        code = m.replaceAll(" // ");
        code = code.trim();
        p=Pattern.compile("\\?saltolinea\\?%");
        m=p.matcher(code);
        code = m.replaceAll(" \n ");
        String[] separate = code.split("(\\t| )+");
        return separate;
    }

}
