package model.pieces;

import model.Color;
import model.Coordinate;

public abstract class Piece {
    private final Color color;
    private Coordinate position;

    protected Piece(Color color) {
        this.color = color;
    }

    public boolean isValidMove(Coordinate next) {
        if (next == null) return false;
        return !(next.xValue() == position.xValue() && next.yValue() == position.yValue());
    }

    public Color getColor() {
        return color;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }
}
