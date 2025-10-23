#ifndef MATRIX_MULTIPLY_H
#define MATRIX_MULTIPLY_H

double** allocate_matrix(int n);
void free_matrix(double** matrix, int n);
void initialize_matrix(double** matrix, int n);
void multiply_matrices(double** a, double** b, double** c, int n);

#endif