package packageModel.moveImplementations;

import packageModel.*;

public class CastlingMovement implements Move {
    private final Piece king;
    private final Coord start;
    private final Coord dest;
    private final Piece rook;
    private final Coord rStart;
    private final Coord rDest;

    public CastlingMovement(Piece king, Coord dest, Piece rook, Coord rEnd) {
        this.king = king;
        start = king.getPos();
        this.dest = dest;
        this.rook = rook;
        rStart = rook.getPos();
        this.rDest = rEnd;
    }

    @Override
    public void undoMove(Board board) {
        board.setPiece(start,king);
        king.decrementMoved();
        board.setPiece(rStart,rook);
        rook.decrementMoved();
        board.setPiece(dest, Factory.newPiece(PcId.EMPTY,null));
        board.setPiece(rDest, Factory.newPiece(PcId.EMPTY,null));
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest, king);
        king.incrementMoved();
        board.setPiece(rDest, rook);
        rook.incrementMoved();
        board.setPiece(start, Factory.newPiece(PcId.EMPTY,null));
        board.setPiece(rStart, Factory.newPiece(PcId.EMPTY,null));
    }

    public String toString(){
        return king + " castled with" + rook + " : " + king + " " + start +  " -> " + dest + " " + rook + " " + rStart + " " + rDest;
    }
    @Override
    public boolean compareDest(Coord dest){
        return dest.x() == this.dest.x() && dest.y() == this.dest.y();
    }


    @Override
    public MoveId moveType(){
        return MoveId.CASTLING;
    }
    @Override
    public Coord getDest(){
        return dest;
    }

    @Override
    public Piece getPiece() {
        return king;
    }
    @Override
    public Coord getStart(){
        return start;
    }

    public Piece getRook(){
        return rook;
    }

    public Coord getrStart(){
        return rStart;
    }

    public Coord getrDest(){
        return rDest;
    }

    @Override
    public boolean compare(Move cmp){
        if(moveType() == cmp.moveType()){
            CastlingMovement test = (CastlingMovement) cmp;
            return (test.getStart().equals(start) && test.getDest().equals(dest) && (test.getPiece()==king) && (test.getRook()==rook) && test.getrStart().equals(rStart) && test.getrDest().equals(rDest));
        }
        return false;
    }
}
