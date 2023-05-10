package packageModel;

import packageModel.chessPiece.*;
import packageModel.moveImplementations.BasicMovement;
import packageModel.moveImplementations.CaptureMovement;
import packageModel.moveImplementations.CastlingMovement;
import packageModel.moveImplementations.EnPassantMovement;

import java.util.Objects;


public class Factory {

    public static Piece newPiece(PcId id, Object array[])
    {
        /* handle cases were id != PcId.EMPTY && array.length != 2 with exception or by returning empty by default */
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
                return new Empty();
        }
    }

    public static Piece[][] newBoard( int x,int y){
        Piece board[][] = new Piece[y][x];
        for(int i = 0 ; i < y ; i ++) {
            for(int j = 0 ; j < x ; j++) {
                board[i][j] = newPiece(PcId.EMPTY,null);
            }
        }
        return board;
    }

    public static Move newMove(String moveType, Object array[]) {
        if (Objects.equals(moveType, "basic") && array.length >= 2) {
            return new BasicMovement((Piece) array[0], (Coord) array[1]);
        } else if (Objects.equals(moveType, "capture") && array.length >= 3) {
            return new CaptureMovement((Piece) array[0], (Coord) array[1], (Piece) array[2]);
        } else if(Objects.equals(moveType,"en passant")){
            return new EnPassantMovement((Piece) array[0],(Coord) array[1], (Piece) array[2]);
        }else if (Objects.equals(moveType,"castling") && array.length >= 4){
            return new CastlingMovement((Piece) array[0], (Coord) array[1], (Piece) array[2],(Coord) array[3] );
        }else
            return null; /* exception */

    }
    /*
    public static void initBoard(Board )
    */

}
