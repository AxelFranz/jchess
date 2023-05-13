package packageTest;

import packageModel.*;

public class FenTest {
    public static void main(String[] args) {
        GameHandler test = new GameHandler();
        Board board = new Board();
        test.setBoard(board);
        System.out.println(test.toFen());

        Object[] setup = new Object[2];
        setup[0] = true;
        setup[1] = new Coord(1,1);
        board.setPiece(new Coord(0,0), Factory.newPiece(PcId.KING,setup));
        System.out.println(test.toFen());

    }
}
