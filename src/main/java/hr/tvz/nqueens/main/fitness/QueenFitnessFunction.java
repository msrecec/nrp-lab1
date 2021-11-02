package hr.tvz.nqueens.main.fitness;

import org.jgap.Chromosome;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

import java.util.ArrayList;

public class QueenFitnessFunction extends FitnessFunction {

    private static final long serialVersionUID = -5534696812461294170L;
    public static int numberOfQueens;

    public QueenFitnessFunction(int number) {
        numberOfQueens = number;
    }

    public double evaluate(IChromosome chromosome) {
        double fitness = numberOfQueens * (numberOfQueens - 1) / 2;
        int fi;
        int fj;
        ArrayList<Integer> geneList = getGenes((Chromosome) chromosome);
        for (int i = 0; i < numberOfQueens - 1; i++) {
            fi = geneList.get(i);
            for (int j = i + 1; j < numberOfQueens; j++) {
                fj = geneList.get(j);
                if (fi == fj || Math.abs(i - j) == Math.abs(fi - fj))
                    fitness--;
            }
        }
        return fitness;
    }

    public static ArrayList<Integer> getGenes(Chromosome chromosome) {
        ArrayList<Integer> geneList = new ArrayList<Integer>();
        for (int i = 0; i < numberOfQueens; i++) {
            geneList.add(getGeneAt(i, chromosome));
        }
        return geneList;
    }

    public static Integer getGeneAt(int i, Chromosome chromosome) {
        return (Integer) chromosome.getGene(i).getAllele();
    }

}
