package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Queen extends NonEmpty{

    private ArrayList<Move> availableMoves;
    public Queen(boolean white, Coord pos) {
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
            while(Board.inBoard(tmp) && board.getPiece(tmp).isEmpty());
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genAll(board);
    }


    private ArrayList<Move> genAll(Board board){
        ArrayList<Move> res = new ArrayList<>();
        Coord direction[] = new Coord[8];
        Object builder[] = new Object[4];
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
        builder[1] = start;
        for( Coord dir: direction) {

            tmp = Coord.addCoord(start,dir);

            while(Board.inBoard(tmp) && board.getPiece(tmp).isEmpty()){
                builder[2] = tmp;
                toTest = MoveFactory.newMove("basic",builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
                tmp = Coord.addCoord(tmp,dir);
            }

            if(Board.inBoard(tmp) && board.getPiece(tmp).isWhite() != isWhite()){

                builder[2] = tmp;
                builder[3] =  board.getPiece(tmp);
                toTest = MoveFactory.newMove("capture",builder);
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
}
