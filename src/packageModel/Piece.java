package packageModel;

import packageModel.Coord;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    private final Boolean color;
    private final String imgPiece; /* idk if we keep it or put Image class instead */
    private Coord pos;
    private ArrayList<Coord> moveList;

    public Piece(Boolean color, String imgPiece, Coord pos)
    {
        this.color = color;
        this.imgPiece = imgPiece;
        this.pos = pos;
        moveList = new ArrayList<Coord>();
    }

    public Boolean checkMove(Coord x {
        return moveList.contains(x);
    }
    public abstract List<Coord> genMoves(Board board); /* Returns all possible move */
    public abstract List<Coord> genValidCapture(Board board);

    public void setAllMoves(Board board)
    {
        moveList.clear();
        moveList.addAll(genMoves(board));
        moveList.addAll(genValidCapture(board));
    }
    public abstract Boolean isEmpty() ;
    public abstract String toString() ;

    public Boolean getColor() {
        return color;
    }
    public Coord getPos() {
        return pos;
    }

    public String getImgPiece() {
        return imgPiece;
    }

    public List<Coord> getMoveList() {
        return moveList;
    }

    public void setMoveList(ArrayList<Coord> moveList) {
        this.moveList = moveList;
    }

    public void setPos(Coord pos) {
        this.pos = pos;
    }

}
