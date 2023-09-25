package attack;

import model.Coordinates;
import util.Utilities;

import java.util.*;

public class PieceAttacks {


    public HashSet<Coordinates> rookAttacks(Coordinates coordinates, List<Coordinates> rookCoordinates, List<Coordinates> enemyCoordinates) {

        /*
            1. durum => durum sutun sabit kalırken satir azalacak
            2. durum => durum sutun sabit kalırken satir artacak
            3. durum => durum satir sabit kalırken sutun artacak
            4. durum => durum satir sabit kalırken sutun azalacak
         */

        // y eksenindeki rakip taşlar
        List<Coordinates> lines = new ArrayList<>();

        // x eksenindeki rakip taşlar
        List<Coordinates> columns = new ArrayList<>();

        // kale hareket edebiliyor kendi renginde ki taşlar tarafından bloklanmıyor
        if (rookCoordinates.size() > 0) {
            for (int i = 0; i < rookCoordinates.size(); i++) {
                // kalenin gidebileceği yerde rakip taş var mı yok mu durumunun kontrolü
                if (enemyCoordinates.contains(rookCoordinates.get(i))) {

                    int x = coordinates.getX();
                    int y = coordinates.getY();

                    if (x == rookCoordinates.get(i).getX()) {
                        lines.add(rookCoordinates.get(i));
                        //System.out.println("x ekseninde ki rakipleri : " + rookCoordinates.get(i));
                    }
                    if (y == rookCoordinates.get(i).getY()) {
                        columns.add(rookCoordinates.get(i));
                        //System.out.println("y ekseninde ki rakipleri : " + rookCoordinates.get(i));
                    }

                }

            }

        }

        /*
            k => kale
            r => rakip

            r - r - k - r durumu için;
            bu satır sabit sutun değişiyor r lerden enbuyuk ve en kucuk sutundeğerini bulursak kalenin
            bir hamlede tehtit ettiği taşları bulmuş olurum
         */

        // coordinates den büyük olanlar
        Set<Coordinates> attackColumnBiggestHashset = new HashSet<>();
        Set<Coordinates> attackColumnSmallestHashset = new HashSet<>();

        Set<Coordinates> attackLineBiggestHashset = new HashSet<>();
        Set<Coordinates> attackLineSmallestHashset = new HashSet<>();

        String sX = String.valueOf(coordinates.getX());
        String sY = String.valueOf(coordinates.getY());
        String s = sX + sY;
        int sInt = Integer.parseInt(s);

        if (columns.size() > 0) {
            for (int i = 0; i < columns.size(); i++) {
                String sS = columns.get(i).getX() + "" + columns.get(i).getY();
                int cInt = Integer.parseInt(sS);
                if (sInt < cInt) {
                    attackColumnBiggestHashset.add(columns.get(i));
                } else if (sInt > cInt) {
                    attackColumnSmallestHashset.add(columns.get(i));
                }
            }
        }

        if (lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                String sS = lines.get(i).getX() + "" + lines.get(i).getY();
                int cInt = Integer.parseInt(sS);
                if (sInt < cInt) {
                    attackLineBiggestHashset.add(lines.get(i));
                } else if (sInt > cInt) {
                    attackLineSmallestHashset.add(lines.get(i));
                }
            }
        }


        HashSet<Coordinates> pieceAttackCoordinates = new HashSet<>();
        pieceAttackCoordinates.add(Utilities.findSmallest(attackColumnBiggestHashset));
        pieceAttackCoordinates.add(Utilities.findBiggest(attackColumnSmallestHashset));
        pieceAttackCoordinates.add(Utilities.findSmallest(attackLineBiggestHashset));
        pieceAttackCoordinates.add(Utilities.findBiggest(attackLineSmallestHashset));
        pieceAttackCoordinates.remove(null);

        return pieceAttackCoordinates;
    }

    public HashSet<Coordinates> bishopAttacks(Coordinates coordinates, List<Coordinates> bishopCoordinates, List<Coordinates> enemyCoordinates) {

        Set<Coordinates> firstRegion = new HashSet<>();
        Set<Coordinates> secondRegion = new HashSet<>();
        Set<Coordinates> thirdRegion = new HashSet<>();
        Set<Coordinates> fourthRegion = new HashSet<>();


        int x = coordinates.getX();
        int y = coordinates.getY();
        String coordinatesString = x + "" + y;
        int coordinatesInt = Integer.parseInt(coordinatesString);


        if (bishopCoordinates.size() > 0) {
            for (int i = 0; i < bishopCoordinates.size(); i++) {
                if (enemyCoordinates.contains(bishopCoordinates.get(i))) {
                    int xB = bishopCoordinates.get(i).getX();
                    int yB = bishopCoordinates.get(i).getY();
                    String coordinatesStringB = xB + "" + yB;
                    int coordinatesIntB = Integer.parseInt(coordinatesStringB);

                    if (coordinatesInt > coordinatesIntB) {
                        int different = coordinatesInt - coordinatesIntB;
                        if (different % 9 == 0) {
                            firstRegion.add(bishopCoordinates.get(i));
                        }
                        if (different % 11 == 0) {
                            secondRegion.add(bishopCoordinates.get(i));
                        }
                    } else {
                        int different = coordinatesIntB - coordinatesInt;
                        if (different % 9 == 0) {
                            thirdRegion.add(bishopCoordinates.get(i));
                        }
                        if (different % 11 == 0) {
                            fourthRegion.add(bishopCoordinates.get(i));
                        }
                    }

                }
            }
        }

        HashSet<Coordinates> pieceAttackCoordinates = new HashSet<>();
        pieceAttackCoordinates.add(Utilities.findBiggest(firstRegion));
        pieceAttackCoordinates.add(Utilities.findBiggest(secondRegion));
        pieceAttackCoordinates.add(Utilities.findSmallest(thirdRegion));
        pieceAttackCoordinates.add(Utilities.findSmallest(fourthRegion));

        pieceAttackCoordinates.remove(null);

        return pieceAttackCoordinates;

    }

    public HashSet<Coordinates> knightAttacks(List<Coordinates> knightCoordinates, List<Coordinates> enemyCoordinates) {
        HashSet<Coordinates> pieceAttackCoordinates = new HashSet<>();

        for (int i = 0; i < knightCoordinates.size(); i++) {
            if (enemyCoordinates.contains(knightCoordinates.get(i))) {
                pieceAttackCoordinates.add(knightCoordinates.get(i));
            }
        }
        pieceAttackCoordinates.remove(null);
        return pieceAttackCoordinates;
    }

    public HashSet<Coordinates> pawnAttacks(List<Coordinates> pawnCoordinates, List<Coordinates> enemyCoordinates) {

        HashSet<Coordinates> pieceAttackCoordinates = new HashSet<>();

        for (int i = 0; i < pawnCoordinates.size(); i++) {
            if (enemyCoordinates.contains(pawnCoordinates.get(i))) {
                pieceAttackCoordinates.add(pawnCoordinates.get(i));
            }
        }
        pieceAttackCoordinates.remove(null);
        return pieceAttackCoordinates;
    }
}
