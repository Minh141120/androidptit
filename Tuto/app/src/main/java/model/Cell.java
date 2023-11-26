package model;

import java.io.Serializable;

public class Cell implements Serializable{

    private static final long serialVersionUID = 6L;
    private int colIndex;
    private int rowIndex;
    private int value;

    public Cell() {

    }

    public Cell(int rowIndex, int colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public Cell(int rowIndex, int colIndex, int value) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.value = value;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return rowIndex + ":" + colIndex;
    }

}

