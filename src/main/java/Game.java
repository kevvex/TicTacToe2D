class Game {

    private final char X = 'X';
    private final char O = 'O';
    private final char EMPTY = ' ';

    enum SYMBOL {
        X, O, EMPTY
    }

    static final int BOARD_SIZE = 3;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    Game() {
        initEmptyBoard();
    }

    private void initEmptyBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    boolean hasWon() {
        boolean hasWon = false;

        // Check each column (vertical)
        for (int row = 0; row < BOARD_SIZE; row++) {
            if(board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != EMPTY) {
                hasWon = true;
            }
        }

        // Check each row (horizontal)
        for (int col = 0; col < BOARD_SIZE; col++) {
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col] && board[0][col] != EMPTY) {
                hasWon = true;
            }
        }

        return hasWon;
    }

    void updateBoard(int x, int y, SYMBOL choice) {
        char symbol = putWhichSymbol(choice);
        board[x][y] = symbol;
    }

    private char putWhichSymbol(SYMBOL choice) {
        char symbol = ' ';
        switch (choice) {
            case O: symbol = O; break;
            case X: symbol = X; break;
            default: break;
        }
        return symbol;
    }

    void drawBoard() {
        for (int col = 0; col < BOARD_SIZE; col++) {
            System.out.println("|---|---|---|");
            for (int row = 0; row < BOARD_SIZE; row++) {
                System.out.printf("| %c ", board[row][col]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("|---|---|---|");
    }
}
