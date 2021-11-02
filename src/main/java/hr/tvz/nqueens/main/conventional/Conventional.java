package hr.tvz.nqueens.main.conventional;

import java.util.ArrayList;

public class Conventional {

    private static final int numberOfQueens = 11;

    public static void main(String[] args) {

        NQueenBacktrack nQueenBacktrack = new NQueenBacktrack(numberOfQueens);

        long startTime = System.nanoTime();

        nQueenBacktrack.solveNQ();

        long timeElapsed = System.nanoTime() - startTime;

        System.out.println((timeElapsed / 1000000) + "ms");

    }

}
