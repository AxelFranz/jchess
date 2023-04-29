package packageModel;

public class Coord {
    public int x;
    public int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* return a String corresponding to the coordinates used in chess */
    public String toString() {
        char row = (char) (65+x);
        return "" + row + (8-y)%9;
    }

}
