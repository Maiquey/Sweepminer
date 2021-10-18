package ca.cmpt276.as3.model;

import ca.cmpt276.as3.Options;

public class OptionsData {

    private static final int DEFAULT_ROWS = 4;
    private static final int DEFAULT_COLS = 6;
    private static final int DEFAULT_NUM_MINES = 6;
    private int rows;
    private int columns;
    private int numOfMines;

    private static OptionsData instance;

    private OptionsData(){
        //this.rows = DEFAULT_ROWS;
        //this.columns = DEFAULT_COLS;
        //this.numOfMines = DEFAULT_NUM_MINES;
    }

    public static  OptionsData getInstance(){
        if (instance == null){
            instance = new OptionsData();
        }
        return instance;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getNumOfMines() {
        return numOfMines;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setNumOfMines(int numOfMines) {
        this.numOfMines = numOfMines;
    }
}
