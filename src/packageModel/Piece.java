package packageModel;

import packageModel.Coord;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private Boolean color;
    private String imgPiece; /* idk if we keep it or put Image class instead */
    private List<Coord> moveList;

    public abstract Boolean checkMove(Coord x); /* False if not possible */
    public abstract List<Coord> genAllMoves(); /* Returns all possible move */
    public abstract Boolean checkCapture(Coord x);
    public abstract List<Coord> genValidCapture();
    public String toString(){
        return ""; /*  should be overwritten in each children */
    }

    public Coord getCoords() {
        return coords;
    }

    public void setCoords(Coord coords) {
        this.coords = coords;
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
