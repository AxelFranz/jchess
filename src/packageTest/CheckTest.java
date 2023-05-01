package packageTest;

import packageModel.*;

public class CheckTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j++)
            {
                testBoard.setPiece(new Coord(i,j), PieceFactory.newPiece(PieceId.EMPTY,null));
            }
        }
        Object pcBuild[] = new Object[2];
        pcBuild[0] = true;
        pcBuild[1] = new Coord(0,0);
        Piece king = PieceFactory.newPiece(PieceId.KING,pcBuild);
        testBoard.setPiece((Coord)pcBuild[1],king);
        Piece empty = PieceFactory.newPiece(PieceId.EMPTY, null);
        pcBuild[1] = new Coord(7,1);
        Piece queen = PieceFactory.newPiece(PieceId.QUEEN,pcBuild);
        testBoard.setPiece((Coord)pcBuild[1],queen);

        System.out.println(testBoard.getPiece(new Coord(0,0)));
        System.out.println(testBoard.getPiece(new Coord(0,0)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,0)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,0)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));
        pcBuild[0] = false;
        pcBuild[1] = new Coord(0,2);

        testBoard.setPiece((Coord)pcBuild[1],PieceFactory.newPiece(PieceId.QUEEN,pcBuild));

        System.out.println(testBoard.getPiece(new Coord(0,0)));
        System.out.println(testBoard.getPiece(new Coord(0,0)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,0)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,0)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));

        System.out.println(testBoard.getPiece(new Coord(7,1)));
        System.out.println(testBoard.getPiece(new Coord(7,1)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(7,1)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(7,1)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));

        testBoard.setPiece(new Coord(1,0),king);
        king.setPos(new Coord(1,0));
        testBoard.setPiece(new Coord(0,0),empty);

        System.out.println(testBoard.getPiece(new Coord(1,0)));
        System.out.println(testBoard.getPiece(new Coord(1,0)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(1,0)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(1,0)).getValidMoves());
        System.out.println(testBoard.isCheck(true,null));


        /*
        System.out.println(testBoard.getPiece(new Coord(0,2)));
        System.out.println(testBoard.getPiece(new Coord(0,2)).allCapturePos(testBoard));
        testBoard.getPiece(new Coord(0,2)).setValidMoves(testBoard);
        System.out.println(testBoard.getPiece(new Coord(0,2)).getValidMoves());
        */



    }
}
