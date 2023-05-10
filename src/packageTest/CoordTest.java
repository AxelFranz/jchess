package packageTest;

import packageModel.Coord;

public class CoordTest {
    public static void main(String[] args) {
        Coord test1 = new Coord(0,0);
        Coord test2 = new Coord(7,7);
        Coord test3 = new Coord(3,3);
        Coord test4 = new Coord(0,7);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(Coord.addCoord(test3,test3));
    }

}
