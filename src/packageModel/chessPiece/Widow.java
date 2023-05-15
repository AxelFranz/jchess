package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;
import java.util.Queue;

public class Widow extends NonEmpty{
    private MoveList availableMoves;
    public Widow(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        Object[] init = {white,pos};
        if(white) this.imgPath = "chessImage/white_widow.png";
        else this.imgPath = "chessImage/black_widow.png";
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
        ArrayList<Coord> res = new ArrayList<>();
        Coord[] direction = new Coord[8];
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != 1 || j != 1)
                    direction[(i*3+j<4)?(3*i+j):(3*i+j-1)] = new Coord(i-1,j-1);
            }
        }
        Coord start = getPos();
        Coord tmp;
        for( Coord dir: direction) {
            tmp = start;
            do {
                tmp = Coord.addCoord(tmp,dir);
                Coord.addToArray(res,tmp);
            }
            while(board.canMove(tmp));
        }
        for(int i = -1; i < 2 ; i += 2){
            for(int j = -1 ; j < 2 ; j += 2){
                Coord.addToArray(res,getPos().addXY(2*j,i));
                Coord.addToArray(res,getPos().addXY(j,2*i));
            }
        }
        return res;

    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAllQ(board);
        availableMoves.addAll(genAllK(board));
        
    }

    private MoveList genAllQ(Board board){
        MoveList res = new MoveList();
        Coord[] direction = new Coord[8];
        Object[] builder = new Object[4];
        Coord start = getPos();
        Coord tmp;
        Move toTest;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0; j < 3; j++) {
                if(i != 1 || j != 1)
                    direction[(i*3+j<4)?(3*i+j):(3*i+j-1)] = new Coord(i-1,j-1);
            }
        }
        builder[0] = this;
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
    private MoveList genAllK(Board board) {
        MoveList res = new MoveList();
        Object[] builder = new Object[4];
        builder[0] = this;
        Coord start = getPos(), z[] = new Coord[2];
        Move toTest;

        for(int i = -1; i < 2 ; i+= 2){
            for(int j = -1 ; j < 2 ; j+= 2){
                z[0] = start.addXY(2*j,i);
                z[1] = start.addXY(j, 2*i);
                for(Coord tmp: z){
                    if(board.canMove(tmp)){
                        builder[1] = tmp;
                        toTest = Factory.newMove(MoveId.BASIC,builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }else if(board.canCapture(this,tmp)){
                        builder[1] = tmp;
                        builder[2] =  board.getPiece(tmp);
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
    public void emptyValidMoves(){
        availableMoves.clear();
    }
    @Override
    public PcId getId(){
        return PcId.WIDOW;
    }
}
