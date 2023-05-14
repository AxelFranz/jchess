package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Knight extends NonEmpty{

    private MoveList availableMoves;

    public Knight(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_knight.png";
        else this.imgPath = "/chessImage/black_knight.png";
    }

    @Override
    public MoveList getValidMoves() {
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

    private MoveList genAll(Board board) {
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
                        toTest = Factory.newMove("basic",builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }else if(board.canCapture(this,tmp)){
                        builder[1] = tmp;
                        builder[2] =  board.getPiece(tmp);
                        toTest = Factory.newMove("capture",builder);
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

    @Override
    public char code(){
        return (isWhite())?('N'):('n');
    }
}
