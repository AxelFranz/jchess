package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Piece;

import java.util.List;

public class King extends Piece {

    public Boolean checkMove(Coord x, Piece[][] board) {
        return null;
    }


    @Override
    public List<Coord> genMoves(Board board) {
        return null;
    }

    @Override
    public List<Coord> genValidCapture(Board board) {
        return null;
    }

    public Boolean isEmpty() {
        return false;
    }

    public String toString() {
        return null;/* should be defined */
    }
}
