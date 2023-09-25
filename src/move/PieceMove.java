package move;

import attack.PieceAttacks;
import calculator.BoardCalculator;
import model.Color;
import model.Coordinates;
import model.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PieceMove {

    public HashMap<String, Float> piecesMove(HashMap<Piece, Coordinates> pieceCoordinateBlack, HashMap<Piece, Coordinates> pieceCoordinateWhite) {

        // tehtit altındaki siyah taşlar
        HashSet<Coordinates> blackHashSet = new HashSet<>();

        // tehtit altındaki beyaz taşlar
        HashSet<Coordinates> whiteHashSet = new HashSet<>();

        ArrayList<Coordinates> allPieceCoordinatesBlack = new ArrayList<>();
        pieceCoordinateBlack.forEach((key, value) -> {
            if (key.getColor().name().equals("BLACK")) {
                allPieceCoordinatesBlack.add(value);
            }
        });

        ArrayList<Coordinates> allPieceCoordinatesWhite = new ArrayList<>();
        pieceCoordinateWhite.forEach((key, value) -> {
            if (key.getColor().name().equals("WHITE")) {
                allPieceCoordinatesWhite.add(value);
            }
        });

        pieceCoordinateBlack.forEach((key, value) -> {
            if (key.getName().equals("Rook")) {
                whiteHashSet.addAll(rookMove(value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            } else if (key.getName().equals("Knight")) {
                whiteHashSet.addAll(knightMove(value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            } else if (key.getName().equals("Bishop")) {
                whiteHashSet.addAll(bishopMove(value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            } else if (key.getName().equals("Queen")) {
                whiteHashSet.addAll(queenMove(value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            } else if (key.getName().equals("King")) {
                whiteHashSet.addAll(kingMove(value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            } else if (key.getName().equals("Pawn")) {
                whiteHashSet.addAll(pawnMove(key, value, allPieceCoordinatesBlack, allPieceCoordinatesWhite));
            }

        });

        pieceCoordinateWhite.forEach((key, value) -> {
            if (key.getName().equals("Rook")) {
                blackHashSet.addAll(rookMove(value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            } else if (key.getName().equals("Knight")) {
                blackHashSet.addAll(knightMove(value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            } else if (key.getName().equals("Bishop")) {
                blackHashSet.addAll(bishopMove(value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            } else if (key.getName().equals("Queen")) {
                blackHashSet.addAll(queenMove(value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            } else if (key.getName().equals("King")) {
                blackHashSet.addAll(kingMove(value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            } else if (key.getName().equals("Pawn")) {
                blackHashSet.addAll(pawnMove(key, value, allPieceCoordinatesWhite, allPieceCoordinatesBlack));
            }
        });

        BoardCalculator boardCalculator = new BoardCalculator();
        return boardCalculator.boardPoint(blackHashSet,whiteHashSet,pieceCoordinateBlack,pieceCoordinateWhite);
    }

    public HashSet<Coordinates> rookMove(Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {

        // bir kalenin kendi rengindeki taşlar tarafından bloklanmadan gidebileceği kordinatların listesi
        List<Coordinates> coordinatesList = new ArrayList<>();
        /*
            1. durum => sutun sabit kalırken satir azalacak
            2. durum => sutun sabit kalırken satir artacak
            3. durum => satir sabit kalırken sutun artacak
            4. durum => satir sabit kalırken sutun azalacak
         */

        int moveCount = 1;
        int x = coordinates.getX();
        int y = coordinates.getY();

        // 1.yukarı yönlü hareket
        while (moveCount <= 7) {
            int possibleX = x - moveCount;
            if (possibleX < 0) {
                break;
            } else {
                Coordinates c = new Coordinates(possibleX, y);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, y))) {
                    break;
                } else {
                    coordinatesList.add(c);
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        // 2.aşşağı yönlü hareket
        while (moveCount <= 7) {
            int possibleX = x + moveCount;
            if (possibleX > 7) {
                break;
            } else {
                Coordinates c = new Coordinates(possibleX, y);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, y))) {
                    break;
                } else {
                    coordinatesList.add(c);
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        // 3.sağa yönlü hareket
        while (moveCount <= 7) {
            int possibleY = y + moveCount;
            if (possibleY > 7) {
                break;
            } else {
                Coordinates c = new Coordinates(x, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(x, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(c);
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        // 4.sola yönlü hareket
        while (moveCount <= 7) {
            int possibleY = y - moveCount;
            if (possibleY < 0) {
                break;
            } else {
                Coordinates c = new Coordinates(x, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(x, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(c);
                    moveCount++;
                }
            }
        }

        PieceAttacks pieceAttacks = new PieceAttacks();
        pieceAttacks.rookAttacks(coordinates, coordinatesList, enemy);

        return pieceAttacks.rookAttacks(coordinates, coordinatesList, enemy);
    }

    public HashSet<Coordinates> knightMove(Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {

        // bir atın kendi rengindeki taşlar tarafından bloklanmadan gidebileceği kordinatların listesi
        List<Coordinates> coordinatesList = new ArrayList<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        int possibleX;
        int possibleY;

        int moveCount = 0;
        while (moveCount <= 7) {
            if (moveCount == 0) {
                possibleX = x - 2;
                possibleY = y - 1;
                if (possibleX >= 0 && possibleY >= 0) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 1) {
                possibleX = x - 2;
                possibleY = y + 1;
                if (possibleX >= 0 && possibleY <= 7) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 2) {
                possibleX = x - 1;
                possibleY = y + 2;
                if (possibleX >= 0 && possibleY <= 7) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 3) {
                possibleX = x - 1;
                possibleY = y - 2;
                if (possibleX >= 0 && possibleY >= 0) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 4) {
                possibleX = x + 1;
                possibleY = y + 2;
                if (possibleX <= 7 && possibleY <= 7) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 5) {
                possibleX = x + 1;
                possibleY = y - 2;
                if (possibleX <= 7 && possibleY >= 0) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 6) {
                possibleX = x + 2;
                possibleY = y + 1;
                if (possibleX <= 7 && possibleY <= 7) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
            if (moveCount == 7) {
                possibleX = x + 2;
                possibleY = y - 1;
                if (possibleX <= 7 && possibleY >= 0) {
                    Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                    if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                        coordinatesList.add(possibleCoordinate);
                    }
                }
                moveCount++;
            }
        }

        PieceAttacks pieceAttacks = new PieceAttacks();
        return pieceAttacks.knightAttacks(coordinatesList, enemy);
    }

    public HashSet<Coordinates> bishopMove(Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {

        // bir filin kendi rengindeki taşlar tarafından bloklanmadan gidebileceği kordinatların listesi
        List<Coordinates> coordinatesList = new ArrayList<>();

        /*  filin bulunduğu kordinatı (3,5) 35 gibi düşünüp
            35 - 9 = 26 => (2,6)
            35 - 11 = 24 => (2,4)
            35 + 9 = 44 => (4,4)
            35 + 11 = 46 => (4,6)

            filin bulunduğu kordinatı (3,5) origin gibi hayalettim

            1.durum =>  yukarı yönlü hareketler
                1.1. durum => -9 yukarı sağ yönlü hareket (kordinat düzleminde ki 1. bölge)
                1.2. durum => -11 yukarı sol yönlü hareket (kordinat düzleminde ki 2. bölge)
            2.durum => aşşağı yönlü hareketler
                2.1. durum => +9 yukarı sağ yönlü hareket (kordinat düzleminde ki 3. bölge)
                2.2. durum => +11 yukarı sağ yönlü hareket (kordinat düzleminde ki 4. bölge)
         */
        int moveCount = 1;
        int x = coordinates.getX();
        int y = coordinates.getY();
        String stringCoordinate = x + "" + y;
        int intCoordinate = Integer.parseInt(stringCoordinate);

        // 1.1. durum
        while (moveCount <= 7) {
            int possibleCoordinateInt = intCoordinate - 9;
            int possibleX = possibleCoordinateInt / 10;
            int possibleY = possibleCoordinateInt % 10;
            if (possibleCoordinateInt < 0 || possibleCoordinateInt % 10 == 8 || possibleCoordinateInt % 10 == 9 || possibleCoordinateInt > 77) {
                break;
            } else {
                Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(possibleCoordinate);
                    intCoordinate = possibleCoordinateInt;
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        intCoordinate = Integer.parseInt(stringCoordinate);

        // 1.2. durum
        while (moveCount <= 7) {
            int possibleCoordinateInt = intCoordinate - 11;
            int possibleX = possibleCoordinateInt / 10;
            int possibleY = possibleCoordinateInt % 10;
            if (possibleCoordinateInt < 0 || possibleCoordinateInt % 10 == 8 || possibleCoordinateInt % 10 == 9 || possibleCoordinateInt > 77) {
                break;
            } else {
                Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(possibleCoordinate);
                    intCoordinate = possibleCoordinateInt;
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        intCoordinate = Integer.parseInt(stringCoordinate);

        // 2.1. durum
        while (moveCount <= 7) {
            int possibleCoordinateInt = intCoordinate + 9;
            int possibleX = possibleCoordinateInt / 10;
            int possibleY = possibleCoordinateInt % 10;
            if (possibleCoordinateInt < 0 || possibleCoordinateInt % 10 == 8 || possibleCoordinateInt % 10 == 9 || possibleCoordinateInt > 77) {
                break;
            } else {
                Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(possibleCoordinate);
                    intCoordinate = possibleCoordinateInt;
                    moveCount++;
                }
            }
        }
        moveCount = 1;
        intCoordinate = Integer.parseInt(stringCoordinate);

        // 2.2. durum
        while (moveCount <= 7) {
            int possibleCoordinateInt = intCoordinate + 11;
            int possibleX = possibleCoordinateInt / 10;
            int possibleY = possibleCoordinateInt % 10;
            if (possibleCoordinateInt < 0 || possibleCoordinateInt % 10 == 8 || possibleCoordinateInt % 10 == 9 || possibleCoordinateInt > 77) {
                break;
            } else {
                Coordinates possibleCoordinate = new Coordinates(possibleX, possibleY);
                if (allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                    break;
                } else {
                    coordinatesList.add(possibleCoordinate);
                    intCoordinate = possibleCoordinateInt;
                    moveCount++;
                }
            }
        }

        PieceAttacks pieceAttacks = new PieceAttacks();
        return pieceAttacks.bishopAttacks(coordinates, coordinatesList, enemy);
    }

    public HashSet<Coordinates> queenMove(Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {
        HashSet<Coordinates> queenHashSet = new HashSet<>();

        HashSet<Coordinates> queenLikeRook = rookMove(coordinates, allSameColorCoordinates, enemy);
        HashSet<Coordinates> queenLikeBishop = bishopMove(coordinates, allSameColorCoordinates, enemy);

        queenHashSet.addAll(queenLikeRook);
        queenHashSet.addAll(queenLikeBishop);
        queenHashSet.remove(null);
        return queenHashSet;
    }

    public HashSet<Coordinates> kingMove(Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {

        // şah da aynı vezir gibi ama tek bir kare hareket ediyor
        HashSet<Coordinates> kingHashSet = new HashSet<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        String coordinatesString = x + "" + y;
        int coordinatesInt = Integer.parseInt(coordinatesString);

        HashSet<Coordinates> kingHashSetTemp = new HashSet<>();

        HashSet<Coordinates> kingLikeRook = rookMove(coordinates, allSameColorCoordinates, enemy);
        HashSet<Coordinates> kingLikeBishop = bishopMove(coordinates, allSameColorCoordinates, enemy);

        kingHashSetTemp.addAll(kingLikeRook);
        kingHashSetTemp.addAll(kingLikeBishop);
        kingHashSetTemp.remove(null);

        for (Coordinates c : kingHashSetTemp) {

            String stringC = c.getX() + "" + c.getY();
            int cInt = Integer.parseInt(stringC);
            int different = cInt - coordinatesInt;
            if (different == 10 || different == -10 || different == +1 || different == -1 || different == -11 || different == +11 || different == -9 || different == +9) {
                kingHashSet.add(c);
            }
        }

        kingHashSet.remove(null);
        return kingHashSet;
    }

    public HashSet<Coordinates> pawnMove(Piece piece, Coordinates coordinates, ArrayList<Coordinates> allSameColorCoordinates, ArrayList<Coordinates> enemy) {

        // bir piyonun taş yemek için hareket etmesi durumnda kendi taşının coordinatlarına gelmeyen durumlar
        List<Coordinates> coordinatesList = new ArrayList<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        int possibleX;
        int possibleY;

        int moveCount = 0;


        if (piece.getColor().equals(Color.WHITE)) {
            while (moveCount < 2) {
                if (moveCount == 0) {
                    possibleX = x - 1;
                    possibleY = y - 1;
                    if (possibleX >= 0 && possibleY >= 0) {
                        Coordinates c = new Coordinates(possibleX, possibleY);
                        if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                            coordinatesList.add(c);
                        }
                    }
                    moveCount++;
                }
                if (moveCount == 1) {
                    possibleX = x - 1;
                    possibleY = y + 1;
                    if (possibleX >= 0 && possibleY <= 7) {
                        Coordinates c = new Coordinates(possibleX, possibleY);
                        if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                            coordinatesList.add(c);
                        }
                    }
                    moveCount++;
                }
            }
        } else {
            while (moveCount < 2) {
                if (moveCount == 0) {
                    possibleX = x + 1;
                    possibleY = y - 1;
                    if (possibleX <= 7 && possibleY >= 0) {
                        Coordinates c = new Coordinates(possibleX, possibleY);
                        if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                            coordinatesList.add(c);
                        }
                    }
                    moveCount++;
                }
                if (moveCount == 1) {
                    possibleX = x + 1;
                    possibleY = y + 1;
                    if (possibleX <= 7 && possibleY <= 7) {
                        Coordinates c = new Coordinates(possibleX, possibleY);
                        if (!allSameColorCoordinates.contains(new Coordinates(possibleX, possibleY))) {
                            coordinatesList.add(c);
                        }
                    }
                    moveCount++;
                }
            }
        }

        PieceAttacks pieceAttacks = new PieceAttacks();

        return pieceAttacks.pawnAttacks(coordinatesList, enemy);
    }
}
