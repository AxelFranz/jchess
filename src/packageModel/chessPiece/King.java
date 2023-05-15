package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class King extends NonEmpty {

    private MoveList availableMoves;
    public King(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_king.png";
        else this.imgPath = "/chessImage/black_king.png";
    }
    @Override
    public boolean isKing(){
        return true;
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        for(int i = 0; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(i != 1 || j != 1 )
                    Coord.addToArray(res,getPos().addXY(i-1,j-1));
            }
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);

    }

    @Override
    public String name() {
        return "King";
    }

    private MoveList genAll(Board board){
        MoveList res = new MoveList();
        Object[] builder = new Object[4];
        Coord tmp ;
        Move toTest;
        builder[0] = this;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if( i != 1 || j != 1){
                    tmp = getPos().addXY(i-1,j-1);

                    if(board.canMove(tmp)){
                        builder[1] = tmp;
                        toTest = Factory.newMove(MoveId.BASIC,builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);

                    } else if (board.canCapture(this,tmp)) {
                        builder[1] = tmp;
                        builder[2] = board.getPiece(tmp);
                        toTest = Factory.newMove(MoveId.CAPTURE,builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }

                }
            }
        }
        return res;
    }
    @Override
    public char code(){
        return (isWhite())?('K'):('k');
    }
    @Override
    public void emptyValidMoves(){
        availableMoves.clear();
    }

}
