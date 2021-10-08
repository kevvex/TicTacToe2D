import javax.swing.*;

class Cell extends JLabel {
    private String message;
    private JLabel label;
    private int index;

    Cell(String message, int index) {
        setCell(message, index);
    }

    private void setCell(String message, int index) {
        this.message = message;
        this.index = index;
        setText(message);
    }

    final int getIndex() {
        return index;
    }
}
