package packageModel;

import java.util.ArrayList;

public class Board {
    private Piece board[][];
    private MoveStack history;
    Board(Piece[][] board, MoveStack history) {
        this.board = board;
        this.history = history;
    }

    Board() {
        this(new Piece[8][8], new MoveStack());
    }
    public Piece getPiece(Coord pos) {
        return board[pos.getX()][pos.getY()];
    }

    public void movePiece(Coord pc1,Coord pc2) {
        /* should be implemented */
    }

    public void boardSet(int gamemode) {
        /* should be implemented */
    }

}
