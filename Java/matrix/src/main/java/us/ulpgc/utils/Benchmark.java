package us.ulpgc.utils;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class Benchmark {
    
    public void run(int matrixSize, int numRuns) {
        System.out.println("Run,Time(s),CPU(%),Memory(MB)");

        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    
        
        for (int run = 0; run < numRuns; run++) {
            double[][] a = MatrixMultiply.allocateMatrix(matrixSize);
            double[][] b = MatrixMultiply.allocateMatrix(matrixSize);
            double[][] c = MatrixMultiply.allocateMatrix(matrixSize);
            
            MatrixMultiply.initializeMatrix(a, matrixSize);
            MatrixMultiply.initializeMatrix(b, matrixSize);
            
            if (run == 0) {
                MatrixMultiply.multiplyMatrices(a, b, c, matrixSize);
            }
            
            long startTime = System.nanoTime();
            long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            double cpuBefore = osBean.getProcessCpuLoad();
            MatrixMultiply.multiplyMatrices(a, b, c, matrixSize);
            long endTime = System.nanoTime();
            long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            double cpuAfter = osBean.getProcessCpuLoad();
            
            double elapsedTime = (endTime - startTime) / 1e9;
            double avgCpu = ((cpuBefore + cpuAfter) / 2.0) * 100;
            double memUsedMB = (memAfter - memBefore) / (1024.0 * 1024.0);
            System.out.printf("%d,%.6f,%.2f,%.2f%n", run + 1, elapsedTime, avgCpu, memUsedMB);
        }
    }
}

