package interfaces;

import model.Color;
import model.Coordinate;

public interface Chess {
    void initalizeGame();
    Color whoseTurn();
    boolean validMove(String current, String next);
    void move(String current, String next);
}
