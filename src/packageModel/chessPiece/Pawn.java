package packageModel.chessPiece;

import packageModel.Coord;
import packageModel.Piece;

import java.util.List;

public class Pawn extends Piece {
    public Boolean checkMove(Coord x, Piece[][] board) {
        return null;
    }

    public List<Coord> genAllMoves() {
        return null;
    }

    public Boolean checkCapture(Coord x, Piece[][] board) {
        return null;
    }

    public List<Coord> genValidCapture() {
        return null;
    }

    public Boolean isEmpty() { return false; }

    public String toString() {
        return null;/* should be defined */
    }
}
