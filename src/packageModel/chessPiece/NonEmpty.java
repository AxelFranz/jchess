package packageModel.chessPiece;

import packageModel.Coord;
import packageModel.Move;
import packageModel.Piece;

import java.util.ArrayList;

public abstract class NonEmpty implements Piece {

    private final boolean white;
    private Coord pos;
    private int moved = 0;

    public NonEmpty(boolean white, Coord pos) {
        this.white = white;
        this.pos = pos;

    }

    @Override
    public boolean isWhite(){
        return white;
    }

    @Override
    public boolean isBlack(){
        return !white;
    }
    @Override
    public boolean isEmpty(){
        return false;
    }

    @Override
    public Coord getPos(){
        return pos;
    }

    @Override
    public void setPos(Coord pos) {
        this.pos = pos;
    }

    @Override
    public void incrementMoved() {
        moved++;
    }

    @Override
    public void decrementMoved() {
        moved--;
    }

    @Override
    public boolean neverMoved(){
        return moved == 0;
    }

}
