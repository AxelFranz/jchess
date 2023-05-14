package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Rook extends NonEmpty {
    private MoveList availableMoves;
    public Rook(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_rook.png";
        else this.imgPath = "/chessImage/black_rook.png";
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
                direction.add(getPos().addXY(i+j/2,i-j/2));
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

    public MoveList genAll(Board board){
        MoveList res = new MoveList();
        ArrayList<Coord> direction = new ArrayList<>();
        for(int i = -1 ; i < 2 ; i += 2){
            for(int j = -1 ; j < 2 ; j += 2){
                direction.add(getPos().addXY((i+j)/2,(i-j)/2));
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
    public String name(){
        return "Rook";
    }

    @Override
    public char code(){
        return (isWhite())?('R'):('r');
    }
}
