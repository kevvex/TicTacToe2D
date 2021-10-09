import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    @DisplayName("Check to see if board is full")
    void checkIfBoardIsFull() {
        Game game = new Game();

        game.updateBoard(0, 0, Game.SYMBOL.O);
        game.updateBoard(1, 0, Game.SYMBOL.O);
        game.updateBoard(2, 0, Game.SYMBOL.O);

        game.updateBoard(0, 1, Game.SYMBOL.O);
        game.updateBoard(1, 1, Game.SYMBOL.X);
        game.updateBoard(2, 1, Game.SYMBOL.X);

        game.updateBoard(0, 2, Game.SYMBOL.O);
        game.updateBoard(1, 2, Game.SYMBOL.X);
        game.updateBoard(2, 2, Game.SYMBOL.X);
        Assertions.assertTrue(game.isBoardFull());
    }

    @Test
    @DisplayName("Check if the position is already taken")
    void checkIfPositionIsTaken() {
        Game game = new Game();
        game.updateBoard(1, 1, Game.SYMBOL.X);
        Game.CODE code = game.updateBoard(1, 1, Game.SYMBOL.X);
        Assertions.assertEquals(Game.CODE.POSITION_TAKEN, code);
    }


    @Test
    @DisplayName("Check if the coordinate is invalid")
    void checkIfCoordinateIsInvalid() {
        Game game = new Game();
        Game.CODE code = game.updateBoard(9, 9, Game.SYMBOL.X);
        Assertions.assertEquals(Game.CODE.INVALID_COORDINATE, code);
    }

    @Test
    @DisplayName("Check if a player can win using three in a COLUMN")
    void checkIfYouCanWinUsingColumns() {
        for (int x = 0; x < Game.BOARD_SIZE; x++) {
            Game game = new Game();
            game.updateBoard(x, 0, Game.SYMBOL.X);
            game.updateBoard(x, 1, Game.SYMBOL.X);
            game.updateBoard(x, 2, Game.SYMBOL.X);

            Assertions.assertTrue(game.hasWon());
            game.drawBoard();
            System.out.println();
        }
    }

    @Test
    @DisplayName("Check if a player can win using three in a ROW")
    void checkIfYouCanWinUsingRows() {
        for (int y = 0; y < Game.BOARD_SIZE; y++) {
            Game game = new Game();
            game.updateBoard(0, y, Game.SYMBOL.X);
            game.updateBoard(1, y, Game.SYMBOL.X);
            game.updateBoard(2, y, Game.SYMBOL.X);

            Assertions.assertTrue(game.hasWon());
            game.drawBoard();
            System.out.println();
        }
    }

    @Test
    @DisplayName("Check if a player can win using three in a TOP RIGHT DIAGONAL")
    void checkIfYouCanWinUsingTopRightDiagonal() {
        Game game = new Game();
        game.updateBoard(0, 0, Game.SYMBOL.X);
        game.updateBoard(1, 1, Game.SYMBOL.X);
        game.updateBoard(2, 2, Game.SYMBOL.X);

        Assertions.assertTrue(game.hasWon());
        game.drawBoard();
        System.out.println();
    }

    @Test
    @DisplayName("Check if a player can win using three in a TOP LEFT DIAGONAL")
    void checkIfYouCanWinUsingTopLeftDiagonal() {
        Game game = new Game();
        game.updateBoard(2, 0, Game.SYMBOL.X);
        game.updateBoard(1, 1, Game.SYMBOL.X);
        game.updateBoard(0, 2, Game.SYMBOL.X);

        Assertions.assertTrue(game.hasWon());
        game.drawBoard();
        System.out.println();
    }

    @Test
    @DisplayName("Blocking to place the X symbol for Player 1")
    void checkIfCanPlacePlayerSymbol() {
        Game game = new Game();
        game.updateBoard(1, 0, Game.SYMBOL.X);
        game.updateBoard(2, 0, Game.SYMBOL.O);
        game.updateBoard(1, 0, Game.SYMBOL.X); // Click again
        game.updateBoard(2, 1, Game.SYMBOL.X);
        Game.CODE code = game.updateBoard(2, 1, Game.SYMBOL.X);

        Assertions.assertEquals(Game.CODE.POSITION_TAKEN, code);
        game.drawBoard();
        System.out.println();
    }

}
