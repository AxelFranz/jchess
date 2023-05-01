package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class King extends NonEmpty {

    private ArrayList<Move> availableMoves;
    public King(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new ArrayList<>();
    }
    @Override
    public boolean isKing(){
        return true;
    }

    @Override
    public ArrayList<Move> getValidMoves() {
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

    private ArrayList<Move> genAll(Board board){
        ArrayList<Move> res = new ArrayList<>();
        Object builder[] = new Object[4];
        Coord tmp ;
        Move toTest;
        builder[0] = this;
        builder[1] = getPos();
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if( i != 1 || j != 1){
                    tmp = getPos().addXY(i-1,j-1);

                    if(board.canMove(tmp)){
                        builder[2] = tmp;
                        toTest = MoveFactory.newMove("basic",builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);

                    } else if (board.canCapture(this,tmp)) {
                        builder[2] = tmp;
                        builder[3] = board.getPiece(tmp);
                        toTest = MoveFactory.newMove("capture",builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }

                }
            }
        }
        return res;
    }

}
