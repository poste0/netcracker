package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class Repairer extends Thread {
    private Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void run(){
        for (int i = 0; i < floor.getNumberSpaces(); i++) {
            System.out.println("Repairing space number <" + i + "> with total area <" +
                    floor.getSpace(i).getSquare() + "> square meters");
        }
    }
}
