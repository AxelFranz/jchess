package packageTest;

import packageModel.*;

public class CheckTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j++)
            {
                testBoard.setPiece(new Coord(i,j), Factory.newPiece(PcId.EMPTY,null));
            }
        }
        Object[] pcBuild = new Object[2];
        pcBuild[0] = true;
        pcBuild[1] = new Coord(0,0);
        Piece king = Factory.newPiece(PcId.KING,pcBuild);
        testBoard.setPiece((Coord)pcBuild[1],king);
        Piece empty = Factory.newPiece(PcId.EMPTY, null);
        pcBuild[1] = new Coord(1,7);
        Piece queen = Factory.newPiece(PcId.QUEEN,pcBuild);
        testBoard.setPiece((Coord)pcBuild[1],queen);

        System.out.println(testBoard.getPiece(new Coord(0,0)));
        System.out.println(testBoard.getPiece(new Coord(0,0)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,0)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,0)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));
        pcBuild[0] = false;
        pcBuild[1] = new Coord(2,0);

        testBoard.setPiece((Coord)pcBuild[1], Factory.newPiece(PcId.QUEEN,pcBuild));

        System.out.println(testBoard.getPiece(new Coord(0,0)));
        System.out.println(testBoard.getPiece(new Coord(0,0)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,0)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,0)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));

        System.out.println(testBoard.getPiece(new Coord(1,7)));
        System.out.println(testBoard.getPiece(new Coord(1,7)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(1,7)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(1,7)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));


        Object[] move = new Object[2];
        move[0] = king;
        move[1] = new Coord(0,1);
        Move mTest = Factory.newMove("basic",move);
        mTest.makeMove(testBoard);





        System.out.println(testBoard.getPiece(new Coord(0,1)));
        System.out.println(testBoard.getPiece(new Coord(0,1)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,1)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,1)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));

        testBoard.printBoard();


        /*
        System.out.println(testBoard.getPiece(new Coord(0,2)));
        System.out.println(testBoard.getPiece(new Coord(0,2)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,2)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,2)).getValidMoves());
        */



    }
}
