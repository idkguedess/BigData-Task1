package us.ulpgc.utils;

public class Benchmark {
    
    public void run(int matrixSize, int numRuns) {
        System.out.println("Run,Time(s)");
        
        for (int run = 0; run < numRuns; run++) {
            double[][] a = MatrixMultiply.allocateMatrix(matrixSize);
            double[][] b = MatrixMultiply.allocateMatrix(matrixSize);
            double[][] c = MatrixMultiply.allocateMatrix(matrixSize);
            
            MatrixMultiply.initializeMatrix(a, matrixSize);
            MatrixMultiply.initializeMatrix(b, matrixSize);
            
            if (run == 0) {
                MatrixMultiply.multiplyMatrices(a, b, c, matrixSize);
            }
            
            long start = System.nanoTime();
            MatrixMultiply.multiplyMatrices(a, b, c, matrixSize);
            long end = System.nanoTime();
            
            double timeSeconds = (end - start) / 1e9;
            System.out.printf("%d,%.6f%n", run + 1, timeSeconds);
        }
    }
}

