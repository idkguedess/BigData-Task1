package us.ulpgc.utils;

public class MatrixMultiply {
    
    public static double[][] allocateMatrix(int n) {
        return new double[n][n];
    }
    
    public static void initializeMatrix(double[][] matrix, int n) {
        java.util.Random random = new java.util.Random(42);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextDouble();
            }
        }
    }
    
    public static void multiplyMatrices(double[][] a, double[][] b, 
                                       double[][] c, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0.0;
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
}
