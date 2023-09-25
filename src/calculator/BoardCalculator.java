package calculator;

import model.Color;
import model.Coordinates;
import model.Piece;

import java.util.*;
import java.util.stream.Collectors;

public class BoardCalculator {

    public HashMap<String, Float> boardPoint(HashSet<Coordinates> blackHashSet, HashSet<Coordinates> whiteHashSet,
                                             HashMap<Piece, Coordinates> pieceCoordinateBlack, HashMap<Piece, Coordinates> pieceCoordinateWhite) {

        //System.out.println("tehtit altındaki siyah taşlar : " + blackHashSet);
        //System.out.println("tehtit altındaki beyaz taşlar : " + whiteHashSet);

        float blackPointTemp = 0;
        float whitePointTemp = 0;

        float blackPointDanger = 0;
        float whitePointDanger = 0;

        float totalBlackPoint = 0;
        float totalWhitePoint = 0;

        for (Coordinates c : blackHashSet) {
            blackPointDanger += getKeysJava8(pieceCoordinateBlack, c).iterator().next().getPieceMarks().marks;
        }

        for (Coordinates c : whiteHashSet) {
            whitePointDanger += getKeysJava8(pieceCoordinateWhite, c).iterator().next().getPieceMarks().marks;
        }

        for (Piece p : pieceCoordinateBlack.keySet()) {
            blackPointTemp += p.getPieceMarks().marks;
        }

        for (Piece p : pieceCoordinateWhite.keySet()) {
            whitePointTemp += p.getPieceMarks().marks;
        }


        totalBlackPoint = blackPointTemp - blackPointDanger / 2;
        totalWhitePoint = whitePointTemp - whitePointDanger / 2;


        HashMap<String, Float> boardPoints = new HashMap<>();

        boardPoints.put(Color.BLACK.name(), totalBlackPoint);
        boardPoints.put(Color.WHITE.name(), totalWhitePoint);


        return boardPoints;
    }


    private static Set<Piece> getKeysJava8(Map<Piece, Coordinates> map, Coordinates value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
