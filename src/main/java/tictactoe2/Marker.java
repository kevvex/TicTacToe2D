package tictactoe2;

class Marker {
    private int x, y, index;
    private char symbol;

    Marker(char symbol) {
        this.symbol = symbol;
    }

    Marker(int index) {
        this.index = index;
    }

    Marker(int x, int y) {
        setMarkerPosition(x, y);
    }

    Marker(int x, int y, char symbol) {
        this.symbol = symbol;
        setMarkerPosition(x, y);
    }

    void setMarkerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    final int getMarkerPosition() {
        return index;
    }

    void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    final int getX() {
        return x;
    }

    final int getY() {
        return y;
    }

    final char getSymbol() {
        return symbol;
    }

    int getIndex() {
        return index;
    }

    void setIndex(int index) {
        this.index = index;
    }
}
