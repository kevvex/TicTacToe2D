import tictactoe2.GameStart;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            Game game = new Game();
            game.start();
        }
        else if (args[0].equalsIgnoreCase("gui")) {
            new GUI();
        }
        else if (args[0].equalsIgnoreCase("version")) {
            switch (args[1]) {
                case "1":
                    System.out.println("Version 1 selected");
                    break;
                case "2":
                    System.out.println("Version 2 selected");
                    new GameStart();
                    break;

            }
        }
    }
}
