package packageModel;

import java.util.ArrayList;

public class GameHandler {

    /** Board game;
     * board containing the game on going
     */
    private Board game;

    /** int turn
     * 0 if no player should play
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
    /** ArrayList<Move> selected
     *  all the possible move that can be done by the last selected piece
     */
    private MoveList selected;
    /** private int HalfMoveClock;
     * every time a move other than a pawn moving or a capture occur this variable is incremented
     */
    private int HalfMoveClock = 0;

    private int fullMoveCount = 0;

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
        selected = (MoveList) game.getPiece(pos).getValidMoves().clone();
    }

    public void changeTurn() {
        turn = -turn;
    }

    public String toFen(){
        StringBuilder fenCode = new StringBuilder(128);
        int count = 0;
        for(int y = 0 ; y < 8 ; y ++){
            for(int x = 0 ; x < 8 ; x++){
                Piece tmp = game.getPiece(x,y);
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
        return fenCode.toString();
    }




}
