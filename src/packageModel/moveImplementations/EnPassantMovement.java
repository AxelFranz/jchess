package packageModel.moveImplementations;

import packageModel.*;

public class EnPassantMovement implements Move {
    private final Piece pawn;
    private final Coord start;
    private final Coord dest;
    private final Piece pawn2;
    private final Coord pos;

    public EnPassantMovement(Piece pawn, Coord dest, Piece pawn2)
    {
        this.pawn = pawn;
        start = pawn.getPos();
        this.dest = dest;
        this.pawn2 = pawn2;
        pos = pawn2.getPos();

    }
    @Override
    public void undoMove(Board board) {
        board.setPiece(start,pawn);
        pawn.decrementMoved();
        board.setPiece(pos,pawn2);
        board.setPiece(dest, Factory.newPiece(PcId.EMPTY,null));

    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest,pawn);
        pawn.incrementMoved();
        board.setPiece(pos, Factory.newPiece(PcId.EMPTY,null));
        board.setPiece(start,Factory.newPiece(PcId.EMPTY, null));
    }

    public String toString(){
        return pawn + " captured En passant " + pawn2 + " : " + start +  " -> " + dest + " passing by " + pos;
    }
    @Override
    public boolean compareDest(Coord dest){
        return dest == this.dest;
    }

    @Override
    public MoveId moveType(){
        return MoveId.ENPASSANT;
    }
    @Override
    public Coord getDest(){
        return dest;
    }

    @Override
    public Piece getPiece() {
        return pawn;
    }


    @Override
    public Coord getStart() {
        return start;
    }

    public Piece getPawn2() {
        return pawn2;
    }

    public Coord getPos() {
        return pos;
    }

    @Override
    public boolean compare(Move cmp){
        if(moveType() == cmp.moveType()){
            EnPassantMovement test = (EnPassantMovement) cmp;
            return (test.getStart().equals(start) && test.getDest().equals(dest) && test.getPiece()==pawn && test.getPos().equals(pos) && test.getPawn2()==pawn2);
        }
        return false;
    }
}
