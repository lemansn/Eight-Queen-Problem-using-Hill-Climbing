/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nqueens;

/**
 *
 * @author leman
 */
public class NQueen {
    private int row;
    private int column;

    public NQueen(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move () {
        row++;
    }

    public boolean ifConflict(NQueen q){
        //  Check rows and columns
        if(row == q.getRow() || column == q.getColumn())
            return true;
            //  Check diagonals
        else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
            return true;
        return false;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
