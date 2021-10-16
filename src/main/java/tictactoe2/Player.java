package tictactoe2;

public class Player {
    private Marker marker;

    public Player() {

    }

    public Player(Marker marker) {
        this.marker = marker;
    }

    void setMarker(Marker marker) {
        this.marker = marker;
    }

    final Marker getMarker() {
        return marker;
    }
}
