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
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq h8 0 1"));
        System.out.println(GameHandler.fenChecker("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq a8 0 1"));
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



    }
}
