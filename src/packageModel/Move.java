package packageModel;

public class Move {
    private final Piece pc;
    private final Coord start;
    private final Coord dest;
    private final Piece captured;

    public Move(Piece pc, Coord start, Coord dest, Piece captured) {
        this.pc = pc;
        this.start = start;
        this.dest = dest;
        this.captured = captured;
    }

    public Move(Piece pc, Coord start, Coord dest) {
        this(pc,start,dest,null); /* maybe replace null by an Empty object */
    }

    public String toString() {
        if( captured.isEmpty() /* any way to decide on whether captured is empty or not */) {
            return pc.toString() + " move from " + start.toString() + " to " + dest.toString();
        }
        return pc.toString() + " move from " + start.toString() + " to " + dest.toString() + " captured " + captured.toString();
    }

    public Piece getPc() {
        return pc;
    }

    public Coord getStart() {
        return start;
    }

    public Coord getDest() {
        return dest;
    }

    public Piece getCaptured() {
        return captured;
    }
}
