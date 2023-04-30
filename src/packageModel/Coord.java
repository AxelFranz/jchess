package packageModel;

import java.util.ArrayList;

public class Coord {
    private final int x;
    private final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static Coord addCoord(Coord pos1,Coord pos2)
    {
        return new Coord(pos1.x + pos2.x, pos1.y + pos2.y);
    }
    public Coord addX( int x ) {
        return new Coord(this.x+x,y);
    }

    public Coord addY ( int y ){
        return new Coord(x,this.y+y);
    }
    public Coord addXY ( int x, int y){
        return new Coord(this.x + x , this.y + y);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    /* return a String corresponding to the coordinates used in chess */
    public String toString() {
        char row = (char) (65+x);
        return "" + row + (8-y);
    }

    public static void addToArray(ArrayList<Coord> lst, Coord pos) {
        if(Board.inBoard(pos))
            lst.add(pos);
    }

}
