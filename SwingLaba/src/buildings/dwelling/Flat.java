package buildings.dwelling;

import buildings.office.Office;
import buildings.Space;
import exception.InvalidRoomsCountException;
import exception.InvalidSpaceAreaException;

public class Flat implements Space {
    private double square;
    private int number;

    private final double SQUARE = 50;
    private final int NUMBER = 2;

    public Flat() {
        square = SQUARE;
        number = NUMBER;
    }

    public Flat(double square) throws InvalidSpaceAreaException{
        this.square = square;
        number = NUMBER;
    }

    public Flat(int number, double square) throws InvalidSpaceAreaException, InvalidRoomsCountException{
        this.square = square;
        this.number = number;
    }
    @Override
    public int getNumber(){
        return number;
    }

    @Override
    public int compareTo(Space o) {
        return square > o.getSquare() ? 1 : square == o.getSquare() ? 0 : -1;
    }

    @Override
    public void setNumber(int number) throws InvalidRoomsCountException{
        if(number <= 0) throw new InvalidRoomsCountException("The number of rooms can't be less 0.");
        this.number = number;
    }
    @Override
    public double getSquare(){
        return square;
    }
    @Override
    public void setSquare(double square) throws InvalidSpaceAreaException{
        if(square <= 0) throw new InvalidSpaceAreaException("The square can't be less 0.");
        this.square = square;
    }
    @Override
    public Space clone() throws CloneNotSupportedException {
        return (Space) super.clone();
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Flat (");
        buffer.append(number);
        buffer.append(", ");
        buffer.append(square);
        buffer.append(")");
        return buffer.toString();
    }
    @Override
    public boolean equals(Object object){
        boolean result = false;
        if(object instanceof Flat){
            result = (number == ((Flat) object).number && square == ((Flat) object).square);
        } else if (object instanceof Office){
            result = (number == ((Office) object).getNumber() && square == ((Office) object).getSquare());
        }
        return result;
    }
    @Override
    public int hashCode(){
        return (((int)(Double.doubleToLongBits(square) >> 32)) ^ (int)(Double.doubleToLongBits(square)) ^ number);
    }
}
