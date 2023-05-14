package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Queen extends NonEmpty{

    private MoveList availableMoves;
    public Queen(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "chessImage/white_queen.png";
        else this.imgPath = "chessImage/black_queen";
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        Coord direction[] = new Coord[8];
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
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);
    }


    private MoveList genAll(Board board){
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
                toTest = Factory.newMove("basic",builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
                tmp = Coord.addCoord(tmp,dir);
            }

            if(board.canCapture(this,tmp)){

                builder[1] = tmp;
                builder[2] =  board.getPiece(tmp);
                toTest = Factory.newMove("capture",builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
            }
        }
        return res;
    }


    @Override
    public String name() {
        return "Queen";
    }

    @Override
    public char code(){
        return (isWhite())?('Q'):('q');
    }
}
