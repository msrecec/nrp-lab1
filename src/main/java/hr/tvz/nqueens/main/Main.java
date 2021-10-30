package hr.tvz.nqueens.main;

public class Main {
    public static void main(String[] args) {
        int N = 4;

        boolean board [][] = new boolean[N][N];

        board = populate(board, N);

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

    private static boolean [][] populate(boolean [][] board, int N) {
        int count = N;

        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < N; ++j) {
                board[i][j] = false;
            }
        }

        for(int i = 0; i < N; ++i) {
            if(count == 0) break;
            for(int j = 0; j < N; ++j) {
                board[i][j] = true;
                count--;
            }
        }

        return board;
    }
}
