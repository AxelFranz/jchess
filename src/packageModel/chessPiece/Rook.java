package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Rook extends NonEmpty {

    public Rook(boolean white, Coord pos) {
        super(white, pos);
    }

    @Override
    public ArrayList<Move> getValidMoves() {
        return null;
    }

    @Override
    public ArrayList<Coord> allCapturePos() {
        return null;
    }

    @Override
    public void setValidMoves(Board board) {

    }
}
