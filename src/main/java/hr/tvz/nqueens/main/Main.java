package hr.tvz.nqueens.main;

import hr.tvz.nqueens.main.entity.QueenFitnessFunction;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int NUMBER_OF_EVOLUTIONS = 500000;
    private static final int NUMBER_OF_QUEENS = 8;
    private static final int POPUL = 200;

    public static void main(String[] args) throws InvalidConfigurationException {
        int evos = 0;
        Configuration conf = new DefaultConfiguration();
        FitnessFunction myFunc = new QueenFitnessFunction(NUMBER_OF_QUEENS);
        conf.setFitnessFunction(myFunc);
        conf.setKeepPopulationSizeConstant(true);
        conf.setPreservFittestIndividual(true);

        Gene[] sampleGenes = new Gene[NUMBER_OF_QUEENS];

        for (int i = 0; i < NUMBER_OF_QUEENS; i++) {
            sampleGenes[i] = new IntegerGene(conf, 0, NUMBER_OF_QUEENS - 1);
        }

        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes);

        conf.setSampleChromosome(sampleChromosome);
        conf.setPopulationSize(POPUL);
        Genotype population = Genotype.randomInitialGenotype(conf);

        Chromosome theBest = (Chromosome) population.getFittestChromosome();
        ArrayList<Integer> geneList = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i = 0; i < NUMBER_OF_EVOLUTIONS; i++) {

            if (((Chromosome) population.getFittestChromosome())
                    .getFitnessValue() == (NUMBER_OF_QUEENS * (NUMBER_OF_QUEENS - 1) * 0.5))
                break;

            population.evolve();
            evos++;
        }
        long timeElapsed = System.nanoTime() - startTime;
        System.out.println("Best solution: ");
        theBest = (Chromosome) population.getFittestChromosome();

        geneList.clear();

        for (int j = 0; j < NUMBER_OF_QUEENS; j++) {
            geneList.add((Integer) theBest.getGenes()[j].getAllele());
        }
        int[] board = {};

        for(int i = 0; i < NUMBER_OF_QUEENS; ++i) {
            board = ArrayUtils.add(board, (int)theBest.getGenes()[i].getAllele());
        }

        printPositions(board, NUMBER_OF_QUEENS);

        System.out.println("Best fitness " + theBest.getFitnessValue());
        System.out.println("Number of evolutions " + evos);
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
