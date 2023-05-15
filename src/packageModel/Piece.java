package packageModel;

import java.util.ArrayList;

public interface Piece {
    public boolean isWhite();
    public boolean isBlack();
    public boolean isEmpty();
    public boolean isKing();
    public MoveList getValidMoves();
    public ArrayList<Coord> allCapturePos(Board board);
    public Coord getPos();
    public void setPos(Coord pos);
    public void incrementMoved();
    public void decrementMoved();
    public boolean neverMoved();
    public void setValidMoves(Board board);
    public void emptyValidMoves();
    public String toString();
    public String name();
    public char code();
    /*
    public boolean cmpPiece(Piece cmp);
     */










}
