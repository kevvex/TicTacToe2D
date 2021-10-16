package tictactoe2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateConverterTest {

    @Test
    @DisplayName("Convert indexes to coordinates")
    void convertIndexesToCoordinates() {
        final int BOARD_SIZE = 3;
        CoordinateConverter converter = new CoordinateConverter(BOARD_SIZE);

        for (int index = 0; index < BOARD_SIZE * BOARD_SIZE; index++) {
            int x = index / BOARD_SIZE;
            int y = index - BOARD_SIZE * x;

            Marker m = converter.convertToMarkerCoordinate(index);
            Assertions.assertEquals(x, m.getX());
            Assertions.assertEquals(y, m.getY());
            System.out.printf("%d -> (%d, %d)\n", index, x, y);
        }
        System.out.println();
    }

    @Test
    @DisplayName("Convert coordinates to indexes using Coordinate class")
    void convertCoordinatesToIndexes() {
        final int BOARD_SIZE = 3;
        CoordinateConverter converter = new CoordinateConverter(BOARD_SIZE);

        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                int index = BOARD_SIZE * x + y;
                int returnedIndex = converter.convertToIndex(x, y);
                Assertions.assertEquals(index, returnedIndex);
                System.out.printf("(%d, %d) -> %d\n", x, y, index);
            }
        }
        System.out.println();
    }

    @Test
    @DisplayName("Convert coordinates to indexes using Coordinate class")
    void convertCoordinatesToIndexesUsingCoordinate() {
        final int BOARD_SIZE = 3;
        CoordinateConverter converter = new CoordinateConverter(BOARD_SIZE);

        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                int index = BOARD_SIZE * x + y;
                Marker coordinate = new Marker(x, y);
                int returnedIndex = converter.convertToIndex(coordinate);
                Assertions.assertEquals(index, returnedIndex);
                System.out.printf("(%d, %d) -> %d\n", x, y, index);
            }
        }
        System.out.println();
    }
}
