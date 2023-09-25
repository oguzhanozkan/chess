package model;

public enum PieceMark {

    PAWN(1),
    BISHOP(3),
    KNIGHT(3),
    ROOK(5),
    QUEEN(9),
    KING(100);

    public float marks;

    PieceMark(float marks) {
        this.marks = marks;
    }
}