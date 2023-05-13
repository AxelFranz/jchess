package packageModel.chessPiece;

import packageModel.*;


import java.util.ArrayList;

public class Pawn extends NonEmpty {

    private MoveList availableMoves;
    public Pawn(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        int dir = (this.isWhite())?(-1):(1);
        Coord.addToArray(res,getPos().addXY(-1,dir));
        Coord.addToArray(res,getPos().addXY(1,dir));
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves.addAll(genBasic(board));
        availableMoves.addAll(genCapture(board));
    }

    @Override
    public String name() {
        return "Pawn";
    }

    private MoveList genBasic(Board board)
    {
        Object[] moveInput = new Object[3];
        MoveList res = new MoveList();
        int dir = (this.isWhite())?(-1):(1);
        Coord tmp = getPos().addY(dir);
        Move toTest;
        if( board.canMove(tmp) )
        {
            moveInput[0]= this;
            moveInput[1]= tmp;
            toTest = Factory.newMove("basic",moveInput);
            if(!board.isCheck(isWhite(),toTest))
                res.add(toTest);
            tmp = getPos().addY(2*dir);
            if(neverMoved() && Board.inBoard(tmp) && board.isEmptyTile(tmp) ){
                moveInput[1] = tmp;
                res.add(Factory.newMove("basic",moveInput));
            }
        }
        return res;
    }


    private MoveList genCapture(Board board){
        Object[] moveInput = new Object[4];
        MoveList res = new MoveList();
        Move toTest;
        int dir = (this.isWhite())?(-1):(1);
        int pos[] = {-1,1};
        for( int i:pos){
            Coord tmp = getPos().addXY(i,dir);
            if( board.canCapture(this,tmp)){
                moveInput[0] = this;
                moveInput[1] = tmp;
                moveInput[2] = board.getPiece(tmp);
                toTest = Factory.newMove("capture",moveInput);
                if(!board.isCheck(isWhite(),toTest))
                    res.add( toTest );
            }
        }
        return res;
    }

    @Override
    public char code(){
        return (isWhite())?('P'):('p');
    }
}
