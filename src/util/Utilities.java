package util;

import model.Coordinates;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Utilities {

    public static Coordinates findBiggest(Set<Coordinates> coordinatesHashSet) {

        Coordinates bigCoordinate = new Coordinates();

        if (coordinatesHashSet.size() > 0) {
            HashSet<Integer> integers = new HashSet<Integer>();
            for (Coordinates c : coordinatesHashSet) {
                String s = c.getX() + "" + c.getY();
                int i = Integer.parseInt(s);
                integers.add(i);
            }
            int obj = Collections.max(integers);
            int x = obj / 10;
            int y = obj % 10;
            bigCoordinate.setX(x);
            bigCoordinate.setY(y);
            return bigCoordinate;
        }
        return null;
    }

    public static Coordinates findSmallest(Set<Coordinates> coordinatesHashSet) {

        Coordinates smallCoordinate = new Coordinates();

        if (coordinatesHashSet.size() > 0) {
            HashSet<Integer> integers = new HashSet<Integer>();
            for (Coordinates c : coordinatesHashSet) {
                String s = c.getX() + "" + c.getY();
                int i = Integer.parseInt(s);
                integers.add(i);
            }
            int obj = Collections.min(integers);
            int x = obj / 10;
            int y = obj % 10;
            smallCoordinate.setX(x);
            smallCoordinate.setY(y);
            return smallCoordinate;
        }
        return null;
    }
}
