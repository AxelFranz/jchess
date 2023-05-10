package packageModel;

import java.util.ArrayList;

public class GameHandler {

    /** Board game;
     * board containing the game on going
     */
    Board game;

    /** int turn
     * 0 if no player should play
     * -1 for black turn
     * 1 fo white turn
     */
    int turn;
    /** int gamemode
     * the gamemode selected, rules can change from a mode to another
     * 0 for default gamemode
     * 1 for King Of The Hill gamemode
     */
    int gamemode;
    /** int gameState;
     *  the state of the ongoing game
     *  0 default: game is ongoing
     *  1 check: game is ongoing with the player in turn being in a check situation
     *  2 stalemate: game ended with a draw
     *  3 checkmate: game ended with the player in turn losing due to a checkmate
     *  4 game mode win: game ended with the player in turn losing due to special game modes rules
     */
    int gameState;
    /** ArrayList<Move> selected
     *  all the possible move that can be done by the last selected piece
     */
    ArrayList<Move> selected;

    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }



}
