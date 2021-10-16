package tictactoe2;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameStart {
    private final int BOARD_SIZE = 3;
    private int countTakenPositions = 0;
    private HashMap<Integer, Marker> board;
    private Player human;
    private Player computer;
    private CoordinateConverter converter = new CoordinateConverter(BOARD_SIZE);

    enum STATUS {
        GAME_OVER,
        TIE,
        OK,
        INVALID_COORDINATE,
        POSITION_TAKEN
    }

    public GameStart() {
        human = new Player(new Marker('X'));
        computer = new Player(new Marker('O'));

        initEmptyBoard();
        gameLoop();
    }

    private void initEmptyBoard() {
        board = new HashMap<>();
        CoordinateConverter converter = new CoordinateConverter(BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            Marker marker = converter.convertToMarkerCoordinate(i);
            marker.setSymbol(' ');
            board.put(i, marker);
        }
    }

    private void gameLoop() {

        STATUS status = STATUS.OK;
        while (status != STATUS.GAME_OVER) {
            if (isInputValid()) {
                status = updateBoard(human.getMarker());

                while (status == STATUS.POSITION_TAKEN) {
                    Random r = new Random();
                    int num = r.nextInt(8);
                    System.out.println("RANDOM: " + num);
                    Marker pos = converter.convertToMarkerCoordinate(num);
                    computer.getMarker().setMarkerPosition(pos.getX(), pos.getY());
                    computer.getMarker().setIndex(num);

                    status = updateBoard(computer.getMarker());
                }
            }
            drawBoard();
        }
    }

    private boolean isInputValid() {
        boolean isValid = true;
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("x = ");
            int inputY = scanner.nextInt();
            System.out.print("y = ");
            int inputX = scanner.nextInt();

            human.getMarker().setMarkerPosition(inputX, inputY);
            human.getMarker().setIndex(converter.convertToIndex(inputX, inputY));
        }
        catch (InputMismatchException e) {
            System.out.println("That is not an integer!");
            isValid = false;
        }

        return isValid;
    }

    private boolean checkIfSomeoneHasWon() {
        return false;
    }

    private boolean isBoardFull() {
        return countTakenPositions == (BOARD_SIZE * BOARD_SIZE);
    }

    private STATUS updateBoard(Marker marker) {
        STATUS code = STATUS.OK;

        try {
            final char EMPTY = ' ';
            if (board.get(marker.getIndex()).getSymbol() == EMPTY) {
                board.replace(marker.getIndex(), marker);
                countTakenPositions++;
            }
            else {
                System.out.printf("Position (%d, %d) already taken!\n", marker.getY(), marker.getX());
                code = STATUS.POSITION_TAKEN;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid coordinate!");
            code = STATUS.INVALID_COORDINATE;
        }

        boolean isTie = isBoardFull();
        if (isTie) {
            System.out.println("It is a tie!");
            code = STATUS.TIE;
        }

        return code;
    }

    private void drawBoard() {
        for (int y = 0; y < BOARD_SIZE; y++) {
            System.out.println("|---|---|---|");
            for (int x = 0; x < BOARD_SIZE; x++) {
                int index = BOARD_SIZE * y + x;
                System.out.printf("| %c ", board.get(index).getSymbol());
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("|---|---|---|\n");
    }

}
