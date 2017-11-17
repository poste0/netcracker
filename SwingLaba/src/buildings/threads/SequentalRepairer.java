package buildings.threads;

import buildings.Floor;


public class SequentalRepairer implements Runnable {
    private Floor floor;
    private Semaphore semaphore;
    public SequentalRepairer(Floor floor, Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        for (int i = 0; i < floor.getNumberSpaces(); i++) {
            try {
                semaphore.enter();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
            System.out.println("Repairing space number <" + i + "> with total area <" +
                    floor.getSpace(i).getSquare() + "> square meters");
            try {
                semaphore.leave();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
