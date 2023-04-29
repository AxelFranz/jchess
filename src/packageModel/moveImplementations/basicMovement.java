package packageModel.moveImplementations;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Move;
import packageModel.Piece;
import packageModel.chessPiece.Empty;

public class basicMovement implements Move {
    private final Piece moving;
    private final Coord start;
    private final Coord dest;
    public basicMovement(Piece moving, Coord start, Coord dest)
    {
        this.moving = moving;
        this.start = start;
        this.dest = dest;
    }
    @Override
    public void undoMove(Board board) {
        board.setPiece(start, moving);
        board.setPiece(dest,new Empty());
        moving.setPos(start);
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest, moving);
        board.setPiece(start, new Empty() /* wil soon replace empty with piece factory*/);
    }

    @Override
    public String toString()
    {
        return moving + " : " + start + " -> " + dest;
    }
}
