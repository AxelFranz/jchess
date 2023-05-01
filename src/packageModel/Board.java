package packageModel;

import java.util.ArrayList;



public class Board {
    private Piece board[][];
    private MoveStack history;
    public Board(Piece[][] board, MoveStack history) {
        this.board = board;
        this.history = history;
    }

    public Board() {
        this(new Piece[8][8], new MoveStack());
    }
    public Piece getPiece(Coord pos) {
        return board[pos.getX()][pos.getY()];
    }
    public Piece getPiece(int x,int y){
        return board[x][y];
    }
    public void setPiece(Coord pos, Piece pc) {
        board[pos.getX()][pos.getY()] = pc;
    }

    public void movePiece(Move newMove) {
        newMove.makeMove(this);
        history.addMove(newMove);

    }

    public void boardSet(int gamemode) {
        /* should be implemented */
    }
    public boolean isCheck(boolean white, Move test) {
        test.makeMove(this);
        ArrayList<Coord> allcapturable = allCapturableTiles(!white);
        Piece tmp;
        for(int x = 0; x <8 ; x++) {
            for(int y = 0; y <8 ; y++) {
                tmp = getPiece(x,y);
                if( tmp.isWhite() == white && tmp.toString()=="King" ){
                    if(allcapturable.contains(new Coord(x,y))) {
                        test.undoMove(this);
                        return true;
                    }

                }
            }
        }
        test.undoMove(this);
        return false;
    }
    private ArrayList<Coord> allCapturableTiles(boolean white){
        ArrayList<Coord> result = new ArrayList<>();
        ArrayList<Coord> dump = null;
        Piece tmp = null;
        for(int x = 0 ; x < 8 ; x++) {
            for(int y = 0 ; y < 8 ; y++){

                tmp = getPiece(x,y);

                if(!tmp.isEmpty() && tmp.isWhite()==white){
                    dump=tmp.allCapturePos(this);
                    for(Coord pos: dump){
                        if(!result.contains(pos))
                            result.add(pos);
                    }
                }

            }
        }
        return result;
    }

    public boolean canCapture(Piece pc, Coord pos){
        return Board.inBoard(pos) && (pc.isWhite()!=getPiece(pos).isWhite()) ;
    }
    public boolean isEmptyTile(Coord pos){
        return getPiece(pos.getX(),pos.getY()).isEmpty();
    }

    public boolean isEmptyTile(int x , int y){
        return getPiece(x,y).isEmpty();
    }
    public static boolean inBoard(Coord pos) {
        if(pos.getX() >=0 && pos.getX() <8 && pos.getY() >= 0 && pos.getY() < 8)
            return true;
        return false;
    }

}
