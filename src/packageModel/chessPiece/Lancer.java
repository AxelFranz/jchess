package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Lancer extends NonEmpty{

    private MoveList availableMoves;
    public Lancer(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "chessPiece/white_lancer.png";
        else this.imgPath = "chessPiece/black_lancer.png";

    }
    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        int dir = (isWhite())?(-1):(1);

        Coord forward = new Coord(getPos().x(),dir);
        if (Board.inBoard(forward) && !board.getPiece(forward).isEmpty() )
            return res;
        for(int i = -1 ; i < 2 ; i+=2){
            Coord cap = new Coord(getPos().x() + i,getPos().y()+2);
            if( Board.inBoard(cap))
                res.add(cap);
        }
        forward = forward.addY(1);
        if (Board.inBoard(forward) && !board.getPiece(forward).isEmpty() )
            return res;
        Coord cap = new Coord(getPos().x(),getPos().y()+3);
        if( Board.inBoard(cap))
            res.add(cap);
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);
    }
    
    private MoveList genAll(Board board){
        Object[] init = new Object[3];
        MoveList res = new MoveList();
        int dir = (isWhite())?(-1):(1);
        int basReached = 0;
        Coord tmp;
        Move toTest;
        boolean mv = true;
        /* basic move part */
        while(mv && basReached < 2){
            mv = false;
            tmp = getPos().addY((basReached+1)*dir);
            if(board.canMove(tmp)){
                init[0]= this;
                init[1]= tmp;
                toTest = Factory.newMove(MoveId.BASIC,init);
                if(!board.isCheck(isWhite(),toTest)){
                    res.add(toTest);
                    basReached++;
                    mv = true;
                }
            }
        }
        if(basReached >= 1) {
            for(int side = -1; side < 2 ; side += 2){
                tmp = getPos().addXY(side,2*dir);
                if( board.canCapture(this,tmp)){
                    init[0] = this;
                    init[1] = tmp;
                    init[2] = board.getPiece(tmp);
                    toTest = Factory.newMove(MoveId.CAPTURE,init);
                    if(!board.isCheck(isWhite(),toTest))
                        res.add( toTest );
                }
            }
        }
        if ( basReached == 2){
            tmp = getPos().addY(3*dir);
            if( board.canCapture(this,tmp)){
                init[0] = this;
                init[1] = tmp;
                init[2] = board.getPiece(tmp);
                toTest = Factory.newMove(MoveId.CAPTURE,init);
                if(!board.isCheck(isWhite(),toTest))
                    res.add( toTest );
            }
        }
        return res;
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
    @Override
    public String name() {
        return "Lancer";
    }


}
