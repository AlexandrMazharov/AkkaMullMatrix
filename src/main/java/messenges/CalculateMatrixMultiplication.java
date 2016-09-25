package messenges;

import data.Matrix;

public class CalculateMatrixMultiplication {
    private final Matrix firstMatrix;
    private final Matrix secondMatrix;

    public CalculateMatrixMultiplication(Matrix firstMatrix, Matrix secondMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
    }

    public Matrix getFirstMatrix() {
        return firstMatrix;
    }

    public Matrix getSecondMatrix() {
        return secondMatrix;
    }
}
