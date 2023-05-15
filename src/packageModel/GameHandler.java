package packageModel;

import packageModel.moveImplementations.BasicMovement;

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
     *  2 stalemate: game ended with a draw due to no legalMoves possible
     *  3 checkmate: game ended with the player in turn losing due to a checkmate
     *  4 game mode win: game ended with the player in turn losing due to special game modes rules
     *  5 50 moves without capture draw: 50 moves were performed without a pawn moving or a piece being captured end in a draw
     *  6 3 same moves draw: repetition of the same moves for 3 turns
     */
    private int gameState;
    public int getGameState() {
        return gameState;
    }

    /** private int HalfMoveClock
     * every time a move other than a pawn moving or a capture occur this variable is incremented
     */
    private int halfMoveClock = 0;
    /** private int fullMoveCount = 0
     *  every time black side play this variable is incremented
     */
    private int fullMoveCount = 1;
    /** private Coord enPassant = null
     *  coord where a pawn who can En Passant would end after doing so, if no En Passant are possible null
     */
    private Coord enPassant = null;

    /** public void setBoard(Board board)
     * Set the game Board to the value of board
     * @param board the new board of the game
     */
    public void setBoard(Board board){
        game = board;
    }

    /** public void setTurn(int turn)
     * set the turn to the turn value
     * @param turn new value of turn
     */
    public void setTurn(int turn){
        this.turn = turn;
    }

    /** public int getTurn()
     *  get the turn variable value
     * @return turn value
     */
    public int getTurn(){
        return turn;
    }

    /** public void setGamemode(int gamemode)
     * set the gamemode before the game is started to play with default or special rules;
     * @param gamemode the new value of gamemode
     */

    public void setGamemode(int gamemode) {
        /* only if the game hasn't started yet ( e.g gamestate = -1 ) */
        this.gamemode = gamemode;
    }

    /** public void setGameState(int gameState)
     * set the state of the game to the actual state, should be called after each move made on the game
     * @param gameState the new gameState
     */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /** public void changeTurn()
     * change the turn form black to white and from white to black
     */
    public void changeTurn() {
        turn = -turn;
    }


    /** public void play(Coord dest)
     * make the movement that put the selected piece in the coordinates given in argument
     * and update all variables to fit the new state of the game
     * @param dest the coordinates in which the player ask to move the selected piece
     */
    public void play(Coord start,Coord dest){
        if(gameState == -1)
            return;
        Piece selected = game.getPiece(start);
        if(selected.isEmpty() || (selected.isWhite() != (turn==1)) )
            return;
        Move temp = selected.getValidMoves().getByDest(dest);
        if(temp == null)
            return;
        temp.makeMove(game);
        game.addHistory(temp);

        enPassant = null;
        switch(temp.moveType()){
            case CAPTURE:
                halfMoveClock = 0;
                break;
            case BASIC:
                BasicMovement quaso = (BasicMovement) temp;
                enPassant = quaso.canEnPassant();
                halfMoveClock++;
                if(temp.getPiece().name().equals("Pawn"))
                    halfMoveClock= 0;
                break;
            default:
                halfMoveClock++;
        }

        if(turn == -1)
            fullMoveCount++;

        changeTurn();
        game.genAllMoves(turn == 1);
        boolean isCheck = game.isCheck(turn == 1);
        boolean hasValidMoves = game.hasLegalMoves(turn == 1);

        if(isCheck && !hasValidMoves)
            gameState = 3;
        else if(!isCheck && !hasValidMoves)
            gameState = 2;
        else if( isCheck )
            gameState = 1;
        else{
            if( game.getHistory().threefold())
                gameState = 6;
            else if (halfMoveClock >= 50)
                gameState = 5;
            else if(gamemode != 0){
                if (gamemode == 1) {
                    if (kothCondition())
                        gameState = 4;
                }
            }
        }

    }


    /** private boolean kothCondition()
     * check if the special winning conditions for the King of The Hill are verified
     * @return a boolean,true if the side who played the last move won false either
     */
    private boolean kothCondition(){
        Coord[] center = new Coord[4];
        center[0] = new Coord(3,3);
        center[1] = new Coord(3,4);
        center[2] = new Coord(4,3);
        center[3] = new Coord(4,4);
        for( Coord pos : center){
            Piece tmp = game.getPiece(pos);
            if(tmp.isKing() && (tmp.isWhite() != (turn == 1)) )
                return true;
        }
        return false;
    }

    /** public String toFen()
     * turn the game into a FEN code to display or save the game for later
     * @return A string which is a valid FEN notation of the ongoing game
     */
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
        fenCode.append(' ');
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
        } else {
            fenCode.append('-');
        }
        fenCode.append(' ');
        if(enPassant == null)
            fenCode.append('-');
        else
            fenCode.append(enPassant);
        fenCode.append(' ');
        fenCode.append(halfMoveClock).append(' ').append(fullMoveCount);
        return fenCode.toString();
    }

    /** public void loadFen(String fen)
     *  use a FEN notation to set up the board
     * @param fen string which contain a FEN notation
     * fen must be a valid fen in order to be used in this function
     * /!\ use fenChecker static function beforehand if the fen come from an untrusted source
     */
    public void loadFen(String fen){
        int index =  Factory.initBoard(this,fen);
        int len = fen.length();
        char buf;
        buf = fen.charAt(index++);
        if(buf == 'b')
            turn = -1;
        else
            turn = 1;
        index++;
        buf = fen.charAt(index++);
        char tmp;
        /*  if the FEN notation indicate that we can't castle
         *  increment move variable for the rooks at their starting positions to block the castling possibility;
         */
        if( buf == 'K'){
            buf = fen.charAt(index++);
        } else {
            tmp = game.getPiece(7,7).code();
            if( tmp == 'R')
                game.getPiece(7,7).incrementMoved();
        }
        if ( buf == 'Q'){
            buf = fen.charAt(index++);
        } else {
            tmp = game.getPiece(0,7).code();
            if( tmp == 'R')
                game.getPiece(0,7).incrementMoved();
        }
        if ( buf == 'k'){
            buf = fen.charAt(index++);
        } else {
            tmp = game.getPiece(7,0).code();
            if( tmp == 'r')
                game.getPiece(7,0).incrementMoved();
        }
        if (buf == 'q') {
            buf = fen.charAt(index++);
        } else {
            tmp = game.getPiece(7,0).code();
            if( tmp == 'r')
                game.getPiece(7,0).incrementMoved();
        }
        if(buf == '-')
            index ++;

        buf = fen.charAt(index++);
        if(buf != '-'){
            char yChar = fen.charAt(index++);
            int dir = 1;
            if(turn == -1)
                dir = -1;
            int y = '8'-yChar;
            Coord dest = new Coord(buf-'a',y+dir);
            Coord dep = new Coord(buf-'a',y-dir);
            Move passant = new BasicMovement(game.getPiece(dest),dest,dep);
            game.addHistory(passant);
        }
        index++;
        StringBuilder nb = new StringBuilder(8);
        buf = fen.charAt(index++);
        while(buf != ' '){
            nb.append(buf);
            buf = fen.charAt(index++);
        }
        halfMoveClock = Integer.parseInt(nb.toString());
        nb = new StringBuilder(8);
        for(int i = index; i < len; i++){
            nb.append(fen.charAt(i));
        }
        fullMoveCount = Integer.parseInt(nb.toString());
        game.genAllMoves(turn==1);
    }

    /** private int allCastles()
     *  stock in an int the information on which castles can and can't be made anymore
     * @return an int encoded depending on the castles available
     */
    private int allCastles(){
        int ret = 0;
        Piece tmp;
        tmp = game.getPiece(4,7);
        if(tmp.isKing() && tmp.isWhite() && tmp.neverMoved()){
            tmp = game.getPiece(7,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 1;
            }
            tmp = game.getPiece(0,7);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 2;
            }
        }
        tmp = game.getPiece(4,0);
        if(tmp.isKing() && tmp.isBlack() && tmp.neverMoved()){
            tmp = game.getPiece(7,0);
            if(tmp.code()=='r' && tmp.neverMoved()){
                ret = ret | 4;
            }
            tmp = game.getPiece(0,0);
            if(tmp.code()=='r' && tmp.neverMoved()){
                ret = ret | 8;
            }
        }
        return ret;
    }

    /** private static boolean validFenChar(char x)
     * A static function that check if a char in the first part of a FEN notation is valid or not
     * @param x the character that must be tested
     * @return a boolean true if the character is valid and false if he isn't
     */
    private static boolean validFenChar(char x){
        return (x>='1' && x<='8') || (x>='a' && x<='z') || (x>='A'&&x<='Z') || (x == '/');
    }

    /** public static boolean fenChecker(String fen)
     *  A static function that check whether a fen is valid and can be used in the program or not
     * @param fen the string of the FEN code tested
     * @return a boolean whose value is true if the FEN given is valid and false in other cases
     */
    public static boolean fenChecker(String fen){
        int index = 0;
        int y = 0;
        int x = 0;
        char buf;
        int length = fen.length();
        buf = fen.charAt(index++);
        while(buf != ' ' && index < length){
            if(!validFenChar(buf)){
                return false;
            }
            if(buf >= '1' && buf <= '8'){
                x += buf-'0';
            } else if( buf != '/'){
                x++;
            }else {
                if(x != 8)
                    return false;
                y++;
                x=0;
            }
            if(x > 8 || y > 7)
                return false;
            buf = fen.charAt(index++);
        }
        if (index >= length || x != 8 || y !=7)
            return false;
        buf = fen.charAt(index++);
        if((buf != 'b' && buf != 'w') || index >= length)
            return false;
        buf = fen.charAt(index++);
        if((buf != ' ') || (index >= length))
            return false;
        buf = fen.charAt(index++);
        while(buf != ' ' && index < length){
            if(buf != '-' && buf != 'Q' && buf != 'K' && buf != 'q' && buf != 'k'){
                return false;
            }
            buf = fen.charAt(index++);
        }
        if (index >= length)
            return false;
        buf = fen.charAt(index++);
        if(buf != '-'){
            if (buf < 'a' || buf > 'h' || index >= length){
                return false;
            }
            buf = fen.charAt(index++);
            if((buf != '3' && buf != '6') || index >= length)
                return false;
        }
        buf = fen.charAt(index++);
        if(buf != ' ' || index >= length){
            return false;
        }
        buf = fen.charAt(index++);
        while(buf != ' ' && index < length){
            if(buf < '0' || buf > '9')
                return false;
            buf = fen.charAt(index++);
        }
        buf = fen.charAt(index++);
        while(index < length){
            if(buf < '0' || buf > '9')
                return false;
            buf = fen.charAt(index++);
        }
        return buf >= '0' && buf <= '9';
    }
    public Board getGame(){
        return game;
    }


}
