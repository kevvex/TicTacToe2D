public class Player {

    private boolean shouldToggle;
    private Game.SYMBOL symbol;
    private String currentPlayer;

    private final String PLAYER1 = "Player 1";
    private final String PLAYER2 = "Player 2";

    Player() {
        symbol = Game.SYMBOL.X;
    }

    public void displayPlayer() {
        if (shouldToggle) {
            System.out.println("Player 1's turn");
        }
        else {
            System.out.println("Player 2's turn");
        }
    }

    Game.SYMBOL symbolToggle() {

        if (toggle()) {
            symbol = Game.SYMBOL.X;
            currentPlayer = PLAYER1;
        }
        else {
            symbol = Game.SYMBOL.O;
            currentPlayer = PLAYER2;
        }

        return symbol;
    }

    Game.SYMBOL getSymbol() {
        return symbol;
    }

    final String getCurrentPlayer() {
        return currentPlayer;
    }

    private boolean toggle() {
        return shouldToggle = !shouldToggle;
    }
}
