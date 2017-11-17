package buildings.threads;

public interface Semaphore {
    void enter() throws InterruptedException;
    void leave() throws InterruptedException;
}
