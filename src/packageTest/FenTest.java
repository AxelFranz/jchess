package packageTest;

import packageModel.*;

public class FenTest {
    public static void main(String[] args) {
        GameHandler test = new GameHandler();
        Board board = new Board();
        test.setBoard(board);
        System.out.println(test.toFen());
        /*
        Object[] setup = new Object[2];
        setupe[0]
        board.setPiece(new Coord(0,0), Factory.newPiece(PcId.KING,));
        */
    }
}
