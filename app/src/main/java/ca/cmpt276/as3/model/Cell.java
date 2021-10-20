package ca.cmpt276.as3.model;

/*
    Class representing one cell of the game grid.
 */

public class Cell {
    private final boolean hasMine;
    private boolean mineRevealed;
    private boolean isScanned;
    private int nearbyHidden;

    public Cell(boolean hasMine) {
        this.hasMine = hasMine;
        this.mineRevealed = false;
        this.isScanned = false;
    }

    public boolean hasMine() {
        return hasMine;
    }

    public boolean isMineRevealed() {
        return mineRevealed;
    }

    public boolean isScanned() {
        return isScanned;
    }

    public int getNearbyHidden() {
        return nearbyHidden;
    }

    public void setMineRevealed(boolean mineRevealed) {
        this.mineRevealed = mineRevealed;
    }

    public void setScanned(boolean scanned) {
        isScanned = scanned;
    }

    public void setNearbyHidden(int nearbyHidden) {
        this.nearbyHidden = nearbyHidden;
    }
}
