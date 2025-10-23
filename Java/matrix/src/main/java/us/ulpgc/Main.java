package us.ulpgc;

import us.ulpgc.utils.Benchmark;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Main <matrix_size> <num_runs>");
            System.exit(1);
        }
        
        int matrixSize = Integer.parseInt(args[0]);
        int numRuns = Integer.parseInt(args[1]);
        
        Benchmark benchmark = new Benchmark();
        benchmark.run(matrixSize, numRuns);
    }
}