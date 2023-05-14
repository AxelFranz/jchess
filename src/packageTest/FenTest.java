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

        System.out.println("must return true :");
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w K - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq h6 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq a3 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 068 1786"));

        System.out.println("must return false :");
        System.out.println(GameHandler.fenChecker("rnbqkbnr/ppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/9/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w   - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -   1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0  "));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq   0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR   KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/ w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/RNBQKBNR w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker(" rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 "));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 -"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - - 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP w KQkq - 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq h8 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq a1 0 1"));


        String[] testFen = new String[11];
        testFen[0] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        testFen[1] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w K - 0 1";
        testFen[2] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w - - 0 1";
        testFen[3] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq h6 0 1";
        testFen[4] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq a3 0 1";
        testFen[5] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - 0 1";
        testFen[6] = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 068 1786";
        testFen[7] = "8/8/8/8/8/8/8/8 b Kkq - 0 1";
        testFen[8] = "rnbqkbnr/pppppppp/8/3pp3/3PP3/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        testFen[9] = "2k5/b2p3B/1p3K2/P1p4N/1P1P3n/6Pp/8/2B1Q3 w - - 40 1";
        testFen[10] = "8/1Kn2p2/3P1P2/1p2Pr2/NB6/6pN/4p3/1Bnk4 b - - 0 1";

        for( String tfen: testFen){
            System.out.println(tfen);
            test.loadFen(tfen);
            test.getGame().printBoard();
            System.out.println(test.toFen());
        }



    }
}
