public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game game = new Game();
            //game.start();
        }
        else if (args[0].equalsIgnoreCase("gui")) {
            new GUI();
        }
    }
}
