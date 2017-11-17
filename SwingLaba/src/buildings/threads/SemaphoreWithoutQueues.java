package buildings.threads;

public class SemaphoreWithoutQueues implements Semaphore{
    private int permits;
    private int current = 0;
    private Object block = new Object();

    public SemaphoreWithoutQueues(int permits) {
        this.permits = permits;
    }

    @Override
    public void enter() throws InterruptedException {
        synchronized (block) {
            ++current;
            if (current > permits) {
                try {
                    block.wait();
                } catch (InterruptedException e) {
                    throw e;
                }

            }
        }

    }
    @Override
    public void leave() {
        synchronized (block) {
            --current;
            block.notify();
        }
    }
}
