package ca.cmpt276.as3.model;

import java.util.ArrayList;
import java.util.Collections;

public class GameManager {
    private ArrayList<Cell> initList;
    private Cell cellGrid[][];
    private int totalMines;
    private int numOfMinesRevealed;
    private int gridRows;
    private int gridCols;
    private int numOfScans;

    public GameManager(int numRows, int numCols, int numOfMines) {
        this.totalMines = numOfMines;
        this.numOfMinesRevealed = 0;
        this.numOfScans = 0;
        this.cellGrid = new Cell[numRows][numCols];
        this.gridRows = numRows;
        this.gridCols = numCols;

        //populate cellGrid
        this.initList = new ArrayList<Cell>();
        int minesInserted = 0;
        for (int i = 0; i < (numRows * numCols); i++){
            if (minesInserted < totalMines){
                initList.add(new Cell(true));
                minesInserted++;
            }
            else{
                initList.add(new Cell(false));
            }
        }

        Collections.shuffle(initList);

        int initIndex = 0;
        for (int row = 0; row < numRows; row++){
            for (int col = 0; col < numCols; col++){
                cellGrid[row][col] = initList.get(initIndex);
                initIndex++;
            }
        }
    }

    public void cellClicked(int col, int row){
        Cell cell = cellGrid[row][col];
        if (cell.hasMine() && !cell.isMineRevealed()){
            cell.setMineRevealed(true);
            numOfMinesRevealed++;
            updateScanners();
        }
        else{
            if (!cell.isScanned()){
                cell.setScanned(true);
                numOfScans++;
                updateNearbyMines(col, row);
            }
        }
    }

    private void updateScanners() {
        for (int row = 0; row < gridRows; row++){
            for (int col = 0; col < gridCols; col++){
                updateNearbyMines(col, row);
            }
        }
    }

    private void updateNearbyMines(int col, int row) {
        Cell cell;
        int mineCount = 0;
        for (int i = 0; i < gridCols; i++){
            cell = cellGrid[row][i];
            if (cell.hasMine() && !cell.isMineRevealed()){
                mineCount++;
            }
        }
        for (int i = 0; i < gridRows; i++){
            cell = cellGrid[i][col];
            if (cell.hasMine() && !cell.isMineRevealed()){
                mineCount++;
            }
        }
        cell = cellGrid[row][col];
        if (cell.isScanned()){
            cell.setNearbyHidden(mineCount);
        }
    }

    public Cell getCell(int row, int col){
        return cellGrid[row][col];
    }

    public void revealGrid(){
        for (int row = 0; row < gridRows; row++){
            for (int col = 0; col < gridCols; col++){
                cellGrid[row][col].setScanned(true);
            }
        }
    }

    public boolean gameWon(){
        return numOfMinesRevealed == totalMines;
    }

    public int getTotalMines() {
        return totalMines;
    }

    public int getNumOfMinesRevealed() {
        return numOfMinesRevealed;
    }

    public int getNumOfScans() {
        return numOfScans;
    }
}
