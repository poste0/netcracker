package buildings;

import buildings.Floor;

import java.util.Comparator;

public class FloorComparator implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return o1.getSquare() > o2.getSquare() ? -1 : o1.getSquare() == o2.getSquare() ? 0 : 1;
    }
}
