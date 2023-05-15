package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.MoveList;
import packageModel.PcId;

import java.util.ArrayList;

public class Mortar extends NonEmpty{
    private MoveList availableMoves;
    public Mortar(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "";
        else this.imgPath = "";
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
    public void emptyValidMoves() {

    }

    @Override
    public PcId getId() {
        return PcId.MORTAR;
    }

    @Override
    public char code() {
        return (isWhite())?('M'):('m');
    }
}
