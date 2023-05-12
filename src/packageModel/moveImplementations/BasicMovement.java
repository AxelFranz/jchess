package packageModel.moveImplementations;

import packageModel.*;

public class BasicMovement implements Move {
    public final Piece moving;
    public final Coord start;
    public final Coord dest;
    public BasicMovement(Piece moving, Coord dest)
    {
        this.moving = moving;
        start = Coord.addCoord(moving.getPos(), new Coord(0,0));
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
    public Coord getCapturedPos() {
        return null;
    }

}
