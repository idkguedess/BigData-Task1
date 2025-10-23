import random
from typing import List

def allocate_matrix(n: int) -> List[List[int]]:
    """Allocate an n x n matrix initialized with zeros."""
    return [[0 for _ in range(n)] for _ in range(n)]

def initialize_matrix(matrix: List[List[int]], n: int, seed: int = 42) -> None:
    """Initialize matrix with random values."""
    random.seed(seed)
    for i in range(n):
        for j in range(n):
            matrix[i][j] = random.randint(0, 100)

def multiply_matrices(a: List[List[int]], 
                     b: List[List[int]], 
                     c: List[List[int]], 
                     n: int) -> None:
    for i in range(n):
        for j in range(n):
            c[i][j] = 0
            for k in range(n):
                c[i][j] += a[i][k] * b[k][j]