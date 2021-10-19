package ca.cmpt276.as3.model;

/*
    Class to store player's settings for the game
    Stores the dimensions of the grid and number of mines
    Singleton class
 */

public class OptionsData {

    private int rows;
    private int columns;
    private int numOfMines;

    private static OptionsData instance;

    private OptionsData(){
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
