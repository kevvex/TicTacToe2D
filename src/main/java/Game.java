import java.util.InputMismatchException;
import java.util.Scanner;

class Game {

    private final char X = 'X';
    private final char O = 'O';
    private final char EMPTY = ' ';

    private int inputX;
    private int inputY;
    private final int INVALID = -1;

    private int boardFullCounter = 0;

    enum SYMBOL {
        X, O, EMPTY
    }

    enum CODE {
        OK, POSITION_TAKEN, INVALID_COORDINATE, TIE
    }

    static final int BOARD_SIZE = 3;
    private char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    Game() {
        inputX = inputY = 0;
        initEmptyBoard();
    }

    private void initEmptyBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                board[x][y] = EMPTY;
            }
        }
    }

    void start() {
        boolean shouldTogglePlayer = false;
        boolean isValid;
        drawBoard();

        CODE code = CODE.OK;
        while (!hasWon() && code != CODE.TIE) {

            playerTurn(shouldTogglePlayer);
            isValid = isInputValid();

            if (isValid) {
                SYMBOL symbol = togglePlayer(shouldTogglePlayer);
                code = updateBoard(inputX, inputY, symbol);
            }

            if (code == CODE.OK) {
                shouldTogglePlayer = !shouldTogglePlayer;
            }

            drawBoard();
        }

        if (code != CODE.TIE) {
            if (shouldTogglePlayer) {
                System.out.println("Player 1 won!");
            }
            else {
                System.out.println("Player 2 won!");
            }
        }
        else {
            System.out.println("It is a tie!");
        }
    }

    private void playerTurn(boolean toggle) {
        String player;

        if (toggle) {
            player = "Player 2's turn\n";
        }
        else {
            player = "Player 1's turn\n";
        }

        System.out.println(player);
    }

    private SYMBOL togglePlayer(boolean toggle) {
        String player;
        SYMBOL symbol;
        if (toggle) {
            symbol = SYMBOL.O;
        }
        else {
            symbol = SYMBOL.X;
        }

        return symbol;
    }

    private boolean isInputValid() {
        boolean isValid = true;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("x = ");
            inputY = scanner.nextInt();
            System.out.print("y = ");
            inputX = scanner.nextInt();

            /* Board does not place the symbol on correct coordinate
             * since board is flipped when using the GUI version
             * Therefore, here the x = y, and y = x.
             *
             * This so that the terminal board is updated correctly when
             * using the GUI version.
             */
        }
        catch (InputMismatchException e) {
            System.out.println("That is not an integer!");
            isValid = false;
        }

        return isValid;
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

        // Check diagonal
        boolean isTopRightDiagonal = board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != EMPTY;
        boolean isTopLeftDiagonal = board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != EMPTY;
        if (isTopRightDiagonal || isTopLeftDiagonal) {
            hasWon = true;
        }

        return hasWon;
    }

    CODE updateBoard(int x, int y, SYMBOL choice) {
        char symbol = putWhichSymbol(choice);
        CODE code = CODE.OK;

        try {
            if (board[x][y] == EMPTY) {
                board[x][y] = symbol;
                boardFullCounter++;
            }
            else {
                System.out.printf("Position (%d, %d) already taken!\n", x, y);
                code = CODE.POSITION_TAKEN;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid coordinate!");
            code = CODE.INVALID_COORDINATE;
        }

        boolean isTie = isBoardFull();
        if (isTie) {
            System.out.println("It is a tie!");
            code = CODE.TIE;
        }

        return code;
    }

    char putWhichSymbol(SYMBOL choice) {
        char symbol = ' ';
        switch (choice) {
            case O: symbol = O; break;
            case X: symbol = X; break;
            default: break;
        }
        return symbol;
    }

    boolean isBoardFull() {
        return (boardFullCounter == (BOARD_SIZE * BOARD_SIZE));
    }

    void drawBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            System.out.println("|---|---|---|");
            for (int y = 0; y < BOARD_SIZE; y++) {
                System.out.printf("| %c ", board[x][y]);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("|---|---|---|");
    }
}
