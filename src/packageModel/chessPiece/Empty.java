package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Empty implements Piece {
    public Empty(){

    }

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
    public boolean isKing() {
        return false;
    }

    @Override
    public ArrayList<Move> getValidMoves() {
        return null;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
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

    @Override
    public String name() {
        return "Empty tile";
    }

    @Override
    public char code(){
        return ' ';
    }
}
