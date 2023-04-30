package packageModel;

import packageModel.chessPiece.*;

public class PieceFactory {

    public static Piece newPiece( PieceId id,Object array[])
    {
        if(id != PieceId.EMPTY && array.length != 2)
            return null;
        switch(id){
            case EMPTY:
                return new Empty();
            case PAWN:
                return new Pawn((boolean)array[0],(Coord)array[1]);
            case KING:
                return new King((boolean)array[0],(Coord)array[1]);
            case QUEEN:
                return new Queen((boolean)array[0],(Coord)array[1]);
            case ROOK:
                return new Rook((boolean)array[0],(Coord)array[1]);
            case BISHOP:
                return new Bishop((boolean)array[0],(Coord)array[1]);
            case KNIGHT:
                return new Knight((boolean)array[0],(Coord)array[1]);
            default:
                return null;
        }
    }
}
