package packageModel.chessPiece;

import packageModel.*;
import packageModel.moveImplementations.BasicMovement;


import java.util.ArrayList;

public class Pawn extends NonEmpty {

    private MoveList availableMoves;
    public Pawn(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_pawn.png";
        else this.imgPath = "/chessImage/black_pawn.png";
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        int dir = (isWhite())?(-1):(1);
        Coord.addToArray(res,getPos().addXY(-1,dir));
        Coord.addToArray(res,getPos().addXY(1,dir));
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves.addAll(genBasic(board));
        availableMoves.addAll(genCapture(board));
        Move buf = genEnPassant(board);
        if(buf != null)
            availableMoves.add(buf);
    }

    @Override
    public String name() {
        return "Pawn";
    }

    private MoveList genBasic(Board board)
    {
        Object[] moveInput = new Object[3];
        MoveList res = new MoveList();
        int dir = (isWhite())?(-1):(1);
        Coord tmp = getPos().addY(dir);
        Move toTest;
        if( board.canMove(tmp) )
        {
            moveInput[0]= this;
            moveInput[1]= tmp;
            toTest = Factory.newMove(MoveId.BASIC,moveInput);
            if(!board.isCheck(isWhite(),toTest))
                res.add(toTest);
            tmp = getPos().addY(2*dir);
            if(neverMoved() && Board.inBoard(tmp) && board.isEmptyTile(tmp) ){
                moveInput[1] = tmp;
                toTest = Factory.newMove(MoveId.BASIC,moveInput);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
            }
        }
        return res;
    }


    private MoveList genCapture(Board board){
        Object[] moveInput = new Object[4];
        MoveList res = new MoveList();
        Move toTest;
        int dir = (isWhite())?(-1):(1);
        int[] pos = {-1,1};
        for( int i:pos){
            Coord tmp = getPos().addXY(i,dir);
            if( board.canCapture(this,tmp)){
                moveInput[0] = this;
                moveInput[1] = tmp;
                moveInput[2] = board.getPiece(tmp);
                toTest = Factory.newMove(MoveId.CAPTURE,moveInput);
                if(!board.isCheck(isWhite(),toTest))
                    res.add( toTest );
            }
        }
        return res;
    }

    private Move genEnPassant(Board board){
        Move ret = null;
        Move last = board.getLastHistory();
        if(last == null)
            return null;
        if (last.moveType() == MoveId.BASIC){
            BasicMovement base = (BasicMovement) last;
            Coord dest = base.canEnPassant();
            if(dest != null){
                int[] pos = {-1,1};
                int dir = (isWhite())?(-1):(1);
                for(int i : pos){
                    if((dest.x() == getPos().x()+i) && (dest.y() == getPos().y()+dir)){
                        Object[] moveInput = new Object[3];
                        moveInput[0] = this;
                        moveInput[1] = dest;
                        moveInput[2] = last.getPiece();
                        Move enPassant = Factory.newMove(MoveId.ENPASSANT,moveInput);
                        if(!board.isCheck(isWhite(),enPassant))
                            ret = enPassant;
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public char code(){
        return (isWhite())?('P'):('p');
    }
    @Override
    public void emptyValidMoves(){
        availableMoves.clear();
    }


}
