import java.util.Scanner;

public class Main {
    private Board board;
    private boolean gameOver;

    public Main() {
        board = new Board(10);
        gameOver = false;
    }

    public void startGame() {
        System.out.println("Welcome to MineSweeper!");
        System.out.println("Your Game is Started!!! \nEnter");
        Scanner ab = new Scanner(System.in);

        while (!gameOver) {
            displayMenu();
            int userInput = ab.nextInt();

            if (userInput >= 1 && userInput <= 3) {
                int cellNumber = getCellInput(ab);
                switch (userInput) {
                    case 1 -> handleReveal(cellNumber);
                    case 2 -> handleFlag(cellNumber);
                    case 3 -> handleRemoveFlag(cellNumber);
                }
                postTurnActions();
            } else if (userInput == 4) {
                board.revealAllMines();
            } else {
                System.out.println("Enter a valid option.");
            }

           
        }
        ab.close();
    }

    private void handleReveal(int cellNumber) {
        int row = (cellNumber - 1) / 10;
        int col = (cellNumber - 1) % 10;
        if (board.revealCell(row, col)) {
            System.out.println("Boom! You hit a bomb. Game Over!");
            board.revealAllMines();
            gameOver = true;
        }
    }

    private void handleFlag(int cellNumber) {
        if (board.getFlagCount() < 10) board.putFlag(cellNumber);
        else System.out.println("Flag limit exceeded.");
    }

    private void handleRemoveFlag(int cellNumber) {
        if (!board.removeFlag(cellNumber)) System.out.println("No flag at this position.");
    }

    private int getCellInput(Scanner ab) {
        int cellNumber;
        do {
            System.out.println("Enter the cell number (1 to 100): ");
            cellNumber = ab.nextInt();
        } while (cellNumber < 1 || cellNumber > 100);
        return cellNumber;
    }

    private void displayMenu() {
        
        System.out.println("1. To Reveal cell\n2. Put Flag\n3. Remove Flag");
    }

    private void postTurnActions() {
        board.printBoard();
        System.out.println("Flagged: " + board.getFlagCount());
        System.out.println("Remaining Flags: " + (10 - board.getFlagCount()));

        if (board.getFlagCount() == 10 && board.checkWinCondition()) {
            System.out.println("Congratulations! You flagged all the mines correctly. You win!");
            gameOver = true;
        }
    }

    public static void main(String[] args) {
        new Main().startGame();
    }
}
