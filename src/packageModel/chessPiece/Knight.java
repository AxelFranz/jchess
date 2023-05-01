package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Knight extends NonEmpty{

    private ArrayList<Move> availableMoves;

    public Knight(boolean white, Coord pos) {
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
        availableMoves = genAll(board);
    }

    private ArrayList<Move> genAll(Board board) {
        ArrayList<Move> res = new ArrayList<>();
        Object builder[] = new Object[4];
        builder[0] = this;
        Coord start = getPos(), z[] = new Coord[2];
        builder[1] = start;
        Move toTest;

        for(int i = -1; i < 2 ; i+= 2){
            for(int j = -1 ; j < 2 ; j+= 2){
                z[0] = start.addXY(2*j,i);
                z[1] = start.addXY(j, 2*i);
                for(Coord dest: z){
                    if(Board.inBoard(dest) && board.getPiece(dest).isEmpty()){
                        builder[2] = dest;
                        toTest = MoveFactory.newMove("basic",builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }else if(Board.inBoard(dest) && board.getPiece(dest).isWhite() != isWhite()){
                        builder[2] = dest;
                        builder[3] =  board.getPiece(dest);
                        toTest = MoveFactory.newMove("capture",builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }
                }
            }
        }
        return res;
    }

    @Override
    public String name(){
        return "Knight";
    }
}
