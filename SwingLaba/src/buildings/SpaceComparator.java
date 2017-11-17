package buildings;

import buildings.Space;

import java.util.Comparator;

public class SpaceComparator implements Comparator<Space>{
    @Override
    public int compare(Space o1, Space o2) {
        return o1.getNumber() > o2.getNumber() ? -1 : o1.getNumber() == o2.getNumber() ? 0 : 1;
    }
}
