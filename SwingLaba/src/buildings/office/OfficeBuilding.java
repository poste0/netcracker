package buildings.office;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import com.sun.media.sound.WaveFloatFileReader;
import exception.FloorIndexOutOfBoundsException;
import exception.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Iterator;

public class OfficeBuilding implements Building {

    private class Node implements Serializable{
        Floor officeFloor;
        Node next;
        Node prev;

        public Node(Floor officeFloor) {
            this.officeFloor = officeFloor;
            next = this;
            prev = this;
        }

        public Node(Floor officeFloor, Node next, Node prev) {

            this.officeFloor = officeFloor;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node head;
    private int size;

    private Node getNodeByIndex(int index){
        Node bufferNode = head.next;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        return bufferNode;
    }
    private void addNodeByIndex(int index, Node node){
        Node bufferNode = head.next;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        node.next = bufferNode;
        node.prev = bufferNode.prev;
        bufferNode.prev = (bufferNode.prev.next = node);
        ++size;
    }
    private void deleteNodeByIndex(int index){
        Node bufferNode = head.next;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        bufferNode.prev.next = bufferNode.next;
        bufferNode.next.prev = bufferNode.prev;
        --size;
    }
    public  OfficeBuilding(int size, int[] numbers){
        Node bufferNode = (head = new Node(null));
        for (int i = 0; i < size; i++) {
            addNodeByIndex(i, new Node(new OfficeFloor(numbers[i]), head, bufferNode));
            bufferNode = bufferNode.next;
        }
    }
    public OfficeBuilding(Floor[] officeFloors){
        Node bufferNode = (head = new Node(null));
        for (int i = 0; i < officeFloors.length; i++) {
            addNodeByIndex(i, new Node(officeFloors[i], head, bufferNode));
            bufferNode = bufferNode.next;
        }
    }
    @Override
    public int getNumberFloors(){
        return size;
    }
    @Override
    public int getNumberSpaces(){
        int number = 0;
        for (int i = 0; i < size; i++) {
            number += getNodeByIndex(i).officeFloor.getNumberSpaces();
        }
        return number;
    }
    @Override
    public double getSquare(){
        double square = 0;
        for (int i = 0; i < size; i++) {
            square += getNodeByIndex(i).officeFloor.getSquare();
        }
        return square;
    }
    @Override
    public int getNumberRooms(){
        int number = 0;
        for (int i = 0; i < size; i++) {
            number += getNodeByIndex(i).officeFloor.getNumberRooms();
        }
        return number;
    }
    @Override
    public Floor[] buildingToArray(){
        Floor[] officeFloors = new Floor[size];
        for (int i = 0; i < size; i++) {
            officeFloors[i] = getNodeByIndex(i).officeFloor;
        }
        return officeFloors;
    }
    @Override
    public Floor getFloor(int index) throws FloorIndexOutOfBoundsException{
        if (index > size - 1) throw new FloorIndexOutOfBoundsException("You can't get the floor by this index.");
        return getNodeByIndex(index).officeFloor;
    }
    @Override
    public void setFloor(int index, Floor officeFloor) throws FloorIndexOutOfBoundsException{
        if (index > size - 1) throw new FloorIndexOutOfBoundsException("You can't set the floor by this index.");
        getNodeByIndex(index).officeFloor = officeFloor;
    }
    @Override
    public Space getSpace(int index) throws FloorIndexOutOfBoundsException{
        if (index > getNumberSpaces() - 1) throw new FloorIndexOutOfBoundsException("You can't get the office by this index.");
        Space space = null;
            for (int i = 0; i < size; i++) {
                if(index < getFloor(i).getNumberSpaces()){
                    space = getFloor(i).getSpace(index);
                    i = size;
                } else {
                    index -= (getFloor(i).getNumberSpaces() - 1);
                }
        }
        return space;
    }
    @Override
    public void setSpace(int index, Space office) throws FloorIndexOutOfBoundsException{
        if (index > getNumberSpaces() - 1) throw new FloorIndexOutOfBoundsException("You can't set the office on this place.");
            for (int i = 0; i < size; i++) {
                if (index < getFloor(i).getNumberSpaces()) {
                    getFloor(i).setSpace(index, office);
                    break;
                } else {
                    index -= (getFloor(i).getNumberSpaces() - 1);
                }
            }
    }
    @Override
    public void addSpace(int index, Space office) throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException {
       if (index > getNumberSpaces()) throw new FloorIndexOutOfBoundsException("You can't add the office on this place.");
        for (int i = 0; i < size; i++) {
            if (index <= getFloor(i).getNumberSpaces()) {
                getFloor(i).addSpace(index, office);
                break;
            } else {
                index -= (getFloor(i).getNumberSpaces() - 1);
            }
        }
    }
    @Override
    public void deleteSpace(int index)  throws SpaceIndexOutOfBoundsException, FloorIndexOutOfBoundsException{
        if (index < getNumberSpaces()) {
            for (int i = 0; i < size; i++) {
                if(index < getFloor(i).getNumberSpaces()){
                    getFloor(i).deleteSpace(index);
                    break;
                } else {
                    index -= (getFloor(index).getNumberSpaces() - 1);
                }
            }
        } else {
            throw new FloorIndexOutOfBoundsException("You can't delete the office from this place.");
        }
    }
    @Override
    public Space getBestSpace(){
        Space office = null;
        double square = 0;
        for (int i = 0; i < size; i++) {
            if (getFloor(i).getBestSpace().getSquare() > square){
                square = (office = getFloor(i).getBestSpace()).getSquare();
            }
        }
        return office;
    }
    @Override
    public Space[] sort(){
        Space[] officesOfSort = new Space[getNumberSpaces()];
        Space office;
        double maxSquare;
        boolean enter;
        for (int i = 0; i < officesOfSort.length; i++) {
            office = null;
            maxSquare = 0;
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < getFloor(j).getNumberSpaces(); k++) {
                    enter = true;
                    for (int l = 0; l < i; l++) {
                        if (getFloor(j).getSpace(k) == officesOfSort[l]) enter = false;
                    }

                    if (enter) {
                        if (getFloor(j).getSpace(k).getSquare() > maxSquare) {
                            maxSquare = getFloor(j).getSpace(k).getSquare();
                            office = getFloor(j).getSpace(k);
                        }
                    }
                }
            }
            officesOfSort[i] = office;
        }
        return officesOfSort;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new Iterator() {
            Node current = head;
            int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex != size;
            }

            @Override
            public Floor next() {
                currentIndex++;
                return (current = current.next).officeFloor;
            }
        };
    }

    @Override
    public Building clone() throws CloneNotSupportedException {
        Floor[] floors = new Floor[size];
        for (int i = 0; i < size; i++) {
            floors[i] = getFloor(i).clone();
        }
        return (Building) new OfficeBuilding(floors);
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("OfficeBuilding (");
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
        if (object instanceof OfficeBuilding) {
            if((result = (size == ((OfficeBuilding) object).size))){
                for (int i = 0; i < size && result; i++) {
                    result = getFloor(i).equals(((OfficeBuilding) object).getFloor(i));
                }
            }
        } else if (object instanceof Dwelling){
            if((result = (size == ((Dwelling) object).getNumberFloors()))){
                for (int i = 0; i < size && result; i++) {
                    result = getFloor(i).equals(((Dwelling) object).getFloor(i));
                }
            }
        }
        return result;
    }
    @Override
    public int hashCode(){
        int result = size;
        for (int i = 0; i < size; i++) {
            result ^= getFloor(i).hashCode();
        }
        return result;
    }
}
