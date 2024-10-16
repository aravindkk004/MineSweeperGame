public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private boolean isFlagged;
    private int cellNumber;
    private int adjacent;

    //constructor for this class
    public Cell(int cellNo) {
        this.isMine = false;
        this.isRevealed = false;
        this.isFlagged = false;
        this.cellNumber = cellNo;
        this.adjacent = 0;
    }

    //checking the cell contains mine or not
    public boolean isMine() {
        return this.isMine;
    }

    //putting mine to this cell
    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    //checking if the cell is revealed or not
    public boolean isRevealed() {
        return this.isRevealed;
    }

    //reveal this cell to the user
    public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }

    //getting adjacent mine for this cell
    public int getAdjacentMines() {
        return this.adjacent;
    }

    public void setAdjacent(int adjacents) {
        this.adjacent = adjacents;
    }

    //checking whether this cell is flagged or not
    public boolean isFlagged() {
        return this.isFlagged;
    }

    //putting flag to this cell
    public void setFlag() {
        this.isFlagged = true;
    }

    //removing flag to this cell
    public void removeFlag() {
        this.isFlagged = false;
    }

    //get the cell number
    public int getCellNumber() {
        return this.cellNumber;
    }
}
