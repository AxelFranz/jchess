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
        ArrayList<Coord> res = new ArrayList<>();
        int dir = (isWhite())?(-1):(1);
        for(int i = 1; i < 3 ; i++){
            Coord forward = new Coord(getPos().x(),i*dir);
            if (Board.inBoard(forward) && !board.getPiece(forward).isEmpty() )
                return res;
        }
        for(int i = -1 ; i < 2 ; i++){
            Coord cap = new Coord(getPos().x() + i,getPos().y()+3);
            if( Board.inBoard(cap))
                res.add(cap);
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
    }
    
    private MoveList genAll(Board board){
        
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
        return (isWhite())?('L'):('l');
    }


}
