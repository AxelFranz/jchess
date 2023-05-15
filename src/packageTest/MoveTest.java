package packageTest;

import packageModel.*;

public class MoveTest {
    public static void main(String[] args) {
        GameHandler game = new GameHandler();
        game.loadFen("rnbqkbnr/pppppppp/8/q7/Q7/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        System.out.println(game.getGame().getPiece(0,4) );
        System.out.println(game.getGame().getPiece(0,4).getValidMoves() );
        System.out.println(new Coord(0,3));
        System.out.println(game.getGame().getPiece(0,4).getValidMoves().getByDest(new Coord(0,3)) );
    }
}
