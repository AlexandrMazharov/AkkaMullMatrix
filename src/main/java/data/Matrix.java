package data;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Matrix {
    private final int[][] matrix;
    private final int numberOfColumns;
    private final int numberOfRows;

    public Matrix(int numberOfRows, int numberOfColumns, int[][] matrix) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.matrix = matrix;
    }

    public int getElement(int i, int j) { //todo: outofbound exception
        return matrix[i][j];
    }

    public int[] getColumn(int columnNumber) { //todo: outofbound exception
        return IntStream.range(0, numberOfRows).map(row -> matrix[row][columnNumber]).toArray();
    }

    public int[] getRow(int rowNumber) { //todo: outofbound exception
        return matrix[rowNumber];
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    @Override
    public String toString() {
        return "matrix=" + Arrays.deepToString(matrix); //todo
    }
}
