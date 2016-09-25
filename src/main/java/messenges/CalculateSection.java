package messenges;

public class CalculateSection {
    private final int[] row;
    private final int[] column;
    private final int rowNumber;
    private final int columnNumber;

    public CalculateSection(int rowNumber, int columnNumber, int[] row, int[] column) {
        this.row = row;
        this.column = column;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public int[] getRow() {
        return row;
    }

    public int[] getColumn() {
        return column;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
