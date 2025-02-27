package core.basesyntax;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void put(T element) throws InterruptedException {
        while (queue.size() >= capacity) {
            wait();
        }

        notify();

        queue.add(element);
    }

    public synchronized T take() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }

        notify();

        return queue.remove();
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
