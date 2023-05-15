package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Princess extends NonEmpty{
    private MoveList availableMoves;
    public Princess(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "chessImage/white_princess.png";
        else this.imgPath = "chessImage/black_princess.png";
    }

    @Override
    public String name() {
        return "Princess";
    }

    @Override
    public char code(){
        return (isWhite())?('I'):('i');
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        ArrayList<Coord> direction = new ArrayList<>();
        Coord start = getPos();
        Coord tmp;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != 1 || j != 1)
                    direction.add(new Coord(i-1,j-1));
            }
        }
        int range = 0;
        for( Coord dir: direction) {
            tmp = start;
            do {
                tmp = Coord.addCoord(tmp,dir);
                Coord.addToArray(res,tmp);
                range ++;
            }
            while(board.canMove(tmp) && range < 3);
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);
    }
    private MoveList genAll(Board board){
        MoveList res = new MoveList();
        ArrayList<Coord> direction = new ArrayList<Coord>();
        Object[] builder = new Object[3];
        Coord start = getPos();
        Coord tmp;
        Move toTest;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != 1 || j != 1)
                    direction.add(new Coord(i-1,j-1));
            }
        }
        builder[0] = this;
        int range;
        for( Coord dir: direction) {

            tmp = Coord.addCoord(start,dir);
            range = 0;
            while(board.canMove(tmp) && range < 3){
                builder[1] = tmp;
                toTest = Factory.newMove(MoveId.BASIC,builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
                tmp = Coord.addCoord(tmp,dir);
                range++;
            }

            if(board.canCapture(this,tmp) && range < 3){

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
    public void emptyValidMoves(){
        availableMoves.clear();
    }
    @Override
    public PcId getId(){
        return PcId.PRINCESS;
    }
}
