package genetic;


import datastructure.Expr;
import datastructure.TestCase;
import parser.Scanner;

public class UTPCGA {

    public static void main(String[] args) {
        // Create GA object
        String str_expr = "(b & e & ~a & ~d & ~f) | (b & e & ~b & ~d & ~f) | (b & d & e & ~a & ~e & ~f) | (b & d & e & ~b & ~e & ~f) | (b & d & f & ~a & ~e & ~f) | (b & d & f & ~b & ~e & ~f) | (b & e & f & ~a & ~d & ~f) | (b & e & f & ~b & ~d & ~f) | (a & d & ~a & ~e & ~f & ~h) | (a & d & ~b & ~e & ~f & ~h) | (a & e & ~a & ~d & ~f & ~h) | (a & e & ~b & ~d & ~f & ~h) | (b & e & ~a & ~d & ~e & ~f) | (b & e & ~b & ~d & ~e & ~f) | (b & f & ~a & ~d & ~e & ~f) | (b & f & ~b & ~d & ~e & ~f) | (a & c & d & h & ~a & ~e & ~f) | (a & c & d & h & ~b & ~e & ~f) | (a & c & e & h & ~a & ~d & ~f) | (a & c & e & h & ~b & ~d & ~f) | (a & d & e & ~a & ~d & ~f & ~h) | (a & d & e & ~a & ~e & ~f & ~h) | (a & d & e & ~b & ~d & ~f & ~h) | (a & d & e & ~b & ~e & ~f & ~h) | (a & c & d & e & h & ~a & ~d & ~f) | (a & c & d & e & h & ~a & ~e & ~f) | (a & c & d & e & h & ~b & ~d & ~f) | (a & c & d & e & h & ~b & ~e & ~f) | (a & d & ~a & ~d & ~e & ~f & ~h) | (a & d & ~b & ~d & ~e & ~f & ~h) | (a & e & ~a & ~d & ~e & ~f & ~h) | (a & e & ~b & ~d & ~e & ~f & ~h) | (a & c & d & h & ~a & ~d & ~e & ~f) | (a & c & d & h & ~b & ~d & ~e & ~f) | (a & c & e & h & ~a & ~d & ~e & ~f) | (a & c & e & h & ~b & ~d & ~e & ~f)";
        Expr expr = new Expr(str_expr);
        Scanner.printNotConstant(expr.getTerms());
        GeneticAlgorithm ga;
        Population population;
        int generation;
        for(int index = 0; index<expr.getTerms().size(); index++) {
            System.out.println("index: " + index);
            if (!expr.getTerms().get(index).getIsConstant()) {
                generation = 1;
                ga = new GeneticAlgorithm(100, 0.001, 0.95, 2,expr, index);
                population = ga.initPopulation( expr,expr.getTerms().get(index));
                ga.evalPopulation(population);
                while (!ga.isTerminationConditionMet(population)) {
                    // Print fittest individual from population
                    System.out.println("Best solution: " + population.getFittest(0).getFitness());
                    // Apply crossover
                    population = ga.crossoverPopulation(population);
                    // Apply mutation
                    population = ga.mutatePopulation(population);
                    // Evaluate population
                    ga.evalPopulation(population);
                    // Increment the current generation
                    generation++;
                }

                /**
                 * We're out of the loop now, which means we have a perfect solution on
                 * our hands. Let's print it out to confirm that it is actually all
                 * ones, as promised.
                 */

                System.out.println("Found solution in " + generation + " generations");
                System.out.println("Best solution: ");
                TestCase.printTs(population.getFittest(0).getChromosome());
            }
        }
    }
}
