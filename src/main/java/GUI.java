import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

class GUI extends JFrame implements MouseListener {
    private JPanel panel;
    private Cell[] gridLabels;

    private Game.CODE code;
    private Game game;
    private Player player;

    GUI() {
        game = new Game();
        player = new Player();
        code = Game.CODE.OK;

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        final int WINDOW_WIDTH = 640;
        final int WINDOW_HEIGHT = 640;

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation((int)(screenSize.getWidth() / 2) - (WINDOW_WIDTH / 2), (int)(screenSize.getHeight() / 2) - (WINDOW_HEIGHT / 2));

        GridLayout grid = new GridLayout(Game.BOARD_SIZE, Game.BOARD_SIZE);
        panel = new JPanel(grid);

        gridLabels = new Cell[Game.BOARD_SIZE * Game.BOARD_SIZE];
        for (int i = 0; i < Game.BOARD_SIZE * Game.BOARD_SIZE; i++) {
            gridLabels[i] = new Cell("", i);
            gridLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
            gridLabels[i].setFont(new Font("Serif", Font.PLAIN, 72));
            gridLabels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(gridLabels[i]);
        }

        panel.addMouseListener(this);
        add(panel);
        setVisible(true);
    }

    private void drawSymbol(int index, Game.SYMBOL symbol) {
        char textSymbol = game.putWhichSymbol(symbol);
        gridLabels[index].setText(String.valueOf(textSymbol));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Cell cell = (Cell)panel.getComponentAt(e.getX(), e.getY());
        int index = cell.getIndex();
        int x = index / Game.BOARD_SIZE;
        int y = index - Game.BOARD_SIZE * x;

        //System.out.println(player.getTextSymbol() + " == " + gridLabels[index].getText());
        if (code == Game.CODE.OK || code == Game.CODE.TIE) {
            player.symbolToggle();
            drawSymbol(index, player.getSymbol());
        }

        /* BUG IN GUI: If placing X, then O, then trying to click on same position
         * for X again, then in new spot. The GUI doesn't draw the X (but works in
         * terminal version)
         *
         * STATUS: UNRESOLVED
         */

        code = game.updateBoard(x, y, player.getSymbol());
        game.drawBoard();

        if (code != Game.CODE.TIE && game.hasWon()) {
            String whoWon = player.getCurrentPlayer() + " won!";
            System.out.println(whoWon);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
