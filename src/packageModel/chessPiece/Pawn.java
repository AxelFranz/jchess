package packageModel.chessPiece;

import packageModel.Board;
import packageModel.Coord;
import packageModel.Move;
import packageModel.MoveFactory;


import java.util.ArrayList;

public class Pawn extends NonEmpty {

    private ArrayList<Move> availableMoves;
    public Pawn(boolean white, Coord pos) {
        super(white, pos);
    }

    @Override
    public ArrayList<Move> getValidMoves() {
        return availableMoves;
    }

    @Override
    public void setValidMoves(Board board) {

    }

    @Override
    public String toString(){
        return "Pawn";
    }

    private ArrayList<Move> genMoves(Board board)
    {
        ArrayList<Move> res = new ArrayList<>();
        int dir = (this.isWhite())?(-1):(1);
        if(!(board.getPiece(getPos().x,getPos().y + dir).isEmpty()))
        {


        }
        return null;
    }
}
