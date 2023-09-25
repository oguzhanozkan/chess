package info;


import model.Color;
import model.Coordinates;
import model.Piece;
import model.PieceMark;
import move.PieceMove;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardInfo {


    // santranç tahtasındaki bütün taşların konum bilgisini buluyor
    public HashMap<String, Float> boardInfo(ArrayList<ArrayList<String>> board){

        HashMap<Piece, Coordinates> pieceCoordinateWhite = new HashMap<>();
        HashMap<Piece, Coordinates> pieceCoordinateBlack = new HashMap<>();

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {

                String s = board.get(i).get(j);
                if (s.charAt(0) == '-') {
                    continue;
                } else {
                    if (s.charAt(1) == 's') {
                        if (s.charAt(0) == 'p') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Pawn", Color.BLACK, PieceMark.PAWN);
                            pieceCoordinateBlack.put(piece, coordinates);
                        } else if (s.charAt(0) == 'k') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Rook",Color.BLACK,PieceMark.ROOK);
                            pieceCoordinateBlack.put(piece, coordinates);
                        } else if (s.charAt(0) == 'a') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Knight",Color.BLACK,PieceMark.KNIGHT);
                            pieceCoordinateBlack.put(piece, coordinates);
                        } else if (s.charAt(0) == 'f') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Bishop",Color.BLACK,PieceMark.BISHOP);
                            pieceCoordinateBlack.put(piece, coordinates);
                        } else if (s.charAt(0) == 'v') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Queen",Color.BLACK,PieceMark.QUEEN);
                            pieceCoordinateBlack.put(piece, coordinates);
                        } else if (s.charAt(0) == 's') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("King",Color.BLACK,PieceMark.KING);
                            pieceCoordinateBlack.put(piece, coordinates);
                        }
                    } else {
                        if (s.charAt(0) == 'p') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Pawn",Color.WHITE,PieceMark.PAWN);
                            pieceCoordinateWhite.put(piece, coordinates);
                        } else if (s.charAt(0) == 'k') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Rook",Color.WHITE,PieceMark.ROOK);
                            pieceCoordinateWhite.put(piece, coordinates);
                        } else if (s.charAt(0) == 'a') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Knight",Color.WHITE,PieceMark.KNIGHT);
                            pieceCoordinateWhite.put(piece, coordinates);
                        } else if (s.charAt(0) == 'f') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Bishop",Color.WHITE,PieceMark.BISHOP);
                            pieceCoordinateWhite.put(piece, coordinates);
                        } else if (s.charAt(0) == 'v') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("Queen",Color.WHITE,PieceMark.QUEEN);
                            pieceCoordinateWhite.put(piece, coordinates);
                        } else if (s.charAt(0) == 's') {
                            Coordinates coordinates = new Coordinates();
                            coordinates.setX(i);
                            coordinates.setY(j);
                            Piece piece = new Piece("King",Color.WHITE,PieceMark.KING);
                            pieceCoordinateWhite.put(piece, coordinates);
                        }
                    }
                }
            }
        }

        PieceMove pieceMove = new PieceMove();
        return pieceMove.piecesMove(pieceCoordinateBlack, pieceCoordinateWhite);
    }
}
