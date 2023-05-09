package packageModel;

import packageModel.chessPiece.Empty;

import java.util.ArrayList;



public class Board {
    private Piece board[][];
    private MoveStack history;
    public Board(Piece[][] board, MoveStack history) {
        this.board = board;
        this.history = history;
    }

    public Board() {
        this(PieceFactory.newBoard(8,8), new MoveStack());
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
        if(test != null)
            test.makeMove(this);
        ArrayList<Coord> allCapture = allCaptureTiles(!white);
        Piece tmp;
        for(int x = 0; x <8 ; x++) {
            for(int y = 0; y <8 ; y++) {
                tmp = getPiece(x,y);
                if( tmp.isWhite() == white && tmp.isKing() ){
                    if(new Coord(x,y).isIn(allCapture)) {
                        if(test != null)
                            test.undoMove(this);
                        return true;
                    }

                }
            }
        }
        if(test != null)
            test.undoMove(this);
        return false;
    }
    private ArrayList<Coord> allCaptureTiles(boolean white){
        ArrayList<Coord> result = new ArrayList<>();
        ArrayList<Coord> dump;
        Piece tmp;
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
    public boolean canMove( Coord pos){
        return Board.inBoard(pos) && isEmptyTile(pos);
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
