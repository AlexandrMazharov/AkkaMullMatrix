package messenges;

public class Calculated {
    private final int result;
    private final int rowNumber;
    private final int columnNumber;

    public Calculated(int rowNumber, int columnNumber, int result) {
        this.result = result;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public int getResult() {
        return result;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }
}
