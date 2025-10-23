import time
from .matrix_multiply import allocate_matrix, initialize_matrix, multiply_matrices

def run_benchmark(n: int, num_runs: int):
    """Run matrix multiplication benchmark."""
    print(f"Matrix Size: {n}, Runs: {num_runs}")
    print("Run,Time(s)")
    
    for run in range(num_runs):
        a = allocate_matrix(n)
        b = allocate_matrix(n)
        c = allocate_matrix(n)
        
        initialize_matrix(a, n)
        initialize_matrix(b, n)
        
        start = time.perf_counter()
        multiply_matrices(a, b, c, n)
        end = time.perf_counter()
        
        print(f"{run + 1},{end - start:.6f}")