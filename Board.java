import java.util.*;

public class Board {
    private Cell[][] cell;
    private int flagCount;

    public Board(int userInput) {
        cell = new Cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cell[i][j] = new Cell(i * 10 + j + 1);
            }
        }
        this.flagCount = 0;
        placeMines(userInput);
        calculateNeighbour();
    }

    //Placing Mines dynamically for every games 
    public void placeMines(int userInput) {
        Random rand = new Random();
        int minePlaced = 0;

        Set<Integer> surroundings = calculateSurroundings(userInput);
        while (minePlaced < userInput) {
            int index = rand.nextInt(100);
            int row = index / 10;
            int col = index % 10;
            if (!cell[row][col].isMine() && !surroundings.contains(index)) {
                cell[row][col].setMine(true);
                minePlaced++;
            }
        }
    }

    //calculating the surroundings according to the mines assigned previously
    public Set<Integer> calculateSurroundings(int userInput) {
        Set<Integer> restricted = new HashSet<>();
        int inputIndex = userInput - 1;
        int row = inputIndex / 10;
        int col = inputIndex % 10;
        for (int r = row - 2; r <= row + 2; r++) {
            for (int c = col - 2; c <= col + 2; c++) {
                if (r >= 0 && r < 10 && c >= 0 && c < 10) {
                    restricted.add(r * 10 + c);
                }
            }
        }
        return restricted;
    }

    public int getFlagCount(){
        return this.flagCount;
    }

    //print the whole board
    public void printBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Cell cell = this.cell[row][col];
                if (cell.isRevealed()) {
                    System.out.print(cell.isMine() ? "B " : cell.getAdjacentMines() + " ");
                } else if (cell.isFlagged()) {
                    System.out.print("F ");
                }
                else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    // if the user choose reveal cell then the cell will reveal whether it is mine or number
    public boolean revealCell(int row, int col) {
        Cell cells = cell[row][col];
        if (cells.isMine()) {
            return true;
        } else {
            cells.setRevealed(true);
            if (cells.getAdjacentMines()==0){
                checkSurroundings(row, col);
            }
            return false;
        }
    }

    //calculating neighbour to calculate the mine count for each cell
    public void calculateNeighbour() {
        for (int i = 0; i < 100; i++) {
            int row = i / 10;
            int col = i % 10;
            if (!cell[row][col].isMine()) {
                int mineCount = 0;
                List<Integer> neighbours = getNeighbours(i);
                for (int neighbour : neighbours) {
                    int r = neighbour / 10;
                    int c = neighbour % 10;
                    if (cell[r][c].isMine()) {
                        mineCount++;
                    }
                }
                cell[row][col].setAdjacent(mineCount);
            }
        }
    }

    public List<Integer> getNeighbours(int index) {
        int row = index / 10;
        int col = index % 10;
        List<Integer> neighbours = new ArrayList<>();
    
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < 10 && c >= 0 && c < 10 && !(r == row && c == col)) {
                    neighbours.add(r * 10 + c);
                }
            }
        }
        return neighbours;
    }

    public boolean openCell(int index) {
        int row = index / 10;
        int col = index % 10;
        if (index < 0 || index >= 100 || cell[row][col].isRevealed()) {
            return false;
        } else if (cell[row][col].getAdjacentMines() > 0) {
            cell[row][col].setRevealed(true);
        }
        return false;
    }

    //used bfs algorithm to check the surroundings of every cell if a cell is empty then 
    // it will reveal all other surroundings cell if the surrounding cell also empty then it will recursively do this process
    public void checkSurroundings(int r, int c) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(r * 10 + c); 
        cell[r][c].setRevealed(true);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentRow = current / 10;
            int currentCol = current % 10;

            if (cell[currentRow][currentCol].getAdjacentMines() == 0) {
                List<Integer> neighbours = getNeighbours(current);
                for (Integer neighbour : neighbours) {
                    int neighbourRow = neighbour / 10;
                    int neighbourCol = neighbour % 10;
                    if (!cell[neighbourRow][neighbourCol].isRevealed() && !cell[neighbourRow][neighbourCol].isMine() &&cell[neighbourRow][neighbourCol].getAdjacentMines()==0 ) {
                        cell[neighbourRow][neighbourCol].setRevealed(true);
                        queue.add(neighbour);
                    }
                }
            }
        }
    }

    public List<Integer> getNeighborsForReveal(int index) {
        List<Integer> neighbours = new ArrayList<>();
        int row = index / 10;
        int col = index % 10;
    
        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if (r >= 0 && r < 10 && c >= 0 && c < 10 && !(r == row && c == col)) {
                    neighbours.add(r * 10 + c+1);
                }
            }
        }
        return neighbours;
    }
    
    //putting flags to the cell based on the user input
    public void putFlag(int number) {
        if (flagCount<10){
            int row = (number - 1) / 10;
            int col = (number - 1) % 10;
            if (!cell[row][col].isFlagged() && !cell[row][col].isRevealed()){
                cell[row][col].setFlag();
                flagCount++;
            }
            else{
                System.out.println("That cell is already Flagged or already revealed.");
            }
        }
    }

    // removing flag on the user mentioned cell
    public boolean removeFlag(int number) {
        int row = (number - 1) / 10;
        int col = (number - 1) % 10;
        if (cell[row][col].isFlagged()) {
            cell[row][col].removeFlag();
            flagCount--;
            return true;
        }
        return false;
    }

    //checking the player is win or not if the flag count is matched 
    public boolean checkWinCondition() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (cell[row][col].isMine() && !cell[row][col].isFlagged()) {
                    System.out.println("Not all mines are flagged");
                    return false;
                }
            }
        }
        System.out.println("All mines are flagged, you won!");
        return true;
    }
    
    //if the user choose mine cell then it will reveal all cells and the game will over
    public void revealAllMines() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (cell[row][col].isMine()) {
                    cell[row][col].setRevealed(true);
                }
            }
        }
    }
}
