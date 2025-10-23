import time
import psutil
from .matrix_multiply import allocate_matrix, initialize_matrix, multiply_matrices

def run_benchmark(n: int, num_runs: int):
    """Run matrix multiplication benchmark with CPU and memory metrics."""
    print("Run,Time(s),CPU(%),Memory(MB)")
    
    process = psutil.Process()
    
    for run in range(num_runs):
        a = allocate_matrix(n)
        b = allocate_matrix(n)
        c = allocate_matrix(n)
        
        initialize_matrix(a, n)
        initialize_matrix(b, n)
        
        cpu_start = psutil.cpu_percent(interval=None)
        mem_before = process.memory_info().rss / (1024 * 1024)
        
        start = time.perf_counter()
        multiply_matrices(a, b, c, n)
        end = time.perf_counter()
        
        cpu_end = psutil.cpu_percent(interval=None)
        mem_after = process.memory_info().rss / (1024 * 1024)
        
        elapsed = end - start
        avg_cpu = (cpu_start + cpu_end) / 2
        mem_used = mem_after - mem_before
        
        print(f"{run + 1},{elapsed:.6f},{avg_cpu:.2f},{mem_used:.2f}")
