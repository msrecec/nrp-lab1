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
//        this.conflicts = numberOfConflicts(gene, MAX_LENGTH);
        String board[][] = new String[MAX_LENGTH][MAX_LENGTH]; //initialize board
        int x = 0; //row
        int y = 0; //column
        int tempx = 0; //temprow
        int tempy = 0; //temcolumn

        int dx[] = new int[] {-1, 1, -1, 1}; //to check for diagonal
        int dy[] = new int[] {-1, 1, 1, -1}; //paired with dx to check for diagonal

        boolean done = false; //used to check is checking fo diagonal is out of bounds
        int conflicts = 0; //number of conflicts found

        clearBoard(board); //clears the board into empty strings
        plotQueens(board); // plots the Q in the board

        // Walk through each of the Queens and compute the number of conflicts.
        for(int i = 0; i < MAX_LENGTH; i++) {
            x = i;
            y = gene[i];

            // Check diagonals.
            for(int j = 0; j < 4; j++) { // because of dx and dy where there are 4 directions for diagonal searching for conflicts
                tempx = x;
                tempy = y; // store coordinate in temp
                done = false;

                while(!done) {//traverse the diagonals
                    tempx += dx[j];
                    tempy += dy[j];

                    if((tempx < 0 || tempx >= MAX_LENGTH) || (tempy < 0 || tempy >= MAX_LENGTH)) { //if exceeds board
                        done = true;
                    } else {
                        if(board[tempx][tempy].equals("Q")) {
                            conflicts++;
                        }
                    }
                }
            }
        }

        this.conflicts = conflicts; //set conflicts of this chromosome
    }

    /* Plots the queens in the board.
     *
     * @param: a nxn board
     */
    public void plotQueens(String[][] board) {
        for(int i = 0; i < MAX_LENGTH; i++) {
            board[i][gene[i]] = "Q";
        }
    }

    /* Clears the board.
     *
     * @param: a nxn board
     */
    public void clearBoard(String[][] board) {
        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int j = 0; j < MAX_LENGTH; j++) {
                board[i][j] = "";
            }
        }
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
