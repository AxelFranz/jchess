package packageModel;

public interface Move {
    public void undoMove(Board board);
    public void makeMove(Board board);
    public String toString();
    public boolean compareDest(Coord dest);
    public MoveId moveType();
    public Coord getDest();
    public Piece getPiece();
    public Coord getStart();
    public boolean compare(Move tmp);

}
