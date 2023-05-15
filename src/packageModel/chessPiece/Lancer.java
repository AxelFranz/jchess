package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.MoveList;
import packageModel.PcId;

import java.util.ArrayList;

public class Lancer extends NonEmpty{

    private MoveList availableMoves;
    public Lancer(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = null/* to set  */;
        else this.imgPath = null/* to set */;

    }
    @Override
    public MoveList getValidMoves() {
        return availableMoves;
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
        availableMoves.clear();
    }

    @Override
    public PcId getId() {
        return PcId.LANCER;
    }

    @Override
    public char code() {
        return 0;
    }


}
