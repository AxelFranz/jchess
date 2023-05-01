package packageModel.moveImplementations;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Move;
import packageModel.Piece;
import packageModel.chessPiece.Empty;

public class CaptureMovement implements Move
{
    private final Piece moving;
    private final Coord start;
    private final Coord dest;
    private final Piece captured;
    public CaptureMovement(Piece moving, Coord start, Coord dest, Piece captured)
    {
        this.moving = moving;
        this.start = start;
        this.dest = dest;
        this.captured = captured;
    }

    @Override
    public void undoMove(Board board) {
        board.setPiece(start,moving);
        board.setPiece(dest,captured);
        moving.setPos(start);
        captured.setPos(dest);
        moving.decrementMoved();
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest,moving);
        board.setPiece(start,new Empty());
        moving.setPos(dest);
        captured.setPos(null);
        moving.incrementMoved();
    }

    public String toString(){
        if(captured.isEmpty())
            return moving + " : " + start +  " -> " + dest;
        return moving + " captured " + captured + " : " + start +  " -> " + dest;
    }

    @Override
    public Coord getCapturedPos() {
        return dest;
    }
}
