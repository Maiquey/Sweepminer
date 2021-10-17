package ca.cmpt276.as3.model;

public class Cell {
    private boolean hasMine;
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
