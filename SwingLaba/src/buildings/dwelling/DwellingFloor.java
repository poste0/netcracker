package buildings.dwelling;

import buildings.Floor;
import buildings.office.OfficeFloor;
import buildings.Space;
import exception.SpaceIndexOutOfBoundsException;

import java.util.Iterator;

public class DwellingFloor implements Floor {
    private Space[] flats;

    public DwellingFloor(int num) {
        flats = new Space[num];
        for (int i = 0; i < num; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor(Space[] flats) {
        this.flats = flats;

    }
    @Override
    public int getNumberSpaces() {
        return flats.length;
    }
    @Override
    public double getSquare() {
        double square = 0;
        for (Space flat : flats) {
            square += flat.getSquare();
        }
        return square;
    }
    @Override
    public int getNumberRooms() {
        int number = 0;
        for (Space flat : flats) {
            number += flat.getNumber();
        }
        return number;
    }
    @Override
    public Space[] floorToArray() {
        return flats;
    }
    @Override
    public Space getSpace(int index) throws SpaceIndexOutOfBoundsException {
        if(index >= flats.length) throw new SpaceIndexOutOfBoundsException("You can't get the office by this index.");
        return flats[index];
    }
    @Override
    public void setSpace(int index, Space flat) throws SpaceIndexOutOfBoundsException{
        if(index >= flats.length) throw new SpaceIndexOutOfBoundsException("You can't add the office on this place.");
        flats[index] = flat;
    }


    @Override
    public void addSpace(int index, Space flat) throws SpaceIndexOutOfBoundsException{
        if (index != flats.length) throw new SpaceIndexOutOfBoundsException("You can't add the office on this place.");
        Space[] flatBuf = new Flat[index + 1];
        for (int i = 0; i < index; i++) {
            flatBuf[i] = flats[i];
        }
        flatBuf[index] = flat;
        flats = flatBuf;
    }
    @Override
    public void deleteSpace(int index) throws SpaceIndexOutOfBoundsException {
        if(index >= flats.length) throw new SpaceIndexOutOfBoundsException("You can't delete the office from this place.");
        Space[] flatsBuf = new Space[flats.length - 1];
        for (int i = 0; i < index; i++) {
            flatsBuf[i] = flats[i];
        }
        for (int i = index; i < flats.length - 1; i++) {
            flatsBuf[i] = flats[i + 1];
        }
        flats = flatsBuf;

    }
    @Override
    public Space getBestSpace(){
        Space maxFlat = null;
        double maxSquare = 0;
        for (Space flat : flats) {
            if(flat.getSquare() > maxSquare) {
                maxSquare = flat.getSquare();
                maxFlat = flat;
            }
        }
        return maxFlat;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator() {
            int current = 0;
            @Override
            public boolean hasNext() {
                return current != flats.length;
            }

            @Override
            public Space next() {
                return flats[current++];
            }
        };
    }

    @Override
    public int compareTo(Floor o) {
        return flats.length > o.getNumberSpaces() ? 1 : flats.length == o.getNumberSpaces() ? 0 : -1;
    }

    @Override
    public Floor clone() throws CloneNotSupportedException {
        Space[] flatsOfBuf = new Space[flats.length];
        for (int i = 0; i < flats.length; i++) {
            flatsOfBuf[i] = flats[i].clone();
        }
        return new DwellingFloor(flatsOfBuf);
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("DwellingFloor (");
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
            if((result = (flats.length == ((OfficeFloor) object).getNumberSpaces()))){
                for (int i = 0; i < flats.length && result; i++) {
                    result = (flats[i].equals(((OfficeFloor) object).getSpace(i)));
                }
            }
        } else if (object instanceof DwellingFloor){
            if((result = (flats.length == ((DwellingFloor) object).flats.length))){
                for (int i = 0; i < flats.length && result; i++) {
                    result = (flats[i].equals(((DwellingFloor) object).flats[i]));
                }
            }
        }
        return result;
    }
    @Override
    public int hashCode(){
        int result = flats.length;
        for (int i = 0; i < flats.length; i++) {
            result ^= flats[i].hashCode();
        }
        return result;
    }
}




