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
}
