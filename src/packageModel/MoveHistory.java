package packageModel;

import java.util.ArrayList;
import java.util.Stack;

public class MoveHistory extends ArrayList<Move> {

    public void addMove(Move x){
        this.add(x);
    }
    public void popMove(){
        this.remove(this.size()-1);
    }

    @Override
    public MoveHistory clone() {
        return (MoveHistory) this.clone();
    }

    public boolean threefold(){
        if(size() >= 6){
            Move[] test= {get(size()-1),get(size()-2),get(size()-5), get(size()-6)};
            return test[0].compare(test[2]) && test[1].compare(test[3]);
        }
        return false;
    }
}
