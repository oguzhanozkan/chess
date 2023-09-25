package model;

public class Piece {

    String name;
    Color color;
    PieceMark pieceMarks;
    public Piece() {
    }

    public Piece(String name, Color color, PieceMark pieceMark) {
        this.name = name;
        this.color = color;
        this.pieceMarks = pieceMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public PieceMark getPieceMarks() {
        return pieceMarks;
    }

    public void setPieceMarks(PieceMark pieceMarks) {
        this.pieceMarks = pieceMarks;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
