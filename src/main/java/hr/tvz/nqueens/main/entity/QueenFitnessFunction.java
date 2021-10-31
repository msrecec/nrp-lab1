package hr.tvz.nqueens.main.entity;

import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.ArrayList;

public class QueenFitnessFunction extends FitnessFunction {
    /**
     *
     */
    private static final long serialVersionUID = -5534696812461294170L;
    public static int numberOfQueens;

    public QueenFitnessFunction(int number) {
        numberOfQueens = number;
    }

    public double evaluate(IChromosome chrom) {
        double fitness = numberOfQueens * (numberOfQueens - 1) * (0.5);
        int temp = 0;
        int temp2 = 0;
        ArrayList<Integer> geneList = getGenes((Chromosome) chrom);
        for (int i = 0; i < numberOfQueens - 1; i++) {
            temp = geneList.get(i);
            for (int j = i + 1; j < numberOfQueens; j++) {
                temp2 = geneList.get(j);
                if (temp == temp2 || (temp2 + i - j) == temp || (temp2 + j - i) == temp)
                    fitness--;
            }
        }
        return fitness;
    }

    public static ArrayList<Integer> getGenes(Chromosome chrom) {
        ArrayList<Integer> geneList = new ArrayList<Integer>();
        for (int i = 0; i < numberOfQueens; i++) {
            geneList.add(getGeneAt(i, chrom));
        }
        return geneList;
    }

    public static Integer getGeneAt(int i, Chromosome chrom) {
        return (Integer) chrom.getGene(i).getAllele();
    }

    public double evaluate(Chromosome chrom) {
        double fitness = numberOfQueens * (numberOfQueens - 1) * (0.5);
        int temp = 0;
        int temp2 = 0;
        ArrayList<Integer> geneList = getGenes(chrom);
        for (int i = 0; i < numberOfQueens - 1; i++) {
            temp = geneList.get(i);
            for (int j = i + 1; j < numberOfQueens; j++) {
                temp2 = geneList.get(j);
                if (temp == temp2 || (temp2 + i - j) == temp || (temp2 + j - i) == temp)
                    fitness--;
            }
        }
        return fitness;
    }

}
