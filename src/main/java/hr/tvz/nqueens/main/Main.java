package hr.tvz.nqueens.main;

import java.util.Random;

public class Main {
    static final Random rand = new Random();

    public static void main(String[] args) {
        int N = 4;

        boolean board [][] = new boolean[N][N];

        int numOfIterations = 0;

        while(true) {
            boolean flag = false;
            numOfIterations++;
            board = clear(board, N);
            board = setBoard(board, N);
            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    if(board[i][j]) {
                        if(up(i,j,board,N) || down(i,j,board,N) || left(i,j,board,N) || right(i,j,board,N)) {
                            flag = true;
                        }
                    }
                }
            }
            if(flag) {
                System.out.println("Number of iterations " + numOfIterations);
                printPositions(board, N);
                continue;
            }
            break;
        }
        System.out.println("Final number of iterations " + numOfIterations);
        printPositions(board, N);
    }

    private static boolean up(int x, int y, boolean[][] board, int N) {
        for(int i = x-1; i >= 0; --i) {
            if(board[i][y]) {
                return true;
            }
        }
        return false;
    }

    private static boolean down(int x, int y, boolean[][] board, int N) {
        for(int i = x+1; i < N; ++i) {
            if(board[i][y]) {
                return true;
            }
        }
        return false;
    }

    private static boolean right(int x, int y, boolean[][] board, int N) {
        for(int i = y+1; i < N; ++i) {
            if(board[x][i]) {
                return true;
            }
        }
        return false;
    }

    private static boolean left(int x, int y, boolean[][] board, int N) {
        for(int i = y-1; i >= 0; --i) {
            if(board[x][i]) {
                return true;
            }
        }
        return false;
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
