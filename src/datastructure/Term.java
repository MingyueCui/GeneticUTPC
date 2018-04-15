package datastructure;

import genetic.Individual;

import java.util.HashSet;

public class Term {
    private String term;
    private HashSet<String> tokens;
    private boolean isConstant;
    private boolean termValue;
    public Term(String term){
        setTerm(term);
        setTokens();
    }
    public void setTerm(String term){
        this.term = term;
    }
    public String getTerm(){
        return this.term;
    }

    public void setTokens(){
        String symbol = "~";
        tokens = new HashSet<>();
        for(int j = 0;j<term.length();j++){
            String temp_literal = term.charAt(j) + "";
            if(symbol.equals(temp_literal)){
                if(tokens.contains(term.charAt(j+1)+"")){
                    isConstant = true;
                }
                tokens.add("~"+term.charAt(j+1)+"");
                j++;
            } else {
                if(tokens.contains("~"+term.charAt(j))){
                    isConstant = true;
                }
                tokens.add(term.charAt(j)+"");
            }
        }

    }
    public HashSet<String> getTokens(){
        return this.tokens;
    }
    public void setIsConstant(){

    }
    public boolean getIsConstant(){
        return isConstant;
    }
    public void calTermValue(Individual individual){
        termValue = true;
        boolean literalValue;
        if(!isConstant){
            String current_literal;
            for(String literal: tokens) {
                if(literal.contains("~")) {
                    current_literal = literal.charAt(1)+"";
                    literalValue = !individual.getChromosome().get(current_literal);
                } else {
                    literalValue = individual.getChromosome().get(literal);
                }
                termValue = termValue && literalValue;
            }
        } else {
            termValue = false;
        }
    }

    public boolean getTermValue(){
        return this.termValue;
    }

}
