package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class Knight extends NonEmpty{

    public Knight(boolean white, Coord pos) {
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
