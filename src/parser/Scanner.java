package parser;

import datastructure.Literal;
import datastructure.Term;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

public class Scanner {
    public Scanner(){

    }

    public static String initExpr(String expr){
        expr = expr.replace(" ","");
        expr = expr.replace("(","");
        expr = expr.replace(")","");
        expr = expr.replace("&","");
        System.out.println(expr);
        return expr;
    }

    public static List<Term> segExpr(String expr){
        String[] temps = expr.split("\\|");
        List<Term> terms = new ArrayList<>();
        for(int i = 0;i<temps.length;i++){
            Term new_term = new Term(temps[i]);
            terms.add(new_term);
        }
        return terms;
    }

    public static Hashtable<String,Boolean> parseLiterals(String expr, List<Term> terms){
        Hashtable<String,Boolean> testcase = new Hashtable<>();
        String symbol = "~";
        for(int i = 0;i<terms.size();i++){
            String currentTerm = terms.get(i).getTerm();
            for(int j = 0;j<currentTerm.length();j++){
                if(!symbol.contains(currentTerm.charAt(j)+"")){
                    testcase.put(currentTerm.charAt(j)+"",true);
                } else {
                    testcase.put(currentTerm.charAt(j+1)+"",true);
                    j++;
                }
            }
        }
        return  testcase;
    }

    public static void printNotConstant(List<Term> terms){
        for(int i = 0;i < terms.size();i++){
            if(!terms.get(i).getIsConstant())
                System.out.println("项："+terms.get(i).getTerm()+" ");
        }
    }

}
