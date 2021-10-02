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
    char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    Game() {
        inputX = inputY = 0;
        initEmptyBoard();
    }

    private void initEmptyBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    void start() {
        boolean isValid;
        boolean shouldTogglePlayer = false;

        CODE code = CODE.OK;
        while (!hasWon() && code != CODE.TIE) {
            shouldTogglePlayer = !shouldTogglePlayer;
            SYMBOL symbol = togglePlayer(shouldTogglePlayer);

            isValid = isInputValid();

            if (isValid) {
                code = updateBoard(inputX, inputY, symbol);
            }
            drawBoard();
        }
    }

    private SYMBOL togglePlayer(boolean toggle) {
        String player;
        SYMBOL symbol;
        if (toggle) {
            player = "Player 1's turn\n";
            symbol = SYMBOL.X;
        }
        else {
            player = "Player 2's turn\n";
            symbol = SYMBOL.O;
        }

        System.out.println(player);

        return symbol;
    }

    private boolean isInputValid() {
        boolean isValid = true;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("x = ");
            inputX = scanner.nextInt();
            System.out.print("y = ");
            inputY = scanner.nextInt();
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

        System.out.println(hasWon);
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

    private char putWhichSymbol(SYMBOL choice) {
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
