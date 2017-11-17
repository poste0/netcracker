package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;

public class HotelBuilding extends Dwelling {

    public HotelBuilding(int number, int[] numberOfFlat)  {
        super(number, numberOfFlat);
        for (int i = 0; i < number; i++) {
            setFloor(i, new HotelFloor(numberOfFlat[i]));
        }
    }

    public HotelBuilding(Floor[] dwellingFloors) {
        super(dwellingFloors);
    }
    public int getStars(){
        int stars = 0;
        for (int i = 0; i < super.getNumberFloors(); i++) {
            if(getFloor(i) instanceof HotelFloor && ((HotelFloor) getFloor(i)).getStars() > stars){
                stars = ((HotelFloor) getFloor(i)).getStars();
            }
        }
        return stars;
    }
    @Override
    public Space getBestSpace(){
        double best = 0;
        Space bestFlat = null;
        for (int i = 0; i < getNumberFloors(); i++) {
            if(getFloor(i) instanceof HotelFloor){
                if(getFloor(i).getBestSpace().getSquare() * ((HotelFloor) getFloor(i)).getStars() > best){
                    best = (bestFlat = getFloor(i).getBestSpace()).getSquare() * (((HotelFloor) getFloor(i)).getStars() < 3 ?
                    ((HotelFloor) getFloor(i)).getStars()* (0.25) : ((HotelFloor) getFloor(i)).getStars()* (0.25) + 0.25);
                }
            }
        }
        return bestFlat;
    }
    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("HotelBuilding (");
        buffer.append(getStars());
        buffer.append(", ");
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
        if(object instanceof HotelBuilding){
            if((result = (getNumberFloors() == (((HotelBuilding) object).getNumberFloors())))){
                for (int i = 0; i <getNumberFloors() && result; i++) {
                    result = (getFloor(i).equals(((HotelBuilding) object).getFloor(i)));
                }
            }
        }
        return result;
    }
    @Override
    public int hashCode(){
        int result = getNumberFloors();
        for (int i = 0; i < getNumberFloors(); i++) {
            result ^= getFloor(i).hashCode();
        }
        return result;
    }
}
