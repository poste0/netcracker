package buildings;

import java.io.*;
import java.util.*;

public class Buildings {
    public static Floor synhronizedFloor(Floor floor){
        return new SynhronizedFloor(floor);
    }
    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    private static BuildingFactory buildingFactory = new DwellingFactory();

    public static Space createSpace(double area){
        return buildingFactory.createSpace(area);
    }
    public static Space createSpace(int roomsCount, double area){
        return buildingFactory.createSpace(roomsCount, area);
    }
    public static Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }
    public static Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }
    public static Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }
    public static Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }

    public static <T extends Comparable<T>> void sort(T[] array) throws IndexOutOfBoundsException{
        T buffer;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if(array[i].compareTo(array[j]) == 1){
                    buffer = array[i];
                    array[i] = array[j];
                    array[j] = buffer;
                }
            }
        }
    }
    /* public static void sort(Space[] array) throws IndexOutOfBoundsException{
         Space buffer;
         for (int i = 0; i < array.length; i++) {
             for (int j = i; j < array.length; j++) {
                 if(array[i].compareTo(array[j]) == 1){
                     buffer = array[i];
                     array[i] = array[j];
                     array[j] = buffer;
                 }
             }
         }
     }
     public static void sort(Floor[] array) throws IndexOutOfBoundsException{
         Floor buffer;
         for (int i = 0; i < array.length; i++) {
             for (int j = i; j < array.length; j++) {
                 if(array[i].compareTo(array[j]) == 1){
                     buffer = array[i];
                     array[i] = array[j];
                     array[j] = buffer;
                 }
             }
         }
     }
     public static void sort(Space[] array, buildings.SpaceComparator comparator){
         Space buffer;
         for (int i = 0; i < array.length; i++) {
             for (int j = i; j < array.length; j++) {
                 if(comparator.compare(array[i], array[j]) == 1){
                     buffer = array[i];
                     array[i] = array[j];
                     array[j] = buffer;
                 }
             }
         }
     }
     public static void sort(Floor[] array, buildings.FloorComparator comparator){
         Floor buffer;
         for (int i = 0; i < array.length; i++) {
             for (int j = i; j < array.length; j++) {
                 if(comparator.compare(array[i], array[j]) == 1){
                     buffer = array[i];
                     array[i] = array[j];
                     array[j] = buffer;
                 }
             }
         }
     }*/
    public static <T> void sort(T[] array, Comparator<T> comparator){
        T buffer;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if(comparator.compare(array[i], array[j]) == 1){
                    buffer = array[i];
                    array[i] = array[j];
                    array[j] = buffer;
                }
            }
        }
    }

    public static void outputBuilding (Building building, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        outputStream.write(building.getNumberFloors());
        for (int i = 0; i < building.getNumberFloors(); i++) {
            outputStream.write(building.getFloor(i).getNumberSpaces());
            for (int j = 0; j < building.getFloor(i).getNumberSpaces(); j++) {
                outputStream.write(building.getFloor(i).getSpace(j).getNumber());
                outputStream.writeDouble(building.getFloor(i).getSpace(j).getSquare());
            }
        }
        outputStream.writeChar('\n');
        outputStream.flush();
    }
    public static Building inputBuilding (InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        Floor[] floors = new Floor[inputStream.read()];
        if(floors.length == 0) return null;
        for (int i = 0; i < floors.length; i++) {
            floors[i] = buildingFactory.createFloor(inputStream.read());
            for (int j = 0; j < floors[i].getNumberSpaces(); j++) {
                floors[i].setSpace(j, buildingFactory.createSpace(inputStream.read(), inputStream.readDouble()));
            }
        }
        inputStream.readChar();
        return buildingFactory.createBuilding(floors);
    }
    public static void writeBuilding(Building building, Writer out) throws IOException {
        BufferedWriter writer = new BufferedWriter(out);
        StringBuffer buffer = new StringBuffer();
        buffer.append(building.getNumberFloors());
        for (int i = 0; i < building.getNumberFloors(); i++) {
            buffer.append(' '); buffer.append(building.getFloor(i).getNumberSpaces());
            for (int j = 0; j < building.getFloor(i).getNumberSpaces(); j++) {
                buffer.append(' '); buffer.append(building.getFloor(i).getSpace(j).getNumber());
                buffer.append(' '); buffer.append(building.getFloor(i).getSpace(j).getSquare());
            }

        }
        writer.write(buffer.toString());
        writer.write("\n");
        writer.flush();
    }
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        int n;
        Floor[] floors = new Floor[(int)streamTokenizer.nval];
        if (floors.length == 0) return null;
        for (int i = 0; i < floors.length; i++) {
            streamTokenizer.nextToken();
            floors[i] = buildingFactory.createFloor((int) streamTokenizer.nval);
            for (int j = 0; j < floors[i].getNumberSpaces(); j++) {
                streamTokenizer.nextToken();
                n = (int) streamTokenizer.nval;
                streamTokenizer.nextToken();
                floors[i].setSpace(j, buildingFactory.createSpace(n, streamTokenizer.nval));
            }
        }
        return buildingFactory.createBuilding(floors);
    }
    public static void writeBuildingFormat(Building building, Writer out){
        Formatter formatter = new Formatter(out);
        formatter.format("%d", building.getNumberFloors());
        for (int i = 0; i < building.getNumberFloors(); i++) {
            formatter.format(" %d", building.getFloor(i).getNumberSpaces());
            for (int j = 0; j < building.getFloor(i).getNumberSpaces(); j++) {
                formatter.format(" %d %s", building.getFloor(i).getSpace(j).getNumber(), building.getFloor(i).getSpace(j).getSquare());
            }
        }
        formatter.format("\n");
        formatter.flush();
    }

    public static Building readBuilding(Scanner scanner) throws InputMismatchException{
        scanner.useLocale(Locale.US);
        Floor[] floors = new Floor[scanner.nextInt()];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = buildingFactory.createFloor(scanner.nextInt());
            for (int j = 0; j < floors[i].getNumberSpaces(); j++) {
                floors[i].setSpace(j, buildingFactory.createSpace(scanner.nextInt(), scanner.nextDouble()));
            }
        }
        return buildingFactory.createBuilding(floors);

    }
}
