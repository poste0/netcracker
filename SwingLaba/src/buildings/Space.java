package buildings;

import exception.InvalidRoomsCountException;
import exception.InvalidSpaceAreaException;

import java.io.Serializable;

public interface Space extends Cloneable, Serializable, Comparable<Space>{
    Space clone() throws CloneNotSupportedException;
    void setSquare(double square) throws InvalidSpaceAreaException;
    void setNumber(int number) throws InvalidRoomsCountException;
    double getSquare();
    int getNumber();
    int compareTo(Space o);
}
