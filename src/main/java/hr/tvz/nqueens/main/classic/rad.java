package hr.tvz.nqueens.main.classic;

import java.util.ArrayList;

public class rad {

    private static final int numberOfQueens = 11;

    public static void main(String[] args) {

        ArrayList<Integer> geneList = new ArrayList<Integer>();
        for (int i = 0; i < numberOfQueens; i++) {
            geneList.add(0);
        }
        boolean ok = false;

        long startTime = System.nanoTime();
        while (true) {
            ok = true;
            if (check(geneList) == 0)
                break;
            for (int i = 0; i < numberOfQueens; i++) {
                if (geneList.get(i) != 3)
                    ok = false;
            }
            if (ok)
                break;
            geneList.set(numberOfQueens - 1, geneList.get(numberOfQueens - 1) + 1);
            for (int i = numberOfQueens - 1; i >= 0; i--) {
                if (geneList.get(0) >= numberOfQueens) {
                    ok = true;
                    break;
                }
                if (geneList.get(i) >= numberOfQueens) {
                    geneList.set(i, 0);
                    geneList.set(i - 1, geneList.get(i - 1) + 1);
                }
            }
        }

        long timeElapsed = System.nanoTime() - startTime;
        for (int j = 0; j < numberOfQueens; j++) {
            System.out.println(geneList.get(j).toString());
        }

        System.out.println(check(geneList));
        System.out.println((timeElapsed / 1000000) + "ms");

    }

    public static int check(ArrayList<Integer> lista) {
        int fitness = 0;
        int temp = 0;
        int temp2 = 0;
        for (int i = 0; i < numberOfQueens - 1; i++) {
            temp = lista.get(i);
            for (int j = i + 1; j < numberOfQueens; j++) {
                temp2 = lista.get(j);
                if (temp == temp2 || (temp2 + i - j) == temp || (temp2 + j - i) == temp)
                    fitness++;
            }
        }
        return fitness;
    }

}
