package model.pieces;

import model.Color;
import model.Coordinate;

public class King extends Piece {

    public King(Color color) {
        super(color);
        setPosition(new Coordinate('E', Color.White == color ? 1 : 8));
    }

    @Override
    public boolean isValidMove(Coordinate next) {
        if (!super.isValidMove(next))
            return false;
        int xDiff = next.xValue() - getPosition().xValue();
        int yDiff = next.yValue() - getPosition().yValue();
        return (xDiff >= -1 && xDiff <= 1 && yDiff >= -1 && yDiff <= 1);
    }
}
