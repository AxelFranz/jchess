package packageTest;

import packageModel.Board;
import packageModel.Coord;
import packageModel.PieceFactory;
import packageModel.PieceId;

public class CheckTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j++)
            {
                testBoard.setPiece(new Coord(i,j), PieceFactory.newPiece(PieceId.EMPTY,null));
                if(testBoard.getPiece(new Coord(i,j)).isEmpty())
                    System.out.println("OK " + (8*i + j) );
            }
        }

    }
}
