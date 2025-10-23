import pytest
from src.matrix_multiply import allocate_matrix, initialize_matrix, multiply_matrices

@pytest.fixture
def matrices():
    n = 128
    a = allocate_matrix(n)
    b = allocate_matrix(n)
    c = allocate_matrix(n)
    initialize_matrix(a, n)
    initialize_matrix(b, n)
    return a, b, c, n

def test_matrix_multiply_benchmark(benchmark, matrices):
    a, b, c, n = matrices
    def run():
        multiply_matrices(a, b, c, n)
    benchmark(run)
