package buildings.office;

import buildings.Space;
import buildings.dwelling.Flat;
import exception.InvalidRoomsCountException;
import exception.InvalidSpaceAreaException;

public class Office implements Space {
    private final double SQUARE = 250;
    private final int NUMBER = 1;
    private double square;
    private int number;

    public Office(){
        square = SQUARE;
        number = NUMBER;
    }
    public Office(double square)throws InvalidSpaceAreaException{
        if(square <= 0) throw new InvalidSpaceAreaException("The square can't be less 0.");
        this.square = square;
        number = 1;
    }
    public Office(int number, double square)throws InvalidSpaceAreaException, InvalidRoomsCountException{
        if(number <= 0) throw new InvalidRoomsCountException("The number of rooms can't be less 0.");
        if(square <= 0) throw new InvalidSpaceAreaException("The square can't be less 0.");
        this.square = square;
        this.number = number;
    }
    @Override
    public Space clone() throws CloneNotSupportedException {
        return (Space) super.clone();
    }

    @Override
    public void setSquare(double square) throws InvalidSpaceAreaException{
        if(square <= 0) throw new InvalidSpaceAreaException("The square can't be less 0.");
        this.square = square;
    }
    @Override
    public void setNumber(int number) throws InvalidRoomsCountException{
        if(number <= 0) throw new InvalidRoomsCountException("The number of rooms can't be less 0.");
        this.number = number;
    }
    @Override
    public double getSquare() {

        return square;
    }
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Space o) {
        return square > o.getSquare() ? 1 : square == o.getSquare() ? 0 : -1;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("Office (");
        buffer.append(number);
        buffer.append(", ");
        buffer.append(square);
        buffer.append(")");
        return buffer.toString();
    }
    @Override
    public boolean equals(Object object){
        boolean result = false;
        if(object instanceof Office){
            result = (number == ((Office) object).number && square == ((Office) object).square);
        } else if (object instanceof Flat){
            result = (number == ((Flat) object).getNumber() && square == ((Flat) object).getSquare());
        }
        return result;
    }
    @Override
    public int hashCode(){
        return (((int)(Double.doubleToLongBits(square) >> 32)) ^ (int)(Double.doubleToLongBits(square)) ^ number);
    }

}
