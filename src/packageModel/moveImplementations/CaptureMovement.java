package packageModel.moveImplementations;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Move;
import packageModel.Piece;
import packageModel.chessPiece.Empty;

public class CaptureMovement implements Move {
    private final Piece moving;
    private final Coord start;
    private final Coord dest;
    private final Piece captured;
    public CaptureMovement(Piece moving, Coord dest, Piece captured) {
        this.moving = moving;
        start = moving.getPos();
        this.dest = dest;
        this.captured = captured;
    }

    @Override
    public void undoMove(Board board) {
        board.setPiece(start,moving);
        board.setPiece(dest,captured);
        moving.decrementMoved();
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest,moving);
        board.setPiece(start,new Empty());
        captured.setPos(null);
        moving.incrementMoved();
    }

    public String toString(){
        return moving + " captured " + captured + " : " + start +  " -> " + dest;
    }

    @Override
    public Coord getCapturedPos() {
        return dest;
    }
}
