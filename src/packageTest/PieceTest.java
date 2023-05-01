package packageTest;

import packageModel.*;

public class PieceTest {
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
        Piece empty = PieceFactory.newPiece(PieceId.EMPTY,null);
        if(empty.isEmpty())
        {
            System.out.println("OK");
        }
        Object array[] = new Object[2];
        array[0] = true;
        array[1] = new Coord(1,6);
        Piece testPawn = PieceFactory.newPiece(PieceId.PAWN,array);
        System.out.println(testPawn);
        System.out.println(testPawn.getPos());
        System.out.println(testPawn.allCapturePos(null));
        testPawn.setPos(new Coord(4,4));
        System.out.println(testPawn.getPos());
        System.out.println(testPawn.allCapturePos(null));
        Piece testQueen = PieceFactory.newPiece(PieceId.QUEEN,array);
        testBoard.setPiece(testQueen.getPos(),testQueen);
        System.out.println(testQueen);
        System.out.println(testQueen.getPos());
        System.out.println(testQueen.allCapturePos(testBoard));
        testQueen.setPos(new Coord(0,0));
        testBoard.setPiece(testQueen.getPos(),testQueen);
        System.out.println(testQueen);
        System.out.println(testQueen.getPos());
        System.out.println(testQueen.allCapturePos(testBoard));
        testBoard.setPiece(testPawn.getPos(),testPawn);
        System.out.println(testQueen);
        System.out.println(testQueen.getPos());
        System.out.println(testQueen.allCapturePos(testBoard));
    }
}
