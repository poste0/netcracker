package buildings.office;


import buildings.Floor;
import buildings.Space;
import buildings.dwelling.DwellingFloor;
import exception.SpaceIndexOutOfBoundsException;

import javax.swing.text.html.HTMLDocument;
import java.io.Serializable;
import java.util.Iterator;

public class OfficeFloor implements Floor {
    private Node head; // index = -1
    private int size;

    private Node getNodeByIndex(int index){
        Node bufferNode = head.next;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        return bufferNode;
    }
    private void addNodeByIndex(int index, Node node){
        Node bufferNode = head;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        node.next = bufferNode.next;
        bufferNode.next = node;
        ++size;
    }
    private void deleteNodeByIndex(int index){
        Node bufferNode = head;
        for (int i = 0; i < index; i++) {
            bufferNode = bufferNode.next;
        }
        bufferNode.next = bufferNode.next.next;
        --size;
    }

    public OfficeFloor(int size) {
        this.size = size;
        head = new Node(null, null);
        head.next = head;
        Node bufferNode = head;
        for (int i = 0; i < size; i++) {
            bufferNode.next = new Node(new Office(), head);
            bufferNode = bufferNode.next;
        }
    }
    public OfficeFloor(Space[] offices){
        if(offices == null){
            size = 0;
        } else {
            size = offices.length;
        }
        head = new Node(null, null);
        head.next = head;
        Node bufferNode = head;
        for (int i = 0; i < size; i++) {
            bufferNode.next = new Node(offices[i], head);
            bufferNode = bufferNode.next;
        }
    }
    @Override
    public Floor clone() throws CloneNotSupportedException {
        Space[] spaces = new Space[size];
        for (int i = 0; i < size; i++) {
            spaces[i] = getSpace(i).clone();
        }
        return new OfficeFloor(spaces);
    }

    @Override
    public int getNumberSpaces(){
        return size;
    }
    @Override
    public double getSquare(){
        double square = 0;
        for (int i = 0; i < size; i++) {
            square += getNodeByIndex(i).office.getSquare();
        }
        return square;
    }
    @Override
    public int getNumberRooms(){
        int number = 0;
        for (int i = 0; i < size; i++) {
            number += getNodeByIndex(i).office.getNumber();
        }
        return number;
    }
    @Override
    public Space[] floorToArray(){
        Space[] offices = new Office[size];
        for (int i = 0; i < size; i++) {
            offices[i] = getNodeByIndex(i).office;
        }
        return offices;
    }
    @Override
    public Space getSpace(int index)throws SpaceIndexOutOfBoundsException{
        if(index >= size) throw new SpaceIndexOutOfBoundsException("You can't get the office by this index.");
        return getNodeByIndex(index).office;
    }
    @Override
    public void setSpace(int index, Space office)throws SpaceIndexOutOfBoundsException{
        if(index >= size) throw new SpaceIndexOutOfBoundsException("You can't set the office on this place.");
        getNodeByIndex(index).office = office;
    }
    @Override
    public void addSpace(int index, Space office)throws SpaceIndexOutOfBoundsException{
        if(index != size) throw new SpaceIndexOutOfBoundsException("You can't add the office on this place.");
        addNodeByIndex(index, new Node(office, null));
    }
    @Override
    public void deleteSpace(int index) throws SpaceIndexOutOfBoundsException{
        if(index >= size) throw new SpaceIndexOutOfBoundsException("You can't delete the office from this place.");
        deleteNodeByIndex(index);
    }
    @Override
    public Space getBestSpace(){
        Space office = null;
        double square = 0;
        for (int i = 0; i < size; i++) {
            if(getSpace(i).getSquare() > square){
                square = (office = getSpace(i)).getSquare();
            }
        }
        return office;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator() {
            private Node current = head;
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return (currentIndex != size);
            }

            @Override
            public Space next() {
                currentIndex++;
                return (current = current.next).office;
            }
        };
    }

    @Override
    public int compareTo(Floor o) {
        return size > o.getNumberSpaces() ? 1 : size == o.getNumberSpaces() ? 0 : -1;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("OfficeFloor (");
        buffer.append(getNumberSpaces());
        for (int i = 0; i < getNumberSpaces(); i++) {
            buffer.append(", ");
            buffer.append(getSpace(i));
        }
        buffer.append(")");
        return buffer.toString();
    }
    @Override
    public boolean equals(Object object){
        boolean result = false;
        if(object instanceof OfficeFloor){
            if((result = (size == ((OfficeFloor) object).size))){
                for (int i = 0; i < size && result; i++) {
                    result = (getNodeByIndex(i).office.equals(((OfficeFloor) object).getNodeByIndex(i).office));
                }
            }
        } else if (object instanceof DwellingFloor){
            if((result = (size == ((DwellingFloor) object).getNumberSpaces()))){
                for (int i = 0; i < size && result; i++) {
                    result = (getNodeByIndex(i).office.equals(((DwellingFloor) object).getSpace(i)));
                }
            }
        }
        return result;
    }

    @Override
    public int hashCode(){
        int result = size;
        for (int i = 0; i < size; i++) {
            result ^= getSpace(i).hashCode();
        }
        return result;
    }
    private class Node implements Serializable{
        public Space office;
        public Node next;

        public Node(Space office, Node next){
            this.office = office;
            this.next = next;
        }
    }
}
