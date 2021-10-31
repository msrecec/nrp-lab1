package hr.tvz.nqueens.main;

import io.jenetics.Gene;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int N = 4;

        int board [] = new int[N];

        board = setUpConventional(board, N);

        board = conventional(board, N);

        printPositions(board, N);

//        int board[] = {2, 0, 3, 1};
//
//        System.out.println(attackLeft(board, 4));
//        System.out.println(attackRight(board, 4));
//        System.out.println(attackDiagonalLeft(board, 4));
//        System.out.println(attackDiagonalRight(board, 4));
//
//        if(!attackAllPositions(board, N)) {
//            printPositions(board, N);
//        }

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
            if(!attackAllPositions(board, N)) break;
        }
        return board;
    }

    private static boolean attackAllPositions(int[] board, int N) {
        return
                attackLeft(board, N) ||
                attackRight(board, N) ||
                attackDiagonalLeft(board, N) ||
                attackDiagonalRight(board, N);
    }


    private static boolean attackLeft(int[] board, int N) {
        for(int i = N-1; i >= 0; --i) {
            for(int j = i-1; j >= 0; --j) {
                if(board[i] == board[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean attackRight(int[] board, int N) {
        for(int i = 0; i < N; ++i) {
            for(int j = i+1; j < N; ++j) {
                if(board[i] == board[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean attackDiagonalRight(int[] board, int N) {
        for(int i = 0; i < N; ++i) {
            for(int j = i+1; j < N; ++j) {
                if(Math.abs(board[i] - board[j]) == Math.abs(j-i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean attackDiagonalLeft(int[] board, int N) {
        for(int i = N-1; i >= 0; --i) {
            for(int j = i-1; j >= 0; --j) {
                if(Math.abs(board[i] - board[j]) == Math.abs(i-j)) {
                    return true;
                }
            }
        }
        return false;
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
