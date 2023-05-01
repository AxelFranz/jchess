package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Rook extends NonEmpty {
    private ArrayList<Move> availableMoves;
    public Rook(boolean white, Coord pos) {
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

    public ArrayList<Move> genAll(Board board){
        ArrayList<Move> res = new ArrayList<>();
        ArrayList<Coord> direction = new ArrayList<>();
        for(int i = -1 ; i < 2 ; i += 2){
            for(int j = -1 ; j < 2 ; j += 2){
                direction.add(getPos().addXY((i+j)/2,(i-j)/2));
            }
        }
        Object builder[] = new Object[4];
        builder[0] = this;
        Coord start = getPos();
        builder[1] = start;
        Coord tmp;
        Move toTest;
        for( Coord dir: direction) {

            tmp = Coord.addCoord(start,dir);

            while(board.canMove(tmp)){
                builder[2] = tmp;
                toTest = MoveFactory.newMove("basic",builder);
                if(!board.isCheck(isWhite(),toTest))
                    res.add(toTest);
                tmp = Coord.addCoord(tmp,dir);
            }

            if(board.canCapture(this,tmp)){

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
    public String name(){
        return "Rook";
    }
}
