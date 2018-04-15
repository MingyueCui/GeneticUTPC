package genetic;

import datastructure.Expr;
import datastructure.Term;


public class GeneticAlgorithm {
    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    //精英计数
    private int elitismCount;
    public Expr expr;
    int index;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount ,
                            Expr expr,int index) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitismCount;
        this.expr = expr;
        this.index = index;
    }

    public Population initPopulation(Expr expr, Term term) {
        // Initialize population
        Population population = new Population(this.populationSize, expr, term);
        return population;
    }

    public double calcFitness(Individual individual) {
        int total = expr.getTerms().size();
        int false_terms = 0;
        for(Term term: expr.getTerms()){
            term.calTermValue(individual);
            if(!term.getTermValue()) {
                false_terms++;
            }
        }
        // Calculate fitness
        double fitness = (double) false_terms / (total-1);
        // Store fitness
        individual.setFitness(fitness);
        System.out.println("false_term:"+false_terms+" total-1:"+(total-1));
        return fitness;
    }


    public void evalPopulation(Population population) {
        double populationFitness = 0;
        for (Individual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        population.setPopulationFitness(populationFitness);
    }


    public boolean isTerminationConditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 1) {
                return true;
            }
        }

        return false;
    }


    public Individual selectParent(Population population) {
        // Get individuals
        Individual individuals[] = population.getIndividuals();
        // Spin roulette wheel
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        // Find parent
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];
    }


    public Population crossoverPopulation(Population population) {
        // Create new population
        Population newPopulation = new Population(population.size());

        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);

            // Apply crossover to this individual?
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // Initialize offspring
                Individual offspring = new Individual(parent1.getChromosome(),expr.getTerms().get(index));
                // Find second parent
                Individual parent2 = selectParent(population);
                // Loop over genome
                for (String gene:expr.getLiterals().keySet()) {
                    // Use half of parent1's genes and half of parent2's genes
                    if (0.5 > Math.random()) {
                        offspring.setGene(gene, parent1.getGene(gene));
                    } else {
                        offspring.setGene(gene, parent2.getGene(gene));
                    }
                }
                // Add offspring to new population
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                // Add individual to new population without applying crossover
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }

        return newPopulation;
    }


    public Population mutatePopulation(Population population) {
        // Initialize new population
        Population newPopulation = new Population(this.populationSize);
        // Loop over current population by fitness
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);
            // Loop over individual's genes
            for (String geneIndex:expr.getLiterals().keySet()) {
                // Skip mutation if this is an elite individual
                if (populationIndex > this.elitismCount) {
                    // Does this gene need mutation?
                    if (this.mutationRate > Math.random()) {
                        // Get new gene
                        boolean newGene = false;
                        if (!individual.getGene(geneIndex)) {
                            newGene = true;
                        }
                        // Mutate gene
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }

            // Add individual to population
            newPopulation.setIndividual(populationIndex, individual);
        }

        // Return mutated population
        return newPopulation;
    }

}
