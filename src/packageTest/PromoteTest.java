package packageTest;

import packageModel.Coord;
import packageModel.GameHandler;
import packageModel.PcId;

public class PromoteTest {
    public static void main(String[] args) {
        GameHandler game = new GameHandler();
        game.loadFen("rnbqkbnr/pppppppp/8/q7/Q7/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        game.getGame().promotePawn(new Coord(0,1), PcId.QUEEN);
        System.out.println(game.toFen());
    }
}
