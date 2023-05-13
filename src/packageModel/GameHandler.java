package packageModel;

import java.util.ArrayList;

public class GameHandler {

    /** Board game;
     * board containing the game on going
     */
    private Board game;

    /** int turn
     * -1 for black turn
     * 1 fo white turn
     */
    private int turn;
    /** int gamemode
     * the gamemode selected, rules can change from a mode to another
     * 0 for default gamemode
     * 1 for King Of The Hill gamemode
     */
    private int gamemode;
    /** int gameState;
     *  the state of the ongoing game
     *  -1 not started: game didn't start yet
     *  0 default: game is ongoing
     *  1 check: game is ongoing with the player in turn being in a check situation
     *  2 stalemate: game ended with a draw
     *  3 checkmate: game ended with the player in turn losing due to a checkmate
     *  4 game mode win: game ended with the player in turn losing due to special game modes rules
     */
    private int gameState;
    /** MoveList selected
     *  all the possible move that can be done by the last selected piece
     */
    private MoveList selected;
    /** private int HalfMoveClock
     * every time a move other than a pawn moving or a capture occur this variable is incremented
     */
    private int HalfMoveClock = 0;
    /** private int fullMoveCount = 0
     *  every time black side play this variable is incremented
     */
    private int fullMoveCount = 0;
    /** private Coord enPassant = null
     *  coord where a pawn who can En Passant would end after doing so, if no En Passant are possible null
     */
    private Coord enPassant = null;


    public void setBoard(Board board){
        game = board;
    }
    public void setTurn(int turn){
        this.turn = turn;
    }

    public void setGamemode(int gamemode) {
        /* only if the game hasn't started yet ( e.g gamestate = -1 ) */
        this.gamemode = gamemode;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void setSelected(Coord pos){
        selected.clear();
        selected = game.getPiece(pos).getValidMoves();
    }

    public void changeTurn() {
        turn = -turn;
    }

    public String toFen(){
        StringBuilder fenCode = new StringBuilder(128);
        int count = 0;
        Piece tmp;
        boolean isSet = false;
        for(int y = 0 ; y < 8 ; y ++){
            for(int x = 0 ; x < 8 ; x++){
                tmp = game.getPiece(x,y);
                if(tmp.isEmpty())
                {
                    count++;
                }else {
                    if(count != 0){
                        fenCode.append(count);
                        count = 0;
                    }
                    fenCode.append(tmp.code());
                }
            }
            if(count != 0) {
                fenCode.append(count);
                count = 0;
            }
            if( y != 7){
                fenCode.append('/');
            }
        }
        fenCode.append(' ');
        if(turn == -1){
            fenCode.append('b');
        } else {
            fenCode.append('w');
        }
        count = allCastles();
        if(count != 0){
            if((count & 1) == 1 )
                fenCode.append('K');
            if((count & 2) == 2)
                fenCode.append('Q');
            if((count & 4) == 4)
                fenCode.append('k');
            if((count & 8) == 8)
                fenCode.append('q');
        }

        return fenCode.toString();
    }

    private int allCastles(){
        int ret = 0;
        Piece tmp;
        tmp = game.getPiece(4,7);
        if(tmp.isKing() && tmp.neverMoved()){
            tmp = game.getPiece(7,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 1;
            }
            tmp = game.getPiece(0,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 2;
            }
        }
        tmp = game.getPiece(4,7);
        if(tmp.isKing() && tmp.neverMoved()){
            tmp = game.getPiece(7,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 4;
            }
            tmp = game.getPiece(0,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 8;
            }
        }
        return ret;
    }



}
