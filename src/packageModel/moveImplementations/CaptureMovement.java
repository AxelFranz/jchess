package packageModel.moveImplementations;

import packageModel.*;
import packageModel.chessPiece.Empty;

public class CaptureMovement implements Move {
    public final Piece moving;
    public final Coord start;
    public final Coord dest;
    public final Piece captured;
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
    public boolean compareDest(Coord dest){
        return dest == this.dest;
    }

    @Override
    public MoveId moveType(){
        return MoveId.CAPTURE;
    }
    @Override
    public Coord getDest(){
        return dest;
    }

}
