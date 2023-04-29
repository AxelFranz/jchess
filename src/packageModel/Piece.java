package packageModel;

import packageModel.Coord;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    public Boolean color;
    private String imgPiece; /* idk if we keep it or put Image class instead */
    private List<Coord> moveList;

    public abstract Boolean checkMove(Coord x); /* False if not possible */
    public abstract List<Coord> genAllMoves(); /* Returns all possible move */
    public abstract Boolean checkCapture(Coord x); /* False if the capture isn't possible */
    public abstract List<Coord> genValidCapture();
    public abstract Boolean isEmpty() ;
    public abstract String toString() ;

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public String getImgPiece() {
        return imgPiece;
    }

    public void setImgPiece(String imgPiece) {
        this.imgPiece = imgPiece;
    }

    public List<Coord> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Coord> moveList) {
        this.moveList = moveList;
    }

}
