package packageTest;

import packageModel.*;

public class BoardTest {
    public static void main(String[] args) {
        Board test = new Board();
        Object init[] = new Object[2];
        init[0]  = true;
        init[1] = new Coord(0,3);
        test.printBoard();
        test.setPiece((Coord)init[1], Factory.newPiece(PcId.QUEEN,init));
        test.printBoard();
        System.out.println(test.getPiece(0,3).getPos());
    }



}
