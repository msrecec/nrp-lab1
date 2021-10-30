package hr.tvz.nqueens.main;

import java.util.Random;

public class Main {
    static final Random rand = new Random();

    public static void main(String[] args) {
        int N = 4;


        boolean board [][] = new boolean[N][N];

        board = clear(board, N);

        board = setBoard(board, N);

        printPositions(board, N);
    }

    private static boolean [][] setBoard(boolean [][] board, int N) {
        int buff = N;
        while(true) {
            int x = rand.nextInt(N);
            int y = rand.nextInt(N);
            if(!board[x][y]) {
                board[x][y] = true;
                buff--;
            }

            if(buff == 0) break;
        }
        return board;
    }

    private static boolean [][] clear(boolean [][] board, int N) {
        int count = N;

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                board[i][j] = false;
            }
        }

        return board;
    }

    private static void printPositions(boolean[][] board, int N) {
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                if(board[i][j]) {
                    System.out.print("Q");
                } else {
                    System.out.print("X");
                }
            }
            System.out.print("\n");
        }
    }
}
