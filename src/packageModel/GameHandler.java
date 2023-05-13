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
    private Piece selected;
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
     * set ther turn to the turn value
     * @param turn new value of turn
     */
    public void setTurn(int turn){
        this.turn = turn;
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

    /** public void setSelected(Coord pos)
     * set the Selected piece to the coordinates of pos
     * @param pos Coordinate of the new selected piece
     */
    public void setSelected(Coord pos){
        selected = game.getPiece(pos);
    }

    /** public void changeTurn()
     * change the turn form black to white and from white to black
     */
    public void changeTurn() {
        turn = -turn;
    }

    /** public ArrayList<Coord> highlighted()
     * @return an ArraList<Coord> containing all tiles where the selected piece can move
     * should be moved in the controller
     */
    public ArrayList<Coord> highlighted(){
        if(selected == null)
            return null;
        return selected.getValidMoves().getAllDest();
    }

    /** public void play(Coord dest)
     * make the movement that put the selected piece in the coordinates given in argument
     * @param dest the coordinates in which the player ask to move the selected piece
     */
    public void play(Coord dest){
        if(gameState == -1)
            return;
        if(selected.isEmpty() || (selected.isWhite() != (turn==1)) )
            return;
        Move temp = selected.getValidMoves().getByDest(dest);
        temp.makeMove(game);
        game.addHistory(temp);
        changeTurn();
        selected = null;
        game.genAllMoves();
        boolean isCheck = game.isCheck(turn == 1);
        boolean hasValidMoves = game.hasLegalMoves(turn == 1);
        /*
        if(gamemode != 0){
            switch (gamemode){
                case 1:
                    kothCondition();
            }
        }
        */
        if(isCheck && !hasValidMoves)
            gameState = 3;
        else if(!isCheck && !hasValidMoves)
            gameState = 2;
        else if( isCheck )
            gameState = 1;
        else
            gameState = 0;


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
        fenCode.append(" " + enPassant + " " + halfMoveClock + " " + fullMoveCount );
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
            index ++;
        }

        buf = fen.charAt(index++);
        if(buf != '-'){
            char y = fen.charAt(index++);
            if(y == '6'){
                Coord dest = new Coord(buf-'a',3);
                Coord dep = new Coord(buf-'a',1);
                Object[] init = new Object[2];
                Piece toMove = game.getPiece(dest);
                Piece save =  game.getPiece(dep);
                game.setPiece(dep,toMove);
                init[1] = dest;
                Move toAdd = Factory.newMove("basic",init);
                game.addHistory(toAdd);
                game.setPiece(dest,toMove);
                game.setPiece(dep,save);
            }
            if( y == '3'){
                Coord dest = new Coord(buf-'a',4);
                Coord dep = new Coord(buf-'a',6);
                Object[] init = new Object[2];
                Piece toMove = game.getPiece(dest);
                Piece save =  game.getPiece(dep);
                game.setPiece(dep,toMove);
                init[1] = dest;
                Move toAdd = Factory.newMove("basic",init);
                game.addHistory(toAdd);
                game.setPiece(dest,toMove);
                game.setPiece(dep,save);
            }
        }
        index++;
        String nb = "";
        buf = fen.charAt(index++);
        while(buf != ' '){
            nb = nb +buf;
            buf = fen.charAt(index++);
        }
        halfMoveClock = Integer.parseInt(nb);
        nb = "";
        for(int i = index; i < len; i++){
            nb = nb + fen.charAt(i);
        }
        fullMoveCount = Integer.parseInt(nb);
    }

    /** private int allCastles()
     *  stock in an int the information on which castles can and can't be made anymore
     * @return an int encoded depending on the castles available
     */
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
        tmp = game.getPiece(4,0);
        if(tmp.isKing() && tmp.neverMoved()){
            tmp = game.getPiece(7,0);
            if(tmp.code()=='R' && tmp.neverMoved()){
                ret = ret | 4;
            }
            tmp = game.getPiece(0,0);
            if(tmp.code()=='R' && tmp.neverMoved()){
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
            if(buf <'1' || buf > '8' || index >= length)
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



}
