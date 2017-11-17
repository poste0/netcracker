package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;

public class HotelFloor extends DwellingFloor {
    private int stars;
    private final int STARS = 1;
    public HotelFloor(int num) {
        super(num);
        stars = STARS;
    }

    public HotelFloor(Space[] flats) {
        super(flats);
        stars = STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("HotelFloor (");
        buffer.append(stars);
        buffer.append(", ");
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
        if (object instanceof HotelFloor){
            result = (((HotelFloor) object).stars == stars && super.equals(object));
        }
        return result;
    }
    @Override
    public int hashCode(){
        return super.hashCode()^stars;
    }
}
