# Minesweeper Game

## Overview

Minesweeper is a classic single-player puzzle game. The objective is to clear a minefield without detonating any mines. Players can reveal cells, place flags on suspected mine locations, and ultimately try to uncover all safe cells.

## Features

- Reveal cells to check for mines.
- Place flags to mark potential mines.
- Remove flags if you change your mind.
- Automatically reveal empty cells and their neighbors.
- Game ends when a mine is triggered or when all mines are successfully flagged.

## Technologies Used

- Java

## Getting Started

### Prerequisites

Ensure you have Java Development Kit (JDK) installed on your machine.

### How to Run

1. Clone the repository or download the code files.
2. Open the project in your favorite Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Compile the Java files.
4. Run the `Main` class to start the game.

### Gameplay Instructions

1. When prompted, enter a cell number (1-100) to interact with the game.
2. Choose one of the following options:
   - **1**: Reveal the selected cell.
   - **2**: Place a flag on the selected cell.
   - **3**: Remove the flag from the selected cell.
   - **4**: Reveal all mines (useful for debugging).
3. The game continues until all mines are flagged or a mine is revealed.

## Class Descriptions

### `Main`

The entry point of the game, responsible for starting the game loop, handling user inputs, and displaying the game menu.

### `Board`

Represents the game board consisting of cells. It manages cell states, mine placement, and win condition checks.

- **Methods:**
  - `placeMines(int userInput)`: Randomly places mines on the board.
  - `printBoard()`: Displays the current state of the board.
  - `revealCell(int row, int col)`: Reveals a cell and checks for adjacent mines.
  - `checkWinCondition()`: Checks if the player has flagged all mines correctly.
  - `revealAllMines()`: Reveals all mines when the game is over.

### `Cell`

Represents a single cell on the board, keeping track of its state (mine, revealed, flagged) and the number of adjacent mines.

- **Methods:**
  - `setMine(boolean isMine)`: Sets the cell as a mine or not.
  - `setRevealed(boolean revealed)`: Marks the cell as revealed.
  - `setFlag()`: Flags the cell.
  - `removeFlag()`: Removes the flag from the cell.

## Contributing

Feel free to fork the repository and submit pull requests for improvements or additional features.

## License

This project is open-source and available under the MIT License.
