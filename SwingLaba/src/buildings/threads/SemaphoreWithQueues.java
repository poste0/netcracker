package buildings.threads;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreWithQueues  implements Semaphore {
    private Queue<Object> queue;
    private AtomicInteger current = new AtomicInteger(0);
    private int permits;
    private boolean flag = false;

    public SemaphoreWithQueues(int permits) {
        this.permits = permits;
        this.queue = new LinkedList<>();
    }

    @Override
    public synchronized void enter() throws InterruptedException {
        flag = true;
        if (current.get() < permits) {
            current.incrementAndGet();
        } else {
            Object block = new Object();
            synchronized (block) {
                queue.offer(block);
                block.wait();
                current.incrementAndGet();
            }
        }
    }

    @Override
    public void leave() throws InterruptedException {
        while (queue.size() == 0 && flag) {
        }
        Object block = queue.poll();
        if(block == null) return;
        synchronized (block) {
            block.notify();

        }
        current.decrementAndGet();
        flag = false;
    }
}
