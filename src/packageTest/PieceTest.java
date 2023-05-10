package packageTest;

import packageModel.*;

public class PieceTest {
    public static void main(String[] args) {
        Board testBoard = new Board();
        for(int i = 0; i < 8 ; i ++){
            for(int j = 0; j < 8 ; j++)
            {
                testBoard.setPiece(new Coord(i,j), Factory.newPiece(PcId.EMPTY,null));
                if(testBoard.getPiece(new Coord(i,j)).isEmpty())
                    System.out.println("OK " + (8*i + j) );
            }
        }
        Piece empty = Factory.newPiece(PcId.EMPTY,null);
        if(empty.isEmpty())
        {
            System.out.println("OK");
        }

        Object array[] = new Object[2];
        array[0] = true;

        array[1] = new Coord(1,6);
        Piece testPawn = Factory.newPiece(PcId.PAWN,array);

        System.out.println(testPawn);
        System.out.println(testPawn.getPos());
        System.out.println(testPawn.allCapturePos(null));
        testPawn.setValidMoves(testBoard);
        System.out.println(testPawn.getValidMoves());
        testBoard.printBoard();
        testPawn.setPos(new Coord(4,4));
        testPawn.incrementMoved();

        System.out.println(testPawn.getPos());
        System.out.println(testPawn.allCapturePos(null));
        testPawn.setValidMoves(testBoard);
        System.out.println(testPawn.getValidMoves());
        testBoard.printBoard();


        /*
        Piece testQueen = Factory.newPiece(PcId.QUEEN,array);
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
        */


        /*
        array[1] = new Coord(2,2);
        Piece testKnight = Factory.newPiece(PcId.KNIGHT,array);
        testBoard.setPiece((Coord)array[1],testKnight);

        System.out.println(testKnight);
        System.out.println(testKnight.getPos());
        System.out.println(testKnight.allCapturePos(testBoard));
        testKnight.setValidMoves(testBoard);
        System.out.println(testKnight.getValidMoves());
         */




    }
}
