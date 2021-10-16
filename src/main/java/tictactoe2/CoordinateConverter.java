package tictactoe2;

public class CoordinateConverter {
    private int BOARD_SIZE;

    public CoordinateConverter(int BOARD_SIZE) {
        this.BOARD_SIZE = BOARD_SIZE;
    }

    int convertToIndex(int x, int y) {
        return BOARD_SIZE * x + y;
    }

    int convertToIndex(Marker coordinate) {
        return BOARD_SIZE * coordinate.getX() + coordinate.getY();
    }

    Marker convertToMarkerCoordinate(int index) {
        int x = index / BOARD_SIZE;
        int y = index - BOARD_SIZE * x;
        return new Marker(x, y);
    }
}
