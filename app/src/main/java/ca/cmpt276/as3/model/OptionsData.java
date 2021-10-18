package ca.cmpt276.as3.model;

import ca.cmpt276.as3.Options;

public class OptionsData {
    private int rows;
    private int columns;
    private int numOfMines;

    private static OptionsData instance;

    private OptionsData(){
        this.rows = 4;
        this.columns = 6;
        this.numOfMines = 6;
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
}
