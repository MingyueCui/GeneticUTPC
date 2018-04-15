package datastructure;

import utils.DnfTransfer;

import java.util.Hashtable;
import java.util.List;
import parser.Scanner;

public class Expr {
    private String expr;
    private String normalExpr;
//    private String falseExpr;
    private List<Term> terms;
//    private List<Term> f_terms;
    private Hashtable<String,Boolean> literals;

    public Expr(String expr){
        this.expr = expr;
        normalExpr = DnfTransfer.toDnf(expr);
        setNormalExpr();
        setTerms();
        setLiterals();
    }
    public void setNormalExpr(){
        normalExpr = Scanner.initExpr(expr);
    }
    public String getNormalExpr(){
        return this.normalExpr;
    }

//    public void setFalseExpr(){
//        String f_expr = "~("+normalExpr+")";
//        String temp_f_Expr = DnfTransfer.toDnf(f_expr);
//        falseExpr = Scanner.initExpr(temp_f_Expr);
//    }

//    public String getFalseExpr(){
//        return this.falseExpr;
//    }

    public void setTerms(){
        terms = Scanner.segExpr(normalExpr);
    }

    public List<Term> getTerms(){
        return this.terms;
    }

//    public void setF_terms(){
//        f_terms = Scanner.segExpr(falseExpr);
//
//    }
//    public List<Term> getF_terms(){
//        return this.f_terms;
//    }

    public void setLiterals() {
        this.literals = Scanner.parseLiterals(expr, terms);
    }

    public Hashtable<String,Boolean> getLiterals() {
        return literals;
    }
}
