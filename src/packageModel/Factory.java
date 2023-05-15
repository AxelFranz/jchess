package packageModel;

import packageModel.chessPiece.*;
import packageModel.moveImplementations.BasicMovement;
import packageModel.moveImplementations.CaptureMovement;
import packageModel.moveImplementations.CastlingMovement;
import packageModel.moveImplementations.EnPassantMovement;

import javax.xml.stream.FactoryConfigurationError;
import java.util.Objects;


public class Factory {

    public String defaultFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
    public String fairyFen = "";
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

    public static Move newMove(MoveId moveType, Object[] array) {
        if (moveType == MoveId.BASIC && array.length >= 2) {
            return new BasicMovement((Piece) array[0], (Coord) array[1]);
        } else if (moveType == MoveId.CAPTURE && array.length >= 3) {
            return new CaptureMovement((Piece) array[0], (Coord) array[1], (Piece) array[2]);
        } else if(moveType == MoveId.ENPASSANT){
            return new EnPassantMovement((Piece) array[0],(Coord) array[1], (Piece) array[2]);
        }else if (moveType == MoveId.CASTLING && array.length >= 4){
            return new CastlingMovement((Piece) array[0], (Coord) array[1], (Piece) array[2],(Coord) array[3] );
        }else
            return null; /* exception */

    }

    private static PcId codeToPcId (char code){
        switch(code){
            case 'P':
                return PcId.PAWN;
            case 'R':
                return PcId.ROOK;
            case 'N':
                return PcId.KNIGHT;
            case 'B':
                return PcId.BISHOP;
            case 'Q':
                return PcId.QUEEN;
            case 'K':
                return PcId.KING;
            default:
                return PcId.EMPTY;
        }
    }

    public static int initBoard(GameHandler handler,String fen){
        Piece[][] tab = new Piece[8][8];
        int index = 0;
        char buf;
        int nbEmpty;
        int x , y;
        Object[] init = new Object[2];
        PcId id;

        buf = fen.charAt(index++);
        x = 0;
        y = 0;
        while(buf != ' '){
            if(buf >= '1' && buf <= '8'){
                nbEmpty = buf-'0';
                for(int i = 0; i < nbEmpty; i++){
                    tab[x][y] = newPiece(PcId.EMPTY,null);
                    x++;
                }
            } else if((buf >= 'a' && buf <= 'z') ||(buf >= 'A' && buf <= 'Z')) {
                init[1] = new Coord(x,y);
                init[0] = true;
                if(buf >= 'a'){
                    init[0] = false;
                    buf = Character.toUpperCase(buf);
                }
                id = codeToPcId(buf);
                tab[x][y] = newPiece(id,init);
                x++;
            } else if( buf == '/'){
                x = 0;
                y++;
            }
            buf = fen.charAt(index++);
        }
        Board newBoard = new Board(tab,new MoveHistory());
        handler.setBoard(newBoard);
        return index;
    }

    public static Piece metamorph(Piece pc,PcId type){
        Object[] init = new Object[2];
        if(pc.isEmpty()){
            return Factory.newPiece(PcId.EMPTY,null);
        } else {
            init[0] = pc.isWhite();
            init[1] = pc.getPos();
            int moved = pc.getMoved();
            Piece newP = Factory.newPiece(type,init);
            newP.setMoved(moved);
            return newP;
        }

    }

}
