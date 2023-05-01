package packageModel.moveImplementations;

import packageModel.*;
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
        board.setPiece(dest, PieceFactory.newPiece(PieceId.EMPTY,null));
        moving.setPos(start);
        moving.decrementMoved();
    }

    @Override
    public void makeMove(Board board) {
        board.setPiece(dest, moving);
        board.setPiece(start, PieceFactory.newPiece(PieceId.EMPTY,null) /* wil soon replace empty with piece factory*/);
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
