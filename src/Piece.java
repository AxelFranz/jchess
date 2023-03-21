public abstract class Piece {
    private Coord coords;
    private String ImgPath; /* idk if we keep it or put Image class instead */

    abstract Boolean check_move(Coord x); /* False if not possible */
    abstract Coord[] gen_all_moves(); /* Returns all possible move */

}
