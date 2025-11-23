package org.example.service;

import org.springframework.stereotype.Component;

@Component
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;
    private static final int MUTANT_THRESHOLD = 2;

    public boolean isMutant(String[] dna) {
        if (dna == null) {
            return false;
        }

        int n = dna.length;

        if (n == 0) {
            return false;
        }

        char[][] matrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            // Validación básica de estructura cuadrada
            if (dna[i].length() != n) {
                return false;
            }

            matrix[i] = dna[i].toCharArray();

            // Validación de caracteres permitidos
            for (char c : matrix[i]) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    return false; // Carácter inválido encontrado
                }
            }
        }

        int sequenceCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Horizontal
                if (j <= n - SEQUENCE_LENGTH) {
                    if (checkSequence(matrix, i, j, 0, 1)) {
                        sequenceCount++;
                        if (sequenceCount >= MUTANT_THRESHOLD) return true;
                    }
                }

                // Vertical
                if (i <= n - SEQUENCE_LENGTH) {
                    if (checkSequence(matrix, i, j, 1, 0)) {
                        sequenceCount++;
                        if (sequenceCount >= MUTANT_THRESHOLD) return true;
                    }
                }

                // Diagonal \
                if (i <= n - SEQUENCE_LENGTH && j <= n - SEQUENCE_LENGTH) {
                    if (checkSequence(matrix, i, j, 1, 1)) {
                        sequenceCount++;
                        if (sequenceCount >= MUTANT_THRESHOLD) return true;
                    }
                }

                // Diagonal /
                if (i <= n - SEQUENCE_LENGTH && j >= SEQUENCE_LENGTH - 1) {
                    if (checkSequence(matrix, i, j, 1, -1)) {
                        sequenceCount++;
                        if (sequenceCount >= MUTANT_THRESHOLD) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSequence(char[][] matrix, int row, int col, int dx, int dy) {
        char first = matrix[row][col];
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (matrix[row + i * dx][col + i * dy] != first) {
                return false;
            }
        }
        return true;
    }
}