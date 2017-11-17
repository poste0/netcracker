package buildings;

import exception.FloorIndexOutOfBoundsException;
import exception.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public interface Building extends Cloneable, Serializable, Iterable{
    Building clone() throws CloneNotSupportedException;
    int getNumberFloors();
    int getNumberSpaces();
    double getSquare();
    int getNumberRooms();
    Floor[] buildingToArray();
    Floor getFloor(int index) throws FloorIndexOutOfBoundsException;
    void setFloor(int index, Floor floor) throws FloorIndexOutOfBoundsException;
    Space getSpace(int index) throws FloorIndexOutOfBoundsException;
    void setSpace(int index, Space space) throws FloorIndexOutOfBoundsException;
    void addSpace(int index, Space space) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException;
    void deleteSpace(int index) throws FloorIndexOutOfBoundsException;
    Space getBestSpace();
    Space[] sort();
    Iterator<Floor> iterator();
}
