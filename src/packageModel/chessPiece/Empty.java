package packageModel.chessPiece;

import packageModel.Coord;
import packageModel.Piece;

import java.util.List;

public class Empty extends Piece {

    public Boolean checkMove(Coord x) {
        return null;
    }

    public List<Coord> genAllMoves() {
        return null;
    }

    public Boolean checkCapture(Coord x) {
        return null;
    }

    public List<Coord> genValidCapture() {
        return null;
    }


    public Boolean isEmpty() {
        return true;
    }

    public String toString() {
        return null;/* should be defined */
    }
}
