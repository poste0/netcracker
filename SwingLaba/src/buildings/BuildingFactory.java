package buildings;

import buildings.Building;
import buildings.Floor;
import buildings.Space;

public interface BuildingFactory {
    Space createSpace(double area);
    Space createSpace(int roomsCount, double area);
    Floor createFloor(int spacesCount);
    Floor createFloor(Space[] spaces);
    Building createBuilding(int floorsCount, int[] spacesCounts);
    Building createBuilding(Floor[] floors);
}

