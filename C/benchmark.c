#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include "matrix_multiply.h"

double get_time() {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return tv.tv_sec + tv.tv_usec * 1e-6;
}

int main(int argc, char* argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Usage: %s <matrix_size> <num_runs>\n", argv[0]);
        return 1;
    }

    int n = atoi(argv[1]);
    int num_runs = atoi(argv[2]);
    
    srand(42);
    
    printf("Matrix Size: %d, Runs: %d\n", n, num_runs);
    printf("Run,Time(s)\n");
    
    for (int run = 0; run < num_runs; run++) {
        double** a = allocate_matrix(n);
        double** b = allocate_matrix(n);
        double** c = allocate_matrix(n);
        
        initialize_matrix(a, n);
        initialize_matrix(b, n);
        
        double start = get_time();
        multiply_matrices(a, b, c, n);
        double end = get_time();
        
        printf("%d,%.6f\n", run + 1, end - start);
        
        free_matrix(a, n);
        free_matrix(b, n);
        free_matrix(c, n);
    }
    
    return 0;
}