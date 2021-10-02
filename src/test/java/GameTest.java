import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {

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
    @DisplayName("Check if a player can win using three in a LEFT DIAGONAL")
    void checkIfYouCanWinUsingLeftDiagonal() {
        Game game = new Game();
        game.updateBoard(0, 0, Game.SYMBOL.X);
        game.updateBoard(1, 1, Game.SYMBOL.X);
        game.updateBoard(2, 2, Game.SYMBOL.X);

        Assertions.assertTrue(game.hasWon());
        game.drawBoard();
        System.out.println();
    }

    @Test
    @DisplayName("Check if a player can win using three in a RIGHT DIAGONAL")
    void checkIfYouCanWinUsingRightDiagonal() {
        Game game = new Game();
        game.updateBoard(2, 0, Game.SYMBOL.X);
        game.updateBoard(1, 1, Game.SYMBOL.X);
        game.updateBoard(0, 0, Game.SYMBOL.X);

        Assertions.assertTrue(game.hasWon());
        game.drawBoard();
        System.out.println();
    }
}
