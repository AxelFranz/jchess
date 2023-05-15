package packageModel.chessPiece;

import packageModel.*;

import java.util.ArrayList;

public class King extends NonEmpty {

    private MoveList availableMoves;
    public King(boolean white, Coord pos) {
        super(white, pos);
        availableMoves = new MoveList();
        if(white) this.imgPath = "/chessImage/white_king.png";
        else this.imgPath = "/chessImage/black_king.png";
    }
    @Override
    public boolean isKing(){
        return true;
    }

    @Override
    public MoveList getValidMoves() {
        return availableMoves;
    }

    @Override
    public ArrayList<Coord> allCapturePos(Board board) {
        ArrayList<Coord> res = new ArrayList<>();
        for(int i = 0; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(i != 1 || j != 1 )
                    Coord.addToArray(res,getPos().addXY(i-1,j-1));
            }
        }
        return res;
    }

    @Override
    public void setValidMoves(Board board) {
        availableMoves.clear();
        availableMoves = genBasCapt(board);
        availableMoves.addAll(genCastling(board));
    }

    @Override
    public String name() {
        return "King";
    }

    private MoveList genBasCapt(Board board){
        MoveList res = new MoveList();
        Object[] builder = new Object[4];
        Coord tmp ;
        Move toTest;
        builder[0] = this;
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if( i != 1 || j != 1){
                    tmp = getPos().addXY(i-1,j-1);

                    if(board.canMove(tmp)){
                        builder[1] = tmp;
                        toTest = Factory.newMove(MoveId.BASIC,builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);

                    } else if (board.canCapture(this,tmp)) {
                        builder[1] = tmp;
                        builder[2] = board.getPiece(tmp);
                        toTest = Factory.newMove(MoveId.CAPTURE,builder);
                        if(!board.isCheck(isWhite(),toTest))
                            res.add(toTest);
                    }

                }
            }
        }

        return res;
    }

    private MoveList genCastling(Board board){
        MoveList res = new MoveList();
        if(!neverMoved())
            return res;
        int y = (isWhite())?(7):(0);
        int[] sides = {-1,1};
        Object[] init = new Object[4];
        Piece tmp;
        for( int r: sides){
            int rook = (r==1)?(7):(0);
            tmp = board.getPiece(rook,y);
            if((tmp.getId() == PcId.ROOK) && (tmp.isWhite() == isWhite()) && tmp.neverMoved()){
                ArrayList<Coord> capt = board.allCaptureTiles( !isWhite());
                ArrayList<Coord> interTiles = new ArrayList<>();
                int x = getPos().x()+r;
                while( x != rook){
                    interTiles.add(new Coord(x,y));
                    x += r;
                }
                boolean empty = true;
                for(Coord tile: interTiles){
                    if(!board.getPiece(tile).isEmpty()){
                        empty = false;
                    }
                }
                if (!capt.contains(interTiles.get(0)) && empty ){
                    init[0] = this;
                    init[1] = interTiles.get(1);
                    init[2] = tmp;
                    init[3] = interTiles.get(0);
                    Move castle = Factory.newMove(MoveId.CASTLING,init);
                    if(!board.isCheck(isWhite(),castle))
                        res.add(castle);
                }
            }
        }
        return res;
    }
    @Override
    public char code(){
        return (isWhite())?('K'):('k');
    }
    @Override
    public void emptyValidMoves(){
        availableMoves.clear();
    }

    @Override
    public PcId getId(){
        return PcId.KING;
    }

}
