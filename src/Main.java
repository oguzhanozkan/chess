import info.BoardInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println(" !!! puan hesaplaması yapmak için bir sayı giriniz !!! ");
            System.out.println(" !!! çıkış için 0 !!! ");
            System.out.println();

            File folder = new File("txtFolder");
            File[] listOfFiles = folder.listFiles();
            int txtFileCount = 0;
            if (listOfFiles != null) {
                txtFileCount = listOfFiles.length;
            }

            System.out.println(txtFileCount + " adet santranç tahtası vardır");
            System.out.print("puan hesaplaması yapmak için bir sayı giriniz : ");
            Scanner input = new Scanner(System.in);
            if (!input.hasNext("[0-9]+")) {
                System.out.println("sayı girmelisiniz");
            } else {
                int selectedChessBoard = input.nextInt() - 1;
                if (selectedChessBoard == -1) {
                    break;
                } else {
                    if (selectedChessBoard >= 3) {
                        System.out.println("geçersiz bir sayı girdiniz");
                    } else {
                        System.out.println();
                        System.out.println(selectedChessBoard + 1 + ". santranç tahtasını seçtiniz.");
                        File pathName = listOfFiles[selectedChessBoard];
                        Scanner scanner = null;
                        ArrayList<String> boardLine = new ArrayList<>();
                        ArrayList<ArrayList<String>> board = new ArrayList<>();
                        String line = "";
                        System.out.println("santranç tahtası : ");
                        try {
                            scanner = new Scanner(new File(String.valueOf(pathName)));
                            while (scanner.hasNextLine()) {
                                line = scanner.nextLine();
                                String[] chessPieces = line.split(" ");
                                boardLine = new ArrayList<>(Arrays.asList(chessPieces));
                                board.add(boardLine);
                                System.out.println(line);
                            }
                            scanner.close();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        BoardInfo pieceSelector = new BoardInfo();
                        System.out.println();
                        System.out.println("puanlar : " + pieceSelector.boardInfo(board).toString());
                        System.out.println("----------------------------");
                    }
                }
            }
        }
    }
}
// 1 => {totalWhitePoint=134.5, totalBlackPoint=133.5}
// 2 => {totalWhitePoint=123.0, totalBlackPoint=116.0}
// 3 => {totalWhitePoint=109.0, totalBlackPoint=108.0}