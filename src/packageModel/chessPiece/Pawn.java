package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Move;
import packageModel.MoveFactory;


import java.util.ArrayList;

public class Pawn extends NonEmpty {

    private ArrayList<Move> availableMoves;
    public Pawn(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new ArrayList<>();
    }

    @Override
    public ArrayList<Move> getValidMoves() {
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

    private ArrayList<Move> genBasic(Board board)
    {
        Object moveInput[] = new Object[3];
        ArrayList<Move> res = new ArrayList<>();
        int dir = (this.isWhite())?(-1):(1);
        Coord tmp = getPos().addY(dir);
        Move toTest;
        if( board.canMove(tmp) )
        {
            moveInput[0]= this;
            moveInput[1]= getPos();
            moveInput[2]= tmp;
            toTest = MoveFactory.newMove("basic",moveInput);
            if(!board.isCheck(isWhite(),toTest))
                res.add(toTest);
            tmp = getPos().addY(2*dir);
            if(neverMoved() && Board.inBoard(tmp) && !board.isEmptyTile(tmp) ){
                moveInput[2] = tmp;
                res.add(MoveFactory.newMove("basic",moveInput));
            }
        }
        return res;
    }


    private ArrayList<Move> genCapture(Board board){
        Object moveInput[]  = new Object[4];
        ArrayList<Move> res = new ArrayList<>();
        Move toTest;
        int dir = (this.isWhite())?(-1):(1);
        int pos[] = {-1,1};
        for( int i:pos){
            Coord tmp = getPos().addXY(i,dir);
            if( board.canCapture(this,tmp)){
                moveInput[0] = this;
                moveInput[1] = getPos();
                moveInput[2] = tmp;
                moveInput[3] = board.getPiece(tmp);
                toTest = MoveFactory.newMove("capture",moveInput);
                if(!board.isCheck(isWhite(),toTest))
                    res.add( toTest );
            }
        }
        return res;
    }
}
