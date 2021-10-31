package hr.tvz.nqueens.main;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Crossover;
import io.jenetics.Genotype;
import io.jenetics.internal.math.Combinatorics;
import io.jenetics.util.Factory;
import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int N = 4;

//        int board [] = new int[N];
//
//        board = conventional(board, N);
//
//        printPositions(board, N);
//
//        System.out.println(getFitness(board, N));


    }

    /**
     * Returns newly generated population
     *
     * @param size number of chromosomes in the population
     * @param N size of the chromosome
     * @return population
     */

    private static List<int[]> generatePopulation(int size, int N) {
        List<int[]> population = new ArrayList<>();
        for(int i = 0; i < size; ++i) {
            population.add(setUpConventional(new int[N], N));
        }
        return population;
    }

    /**
     * Returns the fitness function with N choose 2 - number of conflicts as the value of fitness function
     *
     * @param board
     * @param N
     * @return
     */

    private static double getFitness(int[] board, int N) {
        return maxClashes(N) - numberOfConflicts(board, N);
    }

    private static void genetic(int[] board, int N) {

    }

    private static int maxClashes(int N) {
        return (int)CombinatoricsUtils.binomialCoefficient(N, 2);
    }

    private static int [] setUpConventional(int [] board, int N) {
        Random random = new Random();

        for(int i = 0; i < N; ++i) {
            board[i] = random.nextInt(N);
        }

        return board;
    }

    private static int [] conventional(int[] board, int N) {
        while(true) {
            board = setUpConventional(board, N);
            if(numberOfConflicts(board, N) == 0) break;
        }
        return board;
    }

    /**
     * Returns total number of clashes
     *
     * @param board
     * @param N
     * @return
     */

    private static int numberOfConflicts(int[] board, int N) {
        return horizontal(board, N) + diagonal(board, N);
    }


    private static int horizontal(int[] board, int N) {
        int buff = 0;
        for(int i = N-1; i >= 0; --i) {
            for(int j = i-1; j >= 0; --j) {
                if(board[i] == board[j]) {
                    buff++;
                }
            }
        }
        return buff;
    }

    private static int diagonal(int[] board, int N) {
        int buff = 0;
        for(int i = 0; i < N; ++i) {
            for(int j = i+1; j < N; ++j) {
                if(Math.abs(board[i] - board[j]) == Math.abs(j-i)) {
                    buff++;
                }
            }
        }
        return buff;
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
