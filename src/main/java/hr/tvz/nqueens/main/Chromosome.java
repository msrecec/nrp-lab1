package hr.tvz.nqueens.main;

import org.apache.commons.math3.util.CombinatoricsUtils;

import java.util.Comparator;

public class Chromosome implements Comparable<Chromosome> {

    private int MAX_LENGTH; // size of the gene
    private int[] gene; // location of each queen
    private double fitness; // the fitness of this chromosome towards the solution
    private int conflicts; // number of collisions
    private boolean selected; // if selected for mating
    private double selectionProbability; // probability of being selected for mating in roulette

    public Chromosome(int n) {
        MAX_LENGTH = n;
        gene = new int[MAX_LENGTH];
        fitness = 0.0;
        conflicts = 0;
        selected = false;
        selectionProbability = 0.0;

        initChromosome();
    }

    /* Initializes the chromosome into diagonal queens.
     *
     */
    public void initChromosome() {
        for(int i = 0; i < MAX_LENGTH; i++) {
            gene[i] = i;
        }
    }

    @Override
    public int compareTo(Chromosome c) {
        return this.conflicts - c.getConflicts();
    }

    public void computeConflicts() {
        this.conflicts = numberOfConflicts(gene, MAX_LENGTH);
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


    public void printPositions() {
        for(int i = 0; i < MAX_LENGTH; ++i) {
            for(int j = 0; j < MAX_LENGTH; ++j) {
                if(gene[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print("X");
                }
            }
            System.out.print("\n");
        }
    }








    public int getMAX_LENGTH() {
        return MAX_LENGTH;
    }

    public void setMAX_LENGTH(int MAX_LENGTH) {
        this.MAX_LENGTH = MAX_LENGTH;
    }

    public int getGene(int index) {
        return gene[index];
    }

    public void setGene(int index, int position) {
        this.gene[index] = position;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getConflicts() {
        return conflicts;
    }

    public void setConflicts(int conflicts) {
        this.conflicts = conflicts;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getSelectionProbability() {
        return selectionProbability;
    }

    public void setSelectionProbability(double selectionProbability) {
        this.selectionProbability = selectionProbability;
    }
}
