package buildings;

import exception.SpaceIndexOutOfBoundsException;

import java.util.Iterator;

public class SynhronizedFloor implements Floor {
    private Floor floor;

    public SynhronizedFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public synchronized Floor clone() throws CloneNotSupportedException {
        return floor.clone();
    }

    @Override
    public synchronized int getNumberSpaces() {
        return floor.getNumberSpaces();
    }

    @Override
    public synchronized double getSquare() {
        return floor.getSquare();
    }

    @Override
    public synchronized int getNumberRooms() {
        return floor.getNumberRooms();
    }

    @Override
    public synchronized Space[] floorToArray() {
        return floor.floorToArray();
    }

    @Override
    public synchronized Space getSpace(int index) throws SpaceIndexOutOfBoundsException {
        return floor.getSpace(index);
    }

    @Override
    public synchronized void setSpace(int index, Space space) throws SpaceIndexOutOfBoundsException {
        floor.setSpace(index, space);
    }

    @Override
    public synchronized void addSpace(int index, Space space) throws SpaceIndexOutOfBoundsException {
        floor.addSpace(index, space);
    }

    @Override
    public synchronized void deleteSpace(int index) throws SpaceIndexOutOfBoundsException {
        floor.deleteSpace(index);
    }

    @Override
    public synchronized Space getBestSpace() {
        return floor.getBestSpace();
    }

    @Override
    public synchronized Iterator<Space> iterator() {
        return floor.iterator();
    }

    @Override
    public synchronized int compareTo(Floor o) {
        return floor.compareTo(o);
    }
    @Override
    public synchronized String toString(){
        return floor.toString();
    }
    @Override
    public synchronized boolean equals(Object object){
        return floor.equals(object);
    }
    @Override
    public synchronized int hashCode(){
        return floor.hashCode();
    }
}
