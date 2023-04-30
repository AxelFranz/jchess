package packageModel;

import packageModel.Coord;

import java.util.ArrayList;
import java.util.List;

public interface Piece {
    public boolean isWhite();
    public boolean isBlack();
    public boolean isEmpty();
    public ArrayList<Move> getValidMoves();
    public ArrayList<Coord> allCapturePos();
    public Coord getPos();
    public void setPos(Coord pos);
    public void incrementMoved();
    public void decrementMoved();
    public boolean neverMoved();
    public void setValidMoves(Board board);










}
