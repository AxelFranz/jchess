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
        board.setPiece(dest,Factory.newPiece(PcId.EMPTY,null));
        board.setPiece(dest,captured);
        moving.decrementMoved();
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest, Factory.newPiece(PcId.EMPTY,null));
        board.setPiece(dest,moving);
        board.setPiece(start,Factory.newPiece(PcId.EMPTY,null));
        captured.setPos(null);
        moving.incrementMoved();
    }

    public String toString(){
        return moving + " captured " + captured + " : " + start +  " -> " + dest;
    }
    @Override
    public boolean compareDest(Coord dest){
        return dest.x() == this.dest.x() && dest.y() == this.dest.y();
    }


    @Override
    public MoveId moveType(){
        return MoveId.CAPTURE;
    }
    @Override
    public Coord getDest(){
        return dest;
    }
    @Override
    public Piece getPiece() {
        return moving;
    }
    @Override
    public Coord getStart(){
        return start;
    }
    public Piece getCaptured(){
        return captured;
    }
    @Override
    public boolean compare(Move cmp){
        if(moveType() == cmp.moveType()){
            CaptureMovement test = (CaptureMovement) cmp;
            return (test.getStart().equals(start) && test.getDest().equals(dest) && (test.getPiece()==moving) && (test.getCaptured()==captured));
        }
        return false;
    }

}
