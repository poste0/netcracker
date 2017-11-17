package buildings;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import exception.FloorIndexOutOfBoundsException;
import exception.InexchangeableFloorsException;
import exception.InexchangeableSpacesException;
import exception.SpaceIndexOutOfBoundsException;

public class PlacementExchanger {
    public static boolean isSwapSpaces(Space space1, Space space2){
        return (space1.getSquare() == space2.getSquare() && space1.getNumber() == space2.getNumber());
    }
    public static boolean isSwapFloors(Floor floor1, Floor floor2){
        return (floor1.getSquare() == floor2.getSquare() && floor1.getNumberSpaces() == floor2.getNumberSpaces());
    }
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException, SpaceIndexOutOfBoundsException{
        if (isSwapSpaces(floor1.getSpace(index1), floor2.getSpace(index2))) {
            Space space = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, space);
        } else {
            throw new InexchangeableSpacesException("You can't swap this spaces.");
        }
    }
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException, FloorIndexOutOfBoundsException{
        if(isSwapFloors(building1.getFloor(index1), building2.getFloor(index2))){
            Floor floor = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, floor);
        } else {
            throw new InexchangeableFloorsException("You can't swap this floors.");
        }
    }
}
