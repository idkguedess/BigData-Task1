#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include <sys/resource.h>
#include "matrix_multiply.h"

double get_time() {
    struct timeval tv;
    gettimeofday(&tv, NULL);
    return tv.tv_sec + tv.tv_usec * 1e-6;
}

double get_cpu_usage() {
    struct rusage usage;
    getrusage(RUSAGE_SELF, &usage);
    double user_time = usage.ru_utime.tv_sec + usage.ru_utime.tv_usec * 1e-6;
    double sys_time = usage.ru_stime.tv_sec + usage.ru_stime.tv_usec * 1e-6;
    return user_time + sys_time;
}

long get_memory_usage_kb() {
    struct rusage usage;
    getrusage(RUSAGE_SELF, &usage);
    return usage.ru_maxrss;
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
    printf("Run,Time(s),CPU(s),Memory(MB)\n");
    
    for (int run = 0; run < num_runs; run++) {
        double** a = allocate_matrix(n);
        double** b = allocate_matrix(n);
        double** c = allocate_matrix(n);
        
        initialize_matrix(a, n);
        initialize_matrix(b, n);
        
        double start_time = get_time();
        double start_cpu = get_cpu_usage();
        long mem_before = get_memory_usage_kb();
        multiply_matrices(a, b, c, n);
        double end_time = get_time();
        double end_cpu = get_cpu_usage();
        long mem_after = get_memory_usage_kb();

        double elapsed_time = end_time - start_time;
        double cpu_used = end_cpu - start_cpu;
        double mem_used_mb = (mem_after - mem_before) / 1024.0;

        printf("%d,%.6f,%.6f,%.2f\n", run + 1, elapsed_time, cpu_used, mem_used_mb);
        
        free_matrix(a, n);
        free_matrix(b, n);
        free_matrix(c, n);
    }
    
    return 0;
}