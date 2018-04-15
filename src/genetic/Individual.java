package genetic;

import datastructure.Term;
import datastructure.TestCase;

import java.util.Hashtable;

public class Individual {
    private Hashtable<String,Boolean> chromosome;
    private double fitness = -1;
    private Term boolExpr;

    public Individual(Hashtable<String,Boolean> input_chromosome, Term boolExpr) {
        //some problems
        chromosome = input_chromosome;
        this.boolExpr = boolExpr;
        for (String name:input_chromosome.keySet()) {
            if(boolExpr.getTokens().contains(name)) {
                this.setGene(name, true);
            } else if (boolExpr.getTokens().contains("~"+name)){
                this.setGene(name,false);
            }else{
                double a = Math.random();
                if (0.5 < a) {
                    this.setGene(name, true);
                } else {
                    this.setGene(name, false);
                }
            }

        }
    }

    public Term getBoolExpr() {
        return boolExpr;
    }

    public Hashtable<String,Boolean> getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.size();
    }

    public void setGene(String name, boolean value) {
        chromosome.replace(name,value);
    }

    public boolean getGene(String name) {
        return this.chromosome.get(name);
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public String toString() {
        TestCase.printTs(chromosome);
        return  " -> " + fitness;
    }
}
