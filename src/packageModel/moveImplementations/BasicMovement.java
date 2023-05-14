package packageModel.moveImplementations;

import packageModel.*;

import java.util.Objects;

public class BasicMovement implements Move {
    private final Piece moving;
    private final Coord start;
    private final Coord dest;

    public BasicMovement(Piece moving, Coord dest)
    {
        this.moving = moving;
        start = Coord.addCoord(moving.getPos(), new Coord(0,0));
        this.dest = dest;
    }
    public BasicMovement(Piece moving, Coord dest,Coord start){
        this.moving = moving;
        this.start = start;
        this.dest = dest;
    }
    @Override
    public void undoMove(Board board) {
        Piece empty = Factory.newPiece(PcId.EMPTY,null);
        board.setPiece(start, moving);
        board.setPiece(dest, empty);
        moving.decrementMoved();
    }

    @Override
    public void makeMove(Board board) {
        Piece empty = Factory.newPiece(PcId.EMPTY,null);
        board.setPiece(dest, moving);
        board.setPiece(start, empty);
        moving.incrementMoved();
    }

    @Override
    public String toString()
    {
        return moving + " : " + start + " -> " + dest;
    }
    @Override
    public boolean compareDest(Coord dest){
        return dest == this.dest;
    }

    @Override
    public MoveId moveType(){
        return MoveId.BASIC;
    }

    @Override
    public Coord getDest(){
        return dest;
    }

    @Override
    public Piece getPiece() {
        return moving;
    }
    public Coord canEnPassant(){
        if(moving.name().equalsIgnoreCase("Pawn")){
            int dir = 1;
            if(moving.isWhite())
                dir = -1;
            if( (start.x() == dest.x()) && (start.y() + 2*dir) == dest.y())
                return start.addY(dir);
        }
        return null;
    }
}
