package buildings.dwelling;

import buildings.*;
import buildings.office.OfficeBuilding;
import exception.FloorIndexOutOfBoundsException;
import exception.SpaceIndexOutOfBoundsException;

import java.util.Iterator;

public class Dwelling implements Building {
    private Floor[] dwellingFloors;

    public Dwelling(int number, int[] numberOfFlat) throws ArrayIndexOutOfBoundsException {
        dwellingFloors = new Floor[number];
        for (int i = 0; i < number; i++) {
            dwellingFloors[i] = new DwellingFloor(numberOfFlat[i]);
        }
    }
    public Dwelling(Floor[] dwellingFloors) {
        this.dwellingFloors = dwellingFloors;
    }


    @Override
    public int getNumberFloors() {
        return dwellingFloors.length;
    }
    @Override
    public int getNumberSpaces() {
        int number = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            number += dwellingFloor.getNumberSpaces();
        }
        return number;
    }
    @Override
    public double getSquare() {
        double square = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            square += dwellingFloor.getSquare();
        }
        return square;
    }
    @Override
    public int getNumberRooms() {
        int number = 0;
        for (Floor dwellingFloor : dwellingFloors) {
            number += dwellingFloor.getNumberRooms();
        }
        return number;
    }
    @Override
    public Floor[] buildingToArray() {
        return dwellingFloors;
    }
    @Override
    public Floor getFloor(int index) throws FloorIndexOutOfBoundsException {
        if (index > dwellingFloors.length - 1) throw new FloorIndexOutOfBoundsException("You can't get the floor by this index.");
        return dwellingFloors[index];
    }
    @Override
    public void setFloor(int index, Floor dwellingFloor) throws FloorIndexOutOfBoundsException{
        if (index > dwellingFloors.length - 1) throw new FloorIndexOutOfBoundsException("You can't set the floor by this index.");
        dwellingFloors[index] = dwellingFloor;
    }
    @Override
    public Space getSpace(int index) throws FloorIndexOutOfBoundsException {
        if (index > getNumberSpaces() - 1) throw new FloorIndexOutOfBoundsException("You can't get the office by this index.");
        Space space = null;
        for (int i = 0; i < dwellingFloors.length; i++) {
            if (index < dwellingFloors[i].getNumberSpaces()) {
                space = dwellingFloors[i].getSpace(index);
                i = dwellingFloors.length;
            } else {
                index -= (dwellingFloors[i].getNumberSpaces() - 1);
            }
        }
        return space;
    }
    @Override
    public void setSpace(int index, Space flat) throws FloorIndexOutOfBoundsException{
        if (index > getNumberSpaces() - 1)
            throw new FloorIndexOutOfBoundsException("You can't set the office on this place.");
        for (int i = 0; i < dwellingFloors.length; i++) {
            if (index < dwellingFloors[i].getNumberSpaces()) {
                dwellingFloors[i].setSpace(index, flat);
                break;
            } else {
                index -= (dwellingFloors[i].getNumberSpaces() - 1);
            }
        }
    }
    @Override
    public void addSpace(int index, Space flat) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        if (index > getNumberSpaces()) throw new FloorIndexOutOfBoundsException("You can't add the office on this place.");
        for (int i = 0; i < dwellingFloors.length; i++) {
            if (index <= dwellingFloors[i].getNumberSpaces()) {
                dwellingFloors[i].addSpace(index, flat);
                break;
            } else {
                index -= (dwellingFloors[i].getNumberSpaces() - 1);
            }
        }
    }
    @Override
    public void deleteSpace(int index) throws FloorIndexOutOfBoundsException {
        if (index > getNumberSpaces() - 1) throw new FloorIndexOutOfBoundsException("You can't delete the office from this place.");
        for (int i = 0; i < dwellingFloors.length; i++) {
            if (index < dwellingFloors[i].getNumberSpaces()) {
                dwellingFloors[i].deleteSpace(index);
                break;
            } else {
                index -= (dwellingFloors[i].getNumberSpaces() - 1);
            }
        }
    }
    @Override
    public Space getBestSpace() {
        double square = 0;
        Space maxFlat = null;
        for (int i = 0; i < dwellingFloors.length; i++) {
            if (dwellingFloors[i].getBestSpace().getSquare() > square) {
                square = (maxFlat = dwellingFloors[i].getBestSpace()).getSquare();
            }
        }
        return maxFlat;
    }
    @Override
    public Space[] sort() {
        Space[] flatsOfSort = new Space[getNumberSpaces()];
        Space flat;
        double maxSquare;
        boolean enter;
        for (int i = 0; i < getNumberSpaces(); i++) {
            flat = null;
            maxSquare = 0;
            for (int j = 0; j < dwellingFloors.length; j++) {
                for (int k = 0; k < dwellingFloors[j].getNumberSpaces(); k++) {
                    enter = true;
                    for (int l = 0; l < i; l++) {
                        if (dwellingFloors[j].getSpace(k) == flatsOfSort[l]) enter = false;
                    }

                    if (enter) {
                        if (dwellingFloors[j].getSpace(k).getSquare() > maxSquare) {
                            maxSquare = dwellingFloors[j].getSpace(k).getSquare();
                            flat = dwellingFloors[j].getSpace(k);
                        }
                    }
                }
            }
            flatsOfSort[i] = flat;
        }
        return flatsOfSort;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new Iterator() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current != dwellingFloors.length;
            }

            @Override
            public Floor next() {
                return dwellingFloors[current++];
            }
        };
    }

    @Override
    public Building clone() throws CloneNotSupportedException {
        Floor[] dwellingFloorsOfBuf = new Floor[dwellingFloors.length];
        for (int i = 0; i < dwellingFloorsOfBuf.length; i++) {
            dwellingFloorsOfBuf[i] = dwellingFloors[i].clone();
        }
        return new Dwelling(dwellingFloorsOfBuf);
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Dwelling (");
        buffer.append(getNumberFloors());
        for (int i = 0; i < getNumberFloors(); i++) {
            buffer.append(", ");
            buffer.append(getFloor(i));
        }
        return buffer.toString();
    }
    @Override
    public boolean equals(Object object){
        boolean result = false;
        if(object instanceof Dwelling){
            if((result = (dwellingFloors.length == ((Dwelling) object).dwellingFloors.length))){
                for (int i = 0; i < dwellingFloors.length && result; i++) {
                    result = dwellingFloors[i].equals(((Dwelling) object).dwellingFloors[i]);
                }
            }
        } else if (object instanceof OfficeBuilding){
            if((result = (dwellingFloors.length == ((OfficeBuilding) object).getNumberFloors()))){
                for (int i = 0; i < dwellingFloors.length && result; i++) {
                    result = dwellingFloors[i].equals(((OfficeBuilding) object).getFloor(i));
                }
            }
        }
        return result;
    }
    @Override
    public int hashCode(){
        int result = dwellingFloors.length;
        for (int i = 0; i < dwellingFloors.length; i++) {
            result ^= dwellingFloors[i].hashCode();
        }
        return result;
    }
}
