package packageModel;

public interface Move {
    public void undoMove(Board board);
    public void makeMove(Board board);
    public String toString();
    public Coord getCapturedPos();


}
