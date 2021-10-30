package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coordinate {
    private final static List<Integer> allowedYCoordinate = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

    private final Xcoord xCoordinate;
    private final int yCoordinate;

    public Coordinate(char xCoordinate, int yCoordinate) {
        this.xCoordinate = Xcoord.valueOf(xCoordinate);
        if (this.xCoordinate == null)
            throw new RuntimeException("Invalid X-Coordinate");

        if (allowedYCoordinate.contains(yCoordinate))
            this.yCoordinate = yCoordinate;
        else
            throw new RuntimeException("Invalid Y-Coordinate");
    }

    public int xValue() {
        return xCoordinate.ordinal() + 1;
    }

    public int yValue() {
        return yCoordinate;
    }

    public enum Xcoord {
        A, B, C, D, E, F, G, H;

        private static Map<Character, Xcoord> map = new HashMap<Character, Xcoord>();

        static {
            for (Xcoord xCoord : Xcoord.values()) {
                map.put(xCoord.name().charAt(0), xCoord);
            }
        }

        public static Xcoord valueOf(char ch) {
            return map.get(Character.toUpperCase(ch));
        }
    }
}
