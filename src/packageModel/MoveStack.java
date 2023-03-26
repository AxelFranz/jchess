package packageModel;

import java.util.Stack;

public class MoveStack {
    private Stack<Move> moves;

    MoveStack( Stack<Move> moves) {
        this.moves = moves;
    }
    MoveStack() {
        this(new Stack<Move>());
    }

    public void addMove(Move x){
        moves.add(x);
    }
    public void popMove(){
        moves.pop();
    }
    public Stack<Move> getMoves() {
        return moves;
    }
}
