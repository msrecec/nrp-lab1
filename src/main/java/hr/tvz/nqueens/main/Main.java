package hr.tvz.nqueens.main;

import hr.tvz.nqueens.main.entity.QueenFitnessFunction;
import org.apache.commons.lang.ArrayUtils;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import java.util.ArrayList;

public class Main {
    private static final int NUMBER_OF_EVOLUTIONS = 500000;
    private static final int NUMBER_OF_QUEENS = 8;
    private static final int POPULATION = 200;

    public static void main(String[] args) throws InvalidConfigurationException {
        int evolutions = 0;
        Configuration conf = new DefaultConfiguration();
        FitnessFunction myFunc = new QueenFitnessFunction(NUMBER_OF_QUEENS);
        conf.setFitnessFunction(myFunc);
        conf.setKeepPopulationSizeConstant(true);
        conf.setPreservFittestIndividual(true);

        Gene[] genes = new Gene[NUMBER_OF_QUEENS];

        for (int i = 0; i < NUMBER_OF_QUEENS; i++) {
            genes[i] = new IntegerGene(conf, 0, NUMBER_OF_QUEENS - 1);
        }

        Chromosome chromosome = new Chromosome(conf, genes);

        conf.setSampleChromosome(chromosome);
        conf.setPopulationSize(POPULATION);
        Genotype population = Genotype.randomInitialGenotype(conf);

        Chromosome bestChromosome;
        ArrayList<Integer> geneList = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i = 0; i < NUMBER_OF_EVOLUTIONS; i++) {

            if (population.getFittestChromosome()
                    .getFitnessValue() == (NUMBER_OF_QUEENS * (NUMBER_OF_QUEENS - 1) / 2))
                break;

            population.evolve();
            evolutions++;
        }
        long timeElapsed = System.nanoTime() - startTime;

        System.out.println("Best solution: ");
        bestChromosome = (Chromosome) population.getFittestChromosome();

        geneList.clear();

        for (int j = 0; j < NUMBER_OF_QUEENS; j++) {
            geneList.add((Integer) bestChromosome.getGenes()[j].getAllele());
        }
        int[] board = {};

        for(int i = 0; i < NUMBER_OF_QUEENS; ++i) {
            board = ArrayUtils.add(board, (int)bestChromosome.getGenes()[i].getAllele());
        }

        printPositions(board, NUMBER_OF_QUEENS);

        System.out.println("Best fitness " + bestChromosome.getFitnessValue());
        System.out.println("Number of evolutions " + evolutions);
        System.out.println((timeElapsed / 1000000) + "ms");

    }

    private static void printPositions(int[] board, int N) {
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(board[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print("X");
                }
            }
            System.out.print("\n");
        }
    }

}
