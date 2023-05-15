package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Bishop extends NonEmpty {

    private MoveList availableMoves;
    public Bishop(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_bishop.png";
        else this.imgPath = "/chessImage/black_bishop.png";

    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        ArrayList<Coord> direction = new ArrayList<>();
        for(int i = -1 ; i < 2 ; i += 2){
            for(int j = -1 ; j < 2 ; j += 2){
                direction.add(new Coord(i,j));
            }
        }
        Coord start = getPos(), tmp;
        for(Coord dir: direction){
            tmp = start;
            do {
                tmp = Coord.addCoord(tmp,dir);
                Coord.addToArray(res,tmp);
            }
            while(board.canMove(tmp));
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);
    }


    @Override
    public String name(){
        return "Bishop";
    }

    private MoveList genAll(Board board){
        MoveList res = new MoveList();
        ArrayList<Coord> direction = new ArrayList<>();
        for(int i = -1 ; i < 2 ; i += 2){
            for(int j = -1 ; j < 2 ; j += 2){
                direction.add(new Coord(i,j));
            }
        }
        Object[] builder = new Object[4];
        builder[0] = this;
        Coord start = getPos();
        Coord tmp;
        Move toTest;
        for( Coord dir: direction) {

            tmp = Coord.addCoord(start,dir);

            while(board.canMove(tmp)){
                builder[1] = tmp;
                toTest = Factory.newMove(MoveId.BASIC,builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
                tmp = Coord.addCoord(tmp,dir);
            }

            if(board.canCapture(this,tmp)){

                builder[1] = tmp;
                builder[2] =  board.getPiece(tmp);
                toTest = Factory.newMove(MoveId.CAPTURE,builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
            }
        }
        return res;
    }

    @Override
    public char code(){
        return (isWhite())?('B'):('b');
    }
}
