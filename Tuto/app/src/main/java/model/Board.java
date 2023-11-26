package model;


import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable{

    private static final long serialVersionUID = 4L;
    public static int row = 10;
    public static int col = 10;
    private Cell[][] matrix;

    public static int getRow() {
        return row;
    }

    public static int getCol() {
        return col;
    }

    public Board() {
        this.matrix = new Cell[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                this.matrix[i][j] = new Cell(i, j, 0);
            }
        }
    }
    public Cell[][] getMatrix() {
        return this.matrix;
    }

    public void updateMatrix(ArrayList<Cell> aircraft_parts_coor) {
        for(Cell c : aircraft_parts_coor) {
            this.matrix[c.getRowIndex()][c.getColIndex()].setValue(c.getValue());
        }
    }

    public void printMatrix() {
        System.out.println("----------");
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                System.out.print(this.matrix[i][j].getValue() + " ");
            }
            System.out.println();
        }
    }
}
