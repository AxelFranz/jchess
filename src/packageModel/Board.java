package packageModel;

import java.util.ArrayList;



public class Board {
    private Piece[][] board;
    private MoveStack history;
    public Board(Piece[][] board, MoveStack history) {
        this.board = board;
        this.history = history;
    }

    public Board() {
        this(Factory.newBoard(8,8), new MoveStack());
    }
    public Piece getPiece(Coord pos) {
        return board[pos.x()][pos.y()];
    }
    public Piece getPiece(int x,int y){
        return board[x][y];
    }
    public void setPiece(Coord pos, Piece pc) {
        board[pos.x()][pos.y()] = pc;
        pc.setPos(pos);
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
    public boolean isCheck(boolean white){
        return isCheck(white,null);
    }
    public boolean hasLegalMoves( boolean white){
        Piece tmp;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0 ; j < 8 ; j++){
                tmp = getPiece(i,j);
                if( !tmp.isEmpty() && tmp.isWhite() == white ){
                    if(!tmp.getValidMoves().isEmpty()){
                        return true;
                    }
                }

            }
        }

        return true;
    }

    private ArrayList<Coord> allCaptureTiles(boolean white){
        ArrayList<Coord> result = new ArrayList<>();
        ArrayList<Coord> dump;
        Piece tmp;
        for(int x = 0 ; x < 8 ; x++) {
            for(int y = 0 ; y < 8 ; y++){
                if(!isEmptyTile(x,y)) {
                    tmp = getPiece(x, y);

                    if (tmp.isWhite() == white) {
                        dump = tmp.allCapturePos(this);
                        for (Coord pos : dump) {
                            if (!result.contains(pos))
                                result.add(pos);
                        }
                    }
                }

            }
        }
        return result;
    }

    public boolean canCapture(Piece pc, Coord pos){
        return Board.inBoard(pos) && !isEmptyTile(pos) && (pc.isWhite()!=getPiece(pos).isWhite())   ;
    }
    public boolean canMove( Coord pos){
        return Board.inBoard(pos) && isEmptyTile(pos);
    }
    public boolean isEmptyTile(Coord pos){
        return getPiece(pos).isEmpty();
    }

    public boolean isEmptyTile(int x , int y){
        return getPiece(x,y).isEmpty();
    }
    public static boolean inBoard(Coord pos) {
        if(pos.x() >=0 && pos.x() <8 && pos.y() >= 0 && pos.y() < 8)
            return true;
        return false;
    }

    public void printBoard(){
        System.out.println("_________________");
        for(int i = 0 ; i < 8 ; i++){
            System.out.print("|");
            for(int j = 0 ; j < 8 ; j++){
                System.out.print(board[j][i].code() + "|");
            }
            System.out.println();
        }
        System.out.println("_________________");
    }

}
