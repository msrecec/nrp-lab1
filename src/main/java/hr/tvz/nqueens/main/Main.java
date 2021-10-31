package hr.tvz.nqueens.main;

import io.jenetics.Gene;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int N = 5;

        int board [] = new int[N];

        board = conventional(board, N);

        printPositions(board, N);

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
            if(attackAllPositions(board, N) == 0) break;
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

    private static int attackAllPositions(int[] board, int N) {
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
