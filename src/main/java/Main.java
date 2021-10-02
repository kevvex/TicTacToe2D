public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        game.updateBoard(0, 0, Game.SYMBOL.X);
        game.updateBoard(0, 1, Game.SYMBOL.X);
        game.updateBoard(0, 2, Game.SYMBOL.X);
        game.hasWon();

        game.drawBoard();
    }
}
