package buildings;

import exception.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Cloneable, Serializable, Iterable<Space>, Comparable<Floor>{
    Floor clone() throws CloneNotSupportedException;
    int getNumberSpaces();
    double getSquare();
    int getNumberRooms();
    Space[] floorToArray();
    Space getSpace(int index) throws SpaceIndexOutOfBoundsException;
    void setSpace(int index, Space space) throws SpaceIndexOutOfBoundsException;
    void addSpace(int index, Space space) throws SpaceIndexOutOfBoundsException;
    void deleteSpace(int index) throws SpaceIndexOutOfBoundsException;
    Space getBestSpace();
    Iterator<Space> iterator();
    int compareTo(Floor o);
}
