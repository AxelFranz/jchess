package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;
import java.util.Queue;

public class Widow extends NonEmpty{
    private MoveList availableMoves;
    private final Piece queen,knight;
    public Widow(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        Object[] init = {white,pos};
        queen = Factory.newPiece(PcId.QUEEN,init);
        knight = Factory.newPiece(PcId.QUEEN,init);
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
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        queen.setPos(getPos());
        knight.setPos(getPos());
        ArrayList<Coord> res = new ArrayList<>();
        res.addAll(queen.allCapturePos(board));
        res.addAll(knight.allCapturePos(board));
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        queen.setValidMoves(board);
        knight.setValidMoves(board);
        availableMoves = (MoveList) queen.getValidMoves().clone();
        availableMoves.addAll(knight.getValidMoves());
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
