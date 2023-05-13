package packageModel;

import java.util.ArrayList;

public class MoveList extends ArrayList<Move> {

    public Move getByDest(Coord dest){
        for(Move parc: this){
            if (parc.compareDest(dest))
                return parc;
        }
        return null;
    }
    public ArrayList<Coord> getAllDest(){
        ArrayList<Coord> ret = new ArrayList<Coord>();
        for(Move parc: this){
            ret.add(parc.getDest());
        }
        return ret;
    }

}
