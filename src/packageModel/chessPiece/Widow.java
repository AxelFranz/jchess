package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.MoveList;
import packageModel.PcId;

import java.util.ArrayList;

public class Widow extends NonEmpty{
    private MoveList availableMoves;
    public Widow(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "";
        else this.imgPath = "";
    }

    @Override
    public String name() {
        return "Widow";
    }

    @Override
    public char code(){
        return (isWhite())?('W'):('w');
    }

    @Override
    public MoveList getValidMoves() {
        return null;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        return null;
    }

    @Override
    public void setValidMoves(Board board) {

    }

    @Override
    public void emptyValidMoves(){
        availableMoves.clear();
    }
    @Override
    public PcId getId(){
        return PcId.WIDOW;
    }
}
