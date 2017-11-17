package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class Cleaner extends Thread {
    private Floor floor;

    public Cleaner(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run(){
        for (int i = 0; i < floor.getNumberSpaces(); i++) {
            System.out.println("Cleaning room number <" + i +
                    "> with total area <" + floor.getSpace(i).getSquare() + "> square meters");
        }
    }
}
