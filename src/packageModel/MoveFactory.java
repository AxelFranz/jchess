package packageModel;

import packageModel.moveImplementations.*;

import java.util.Objects;

public class MoveFactory {
    public static Move newMove(String moveType, Object array[]) {
        if(Objects.equals(moveType, "basic") && array.length >= 3) {
            return new basicMovement((Piece)array[0],(Coord)array[1],(Coord)array[2]);
        } else if (Objects.equals(moveType, "capture") && array.length >= 4) {
            return new CaptureMovement((Piece)array[0],(Coord)array[1],(Coord)array[2], (Piece)array[3]);
        } else
            return null;
    }
}
