public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int cellNumber;
    private int adjacent;

    public Cell(int cellNo) {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.cellNumber = cellNo;
        this.adjacent = 0;
    }

    public boolean isMine() {
        return this.isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isRevealed() {
        return this.isRevealed;
    }

    public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }

    public int getAdjacentMines() {
        return this.adjacent;
    }

    public void setAdjacent(int adjacents) {
        this.adjacent = adjacents;
    }

    public boolean isFlagged() {
        return this.isFlagged;
    }

    public void setFlag() {
        this.isFlagged = true;
    }

    public void removeFlag() {
        this.isFlagged = false;
    }

    public int getCellNumber() {
        return this.cellNumber;
    }
}
