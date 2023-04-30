package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Empty implements Piece {

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean neverMoved() {
        return false;
    }

    @Override
    public void setValidMoves(Board board) {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public ArrayList<Move> getValidMoves() {
        return null;
    }

    @Override
    public ArrayList<Coord> allCapturePos() {
        return null;
    }

    @Override
    public Coord getPos() {
        return null;
    }

    @Override
    public void setPos(Coord pos) {
    }

    @Override
    public void incrementMoved() {
    }

    @Override
    public void decrementMoved() {
    }

    public String toString(){
        return "empty";
    }
}
