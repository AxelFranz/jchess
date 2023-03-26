package packageModel;

public class Move {
    private Piece pc;
    private Coord start;
    private Coord dest;
    private Piece captured;

    public Move(Piece pc, Coord start, Coord dest, Piece captured) {
        this.pc = pc;
        this.start = start;
        this.dest = dest;
        this.captured = captured;
    }

    public Move(Piece pc, Coord start, Coord dest) {
        this(pc,start,dest,null); /* maybe replace null by an Empty object */
    }
}
